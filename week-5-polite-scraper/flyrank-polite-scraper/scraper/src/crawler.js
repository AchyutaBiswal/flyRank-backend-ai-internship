// src/crawler.js
//
// Stage 2: find all three pages.
//
// A crawler finds the shelves; an extractor (extractor.js) reads the labels.
// This module only cares about discovering book URLs and following the
// catalogue's own "next" link - it never guesses what page 4 would be.

import * as cheerio from 'cheerio';
import { config } from './config.js';
import { politeFetch, FetchError } from './fetcher.js';
import { resolveAndDedupe, toAbsoluteUrl, cataloguePageCacheFileName } from './urls.js';

/**
 * Pull every book link and the "next page" link out of one catalogue page.
 * Pure function over already-downloaded HTML, so it's easy to test.
 */
export function parseCataloguePage(html, pageUrl) {
  const $ = cheerio.load(html);

  const hrefs = $('article.product_pod h3 a')
    .map((_, el) => $(el).attr('href'))
    .get()
    .filter(Boolean);

  const bookUrls = resolveAndDedupe(hrefs, pageUrl);

  const nextHref = $('ul.pager li.next a').attr('href');
  const nextPageUrl = nextHref ? toAbsoluteUrl(nextHref, pageUrl) : null;

  return { bookUrls, nextPageUrl };
}

/**
 * Download the first `config.catalogPageCount` catalogue pages, following
 * the site's own pagination, and return the deduplicated list of book URLs
 * plus a per-page log of what happened (used for the run report).
 */
export async function discoverBookUrls() {
  const allBookUrls = [];
  const seen = new Set();
  const pageResults = [];

  let currentUrl = config.entryUrl;

  for (let pageNumber = 1; pageNumber <= config.catalogPageCount; pageNumber += 1) {
    if (!currentUrl) {
      // The site ran out of pages before we reached our scope - note it and stop.
      pageResults.push({
        pageNumber,
        url: null,
        status: 'skipped',
        reason: 'no further "next" link on the catalogue',
      });
      break;
    }

    const cacheFileName = cataloguePageCacheFileName(pageNumber);

    try {
      const { html, fromCache } = await politeFetch(currentUrl, cacheFileName);
      const { bookUrls, nextPageUrl } = parseCataloguePage(html, currentUrl);

      for (const url of bookUrls) {
        if (!seen.has(url)) {
          seen.add(url);
          allBookUrls.push(url);
        }
      }

      pageResults.push({
        pageNumber,
        url: currentUrl,
        status: 'ok',
        fromCache,
        booksFound: bookUrls.length,
      });

      currentUrl = nextPageUrl;
    } catch (err) {
      const reason = err instanceof FetchError ? err.message : String(err);
      console.error(`FAILED catalogue page ${pageNumber} (${currentUrl}): ${reason}`);
      pageResults.push({
        pageNumber,
        url: currentUrl,
        status: 'failed',
        reason,
      });
      // A broken catalogue page can't tell us where the next one is,
      // so we stop discovering further pages but keep whatever books
      // we already found from earlier pages.
      break;
    }
  }

  return { bookUrls: allBookUrls, pageResults };
}
