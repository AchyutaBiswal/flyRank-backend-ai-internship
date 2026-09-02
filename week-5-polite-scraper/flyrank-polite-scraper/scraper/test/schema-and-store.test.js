import { test } from 'node:test';
import assert from 'node:assert/strict';
import { validateBookRecord } from '../src/schema.js';
import { dedupeByProductUrl, partitionByValidity } from '../src/store.js';

const goodRecord = {
  title: 'A Light in the Attic',
  product_url: 'https://books.toscrape.com/catalogue/a-light-in-the-attic_1000/index.html',
  price_gbp: 51.77,
  price_text: '£51.77',
  availability: 'In stock (22 available)',
  rating: 3,
  rating_text: 'Three',
  description: 'A poetry collection.',
  source_page: 'https://books.toscrape.com/catalogue/page-1.html',
  fetched_at: '2026-08-06T10:00:00.000Z',
};

test('validateBookRecord accepts a fully clean record', () => {
  const result = validateBookRecord(goodRecord);
  assert.equal(result.ok, true);
});

test('validateBookRecord rejects a record with a null price_gbp and explains why', () => {
  const result = validateBookRecord({ ...goodRecord, price_gbp: null });
  assert.equal(result.ok, false);
  assert.match(result.reason, /price_gbp/);
});

test('validateBookRecord accepts a null description (no description on the page)', () => {
  const result = validateBookRecord({ ...goodRecord, description: null });
  assert.equal(result.ok, true);
});

test('dedupeByProductUrl removes a record whose canonical URL repeats, keeping the first', () => {
  const twice = [goodRecord, { ...goodRecord, title: 'Duplicate Entry' }];
  const deduped = dedupeByProductUrl(twice);
  assert.equal(deduped.length, 1);
  assert.equal(deduped[0].title, 'A Light in the Attic');
});

test('partitionByValidity sends a bad record to the invalid bucket with a reason', () => {
  const bad = { ...goodRecord, title: '' };
  const { valid, invalid } = partitionByValidity([goodRecord, bad]);
  assert.equal(valid.length, 1);
  assert.equal(invalid.length, 1);
  assert.match(invalid[0].reason, /title/);
});
