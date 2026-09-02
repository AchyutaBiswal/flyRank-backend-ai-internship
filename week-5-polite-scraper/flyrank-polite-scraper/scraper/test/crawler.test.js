import { test } from 'node:test';
import assert from 'node:assert/strict';
import fs from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import { parseCataloguePage } from '../src/crawler.js';

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const fixturesDir = path.join(__dirname, 'fixtures');

test('parseCataloguePage extracts absolute, deduplicated book URLs and the next page URL', () => {
  const html = fs.readFileSync(path.join(fixturesDir, 'catalogue-page-1.html'), 'utf-8');
  const { bookUrls, nextPageUrl } = parseCataloguePage(html, 'https://books.toscrape.com/index.html');

  // The fixture lists the same book twice - dedupe must collapse it to one.
  assert.deepEqual(bookUrls, [
    'https://books.toscrape.com/catalogue/a-light-in-the-attic_1000/index.html',
    'https://books.toscrape.com/catalogue/tipping-the-velvet_999/index.html',
  ]);
  assert.equal(nextPageUrl, 'https://books.toscrape.com/catalogue/page-2.html');
});
