// src/index.js
//
// Orchestrates the whole pipeline: fetch -> extract -> normalize ->
// validate -> store -> report. One documented command, one run.
//
//   npm start                 normal run
//   npm run start:fault       normal run + one deliberately broken book URL,
//                             to prove Stage 5's failure handling (Done
//                             checklist item: "One deliberately broken URL
//                             is logged and skipped").

import { config } from './config.js';
import { discoverBookUrls } from './crawler.js';
import { politeFetch, FetchError } from './fetcher.js';
import { extractBookRecord } from './extractor.js';
import { normalizeRecord } from './normalize.js';
import { storeRecords } from './store.js';
import { buildRunReport, writeRunReport } from './report.js';
import { bookCacheFileName } from './urls.js';

async function run() {
  const injectFault = process.argv.includes('--inject-fault');
  const startedAt = Date.now();

  console.log('=== Stage 2: discovering catalogue pages & book URLs ===');
  const { bookUrls, pageResults } = await discoverBookUrls();

  if (injectFault) {
    console.log(`(fault injection) appending one made-up book URL: ${config.fakeBookUrlForFaultInjection}`);
    bookUrls.push(config.fakeBookUrlForFaultInjection);
  }

  console.log(
    `catalogue_pages=${pageResults.filter((p) => p.status === 'ok').length} discovered=${bookUrls.length} unique_urls=${bookUrls.length}`
  );

  console.log('\n=== Stage 3: extracting raw records from each book page ===');
  const rawRecords = [];
  const detailPageResults = [];

  for (const url of bookUrls) {
    const cacheFileName = bookCacheFileName(url);
    try {
      const { html, fromCache } = await politeFetch(url, cacheFileName);
      const sourcePage = pageResults.find((p) => p.status === 'ok')?.url ?? config.entryUrl;
      const raw = extractBookRecord(html, url, sourcePage);
      rawRecords.push(raw);
      detailPageResults.push({ url, status: 'ok', fromCache });
    } catch (err) {
      const reason = err instanceof FetchError ? err.message : String(err);
      console.error(`FAILED book page (${url}): ${reason}`);
      detailPageResults.push({ url, status: 'failed', reason });
      // One bad page is logged and skipped - the run keeps going.
    }
  }
  console.log(`detail_pages=${rawRecords.length}`);
  if (rawRecords[0]) {
    console.log('Sample raw record:', JSON.stringify(rawRecords[0], null, 2));
  }

  console.log('\n=== Stage 4: normalizing and validating records ===');
  const normalized = rawRecords.map(normalizeRecord);
  const { valid, invalid } = storeRecords(normalized);
  console.log(`valid_records=${valid.length} invalid_records=${invalid.length}`);

  console.log('\n=== Stage 5: writing the run report ===');
  const report = buildRunReport({
    startedAt,
    cataloguePageResults: pageResults,
    detailPageResults,
    validCount: valid.length,
    invalidCount: invalid.length,
  });
  writeRunReport(report);
  console.log(JSON.stringify(report, null, 2));

  console.log(`\nDone. See ${config.booksOutputPath}, ${config.errorsOutputPath}, ${config.reportOutputPath}`);
}

run().catch((err) => {
  // The one place a truly unexpected error is allowed to stop the process -
  // every expected failure (bad page, bad record) is already handled above.
  console.error('Fatal error, run aborted:', err);
  process.exitCode = 1;
});
