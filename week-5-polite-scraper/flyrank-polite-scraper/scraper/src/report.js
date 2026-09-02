// src/report.js
//
// Stage 5: a good job finishes its work, then tells you what happened.
//
// A scraper that reports nothing can fail silently for weeks - this file
// is how every run leaves a few honest numbers behind.

import fs from 'node:fs';
import { config } from './config.js';

/**
 * @param {object} stats
 * @param {number} stats.startedAt     epoch ms
 * @param {Array}  stats.cataloguePageResults   from crawler.discoverBookUrls()
 * @param {Array}  stats.detailPageResults      [{url, status: 'ok'|'failed', fromCache, reason?}]
 * @param {number} stats.validCount
 * @param {number} stats.invalidCount
 */
export function buildRunReport(stats) {
  const finishedAt = Date.now();

  const cataloguePagesFetched = stats.cataloguePageResults.filter((p) => p.status === 'ok').length;
  const cataloguePagesFromCache = stats.cataloguePageResults.filter((p) => p.status === 'ok' && p.fromCache).length;
  const cataloguePagesFailed = stats.cataloguePageResults.filter((p) => p.status === 'failed');

  const detailPagesOk = stats.detailPageResults.filter((p) => p.status === 'ok');
  const detailPagesFromCache = detailPagesOk.filter((p) => p.fromCache).length;
  const detailPagesFailed = stats.detailPageResults.filter((p) => p.status === 'failed');

  const failedPages = [
    ...cataloguePagesFailed.map((p) => ({ url: p.url, stage: 'catalogue', reason: p.reason })),
    ...detailPagesFailed.map((p) => ({ url: p.url, stage: 'detail', reason: p.reason })),
  ];

  return {
    started_at: new Date(stats.startedAt).toISOString(),
    finished_at: new Date(finishedAt).toISOString(),
    duration_ms: finishedAt - stats.startedAt,
    catalogue_pages_fetched: cataloguePagesFetched,
    catalogue_pages_cache_hits: cataloguePagesFromCache,
    detail_pages_fetched: detailPagesOk.length,
    detail_pages_cache_hits: detailPagesFromCache,
    total_cache_hits: cataloguePagesFromCache + detailPagesFromCache,
    unique_books_discovered: stats.detailPageResults.length,
    valid_records: stats.validCount,
    invalid_records: stats.invalidCount,
    failed_pages: failedPages.length,
    failed_page_details: failedPages,
  };
}

export function writeRunReport(report) {
  fs.writeFileSync(config.reportOutputPath, JSON.stringify(report, null, 2) + '\n', 'utf-8');
}
