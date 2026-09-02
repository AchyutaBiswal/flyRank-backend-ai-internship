// src/config.js
//
// Every "policy" number the scraper follows lives here, in one place,
// so politeness settings are configurable rather than scattered magic
// numbers across the codebase.

import path from 'node:path';
import { fileURLToPath } from 'node:url';

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const PROJECT_ROOT = path.resolve(__dirname, '..');

export const config = {
  // --- Target ---------------------------------------------------------
  // The only site this scraper is allowed to touch. It exists as a public
  // practice sandbox specifically for learning to scrape.
  entryUrl: 'https://books.toscrape.com/index.html',
  catalogPageCount: 3, // scope is fixed: first 3 catalogue pages, no more.

  // --- Politeness -------------------------------------------------------
  // TODO: replace <your-username>/<your-repo> with your real public repo
  // link before you run this against the live site for your submission.
  userAgent: 'FlyRankInternshipA9/1.0 (+https://github.com/AchyutaBiswal/flyRank-backend-ai-internship)',
  requestTimeoutMs: 8000, // give up rather than hang forever
  minDelayBetweenRequestsMs: 500, // never hammer the site; cache hits skip this
  maxRetries: 1, // one retry, only for timeouts / 5xx - never for 404/403
  retryBackoffMs: 1000,

  // --- Paths --------------------------------------------------------
  cacheDir: path.join(PROJECT_ROOT, 'cache'),
  outputDir: path.join(PROJECT_ROOT, 'output'),
  booksOutputPath: path.join(PROJECT_ROOT, 'output', 'books.json'),
  errorsOutputPath: path.join(PROJECT_ROOT, 'output', 'errors.json'),
  reportOutputPath: path.join(PROJECT_ROOT, 'output', 'run-report.json'),

  // A single made-up book URL used only when --inject-fault is passed on
  // the command line, to prove the pipeline survives one broken page
  // without ever sending extra traffic to the real site.
  fakeBookUrlForFaultInjection:
    'https://books.toscrape.com/catalogue/this-book-does-not-exist-000000/index.html',
};
