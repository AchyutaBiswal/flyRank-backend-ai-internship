import { test } from 'node:test';
import assert from 'node:assert/strict';
import fs from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import { extractBookRecord } from '../src/extractor.js';

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const fixturesDir = path.join(__dirname, 'fixtures');
const fixedClock = () => '2026-08-06T10:00:00.000Z';

test('extractBookRecord stores null description when the page has none, never invents text', () => {
  const html = fs.readFileSync(path.join(fixturesDir, 'book-missing-description.html'), 'utf-8');
  const record = extractBookRecord(
    html,
    'https://books.toscrape.com/catalogue/no-description-book/index.html',
    'https://books.toscrape.com/catalogue/page-1.html',
    fixedClock
  );

  assert.equal(record.title, 'A Book Without A Description');
  assert.equal(record.price_text, '£12.34');
  assert.equal(record.rating_text, 'Four');
  assert.equal(record.description, null);
  // All eight keys must always be present, even when a value is null.
  assert.deepEqual(Object.keys(record).sort(), [
    'availability_text',
    'description',
    'fetched_at',
    'price_text',
    'product_url',
    'rating_text',
    'source_page',
    'title',
  ]);
});

test('extractBookRecord does not throw on a malformed page missing the product area', () => {
  const html = fs.readFileSync(path.join(fixturesDir, 'book-malformed.html'), 'utf-8');

  assert.doesNotThrow(() => {
    const record = extractBookRecord(
      html,
      'https://books.toscrape.com/catalogue/malformed-book/index.html',
      'https://books.toscrape.com/catalogue/page-1.html',
      fixedClock
    );
    assert.equal(record.title, null);
    assert.equal(record.price_text, null);
    assert.equal(record.rating_text, null);
    assert.equal(record.availability_text, null);
  });
});
