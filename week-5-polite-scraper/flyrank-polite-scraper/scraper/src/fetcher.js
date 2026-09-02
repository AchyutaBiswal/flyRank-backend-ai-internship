// src/fetcher.js
//
// Stage 1 + Stage 5: fetch once, cache once, and be honest about failures.
//
// Every real network request in this project goes through politeFetch().
// It is the only place that talks to the network, which is what lets the
// rest of the pipeline stay simple: everything downstream just gets HTML.

import fs from 'node:fs';
import path from 'node:path';
import { config } from './config.js';

let lastRealRequestAt = 0;

/** A request that we could classify (HTTP status, timeout, or network error). */
export class FetchError extends Error {
  constructor(message, { url, status = null, kind }) {
    super(message);
    this.name = 'FetchError';
    this.url = url;
    this.status = status; // null for timeouts/network errors
    this.kind = kind; // 'timeout' | 'network' | 'http'
  }
}

/**
 * Pure decision rule, kept separate from the network code so it is
 * trivially unit-testable: should this status/kind be retried once?
 * - 404 (page does not exist) and 403 (site said no) are never retried.
 * - 5xx and timeouts/network errors are worth one retry.
 */
export function shouldRetry({ status, kind }) {
  if (kind === 'timeout' || kind === 'network') return true;
  if (typeof status === 'number' && status >= 500 && status <= 599) return true;
  return false;
}

function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

function waitForPoliteness() {
  const elapsed = Date.now() - lastRealRequestAt;
  const remaining = config.minDelayBetweenRequestsMs - elapsed;
  if (remaining > 0) return sleep(remaining);
  return Promise.resolve();
}

async function fetchOnce(url) {
  const controller = new AbortController();
  const timer = setTimeout(() => controller.abort(), config.requestTimeoutMs);

  try {
    const response = await fetch(url, {
      headers: { 'User-Agent': config.userAgent },
      signal: controller.signal,
    });

    if (response.status !== 200) {
      throw new FetchError(`Unexpected status ${response.status} for ${url}`, {
        url,
        status: response.status,
        kind: 'http',
      });
    }

    return await response.text();
  } catch (err) {
    if (err instanceof FetchError) throw err;
    if (err.name === 'AbortError') {
      throw new FetchError(`Timed out after ${config.requestTimeoutMs}ms: ${url}`, {
        url,
        kind: 'timeout',
      });
    }
    throw new FetchError(`Network error fetching ${url}: ${err.message}`, {
      url,
      kind: 'network',
    });
  } finally {
    clearTimeout(timer);
  }
}

/**
 * Fetch a URL politely, reading from disk cache when available.
 *
 * @param {string} url            Absolute URL to fetch.
 * @param {string} cacheFileName  File name (not path) to cache this page under.
 * @returns {Promise<{html: string, fromCache: boolean}>}
 */
export async function politeFetch(url, cacheFileName) {
  fs.mkdirSync(config.cacheDir, { recursive: true });
  const cachePath = path.join(config.cacheDir, cacheFileName);

  if (fs.existsSync(cachePath)) {
    const html = fs.readFileSync(cachePath, 'utf-8');
    console.log(`CACHE HIT  ${url}  (${html.length} bytes, from ${cacheFileName})`);
    return { html, fromCache: true };
  }

  await waitForPoliteness();

  let attempt = 0;
  // attempt 0 = first try, attempt 1 = single retry
  for (;;) {
    lastRealRequestAt = Date.now();
    try {
      const html = await fetchOnce(url);
      fs.writeFileSync(cachePath, html, 'utf-8');
      console.log(`FETCH      ${url}  (status 200, ${html.length} bytes)`);
      return { html, fromCache: false };
    } catch (err) {
      const canRetry = attempt < config.maxRetries && shouldRetry(err);
      if (!canRetry) throw err;
      console.log(
        `RETRY      ${url}  (${err.kind}${err.status ? ' ' + err.status : ''}, waiting ${config.retryBackoffMs}ms)`
      );
      await sleep(config.retryBackoffMs);
      attempt += 1;
    }
  }
}
