import { test } from 'node:test';
import assert from 'node:assert/strict';
import { parsePriceGBP, parseRating, cleanAvailability, normalizeRecord } from '../src/normalize.js';

test('parsePriceGBP converts "£51.77" to the number 51.77', () => {
  assert.equal(parsePriceGBP('£51.77'), 51.77);
});

test('parsePriceGBP throws on unparseable price text', () => {
  assert.throws(() => parsePriceGBP('contact us for pricing'));
});

test('parseRating converts the word "Three" to the number 3', () => {
  assert.equal(parseRating('Three'), 3);
});

test('parseRating throws on an unrecognized rating word', () => {
  assert.throws(() => parseRating('Bazillion'));
});

test('cleanAvailability collapses whitespace/newlines into one line', () => {
  const raw = '\n      In stock (22 available)\n    ';
  assert.equal(cleanAvailability(raw), 'In stock (22 available)');
});

test('normalizeRecord never throws, even when every raw field is bad', () => {
  const raw = {
    title: null,
    product_url: 'https://books.toscrape.com/catalogue/x/index.html',
    price_text: 'n/a',
    availability_text: '   ',
    rating_text: 'not-a-rating',
    description: null,
    source_page: 'https://books.toscrape.com/catalogue/page-1.html',
    fetched_at: new Date().toISOString(),
  };
  const clean = normalizeRecord(raw);
  assert.equal(clean.price_gbp, null);
  assert.equal(clean.rating, null);
  assert.equal(clean.availability, null);
  assert.equal(clean.title, null); // still surfaced, so schema validation can reject it
});

test('normalizeRecord keeps the raw text alongside the clean value', () => {
  const raw = {
    title: 'A Light in the Attic',
    product_url: 'https://books.toscrape.com/catalogue/a-light-in-the-attic_1000/index.html',
    price_text: '£51.77',
    availability_text: 'In stock (22 available)',
    rating_text: 'Three',
    description: 'A poetry collection.',
    source_page: 'https://books.toscrape.com/catalogue/page-1.html',
    fetched_at: '2026-08-06T10:00:00.000Z',
  };
  const clean = normalizeRecord(raw);
  assert.equal(clean.price_gbp, 51.77);
  assert.equal(clean.price_text, '£51.77');
  assert.equal(clean.rating, 3);
  assert.equal(clean.rating_text, 'Three');
});
