import { test } from 'node:test';
import assert from 'node:assert/strict';
import { toAbsoluteUrl, resolveAndDedupe, bookCacheFileName } from '../src/urls.js';

test('toAbsoluteUrl resolves a relative href against its page URL', () => {
  const pageUrl = 'https://books.toscrape.com/catalogue/page-1.html';
  const absolute = toAbsoluteUrl('a-light-in-the-attic_1000/index.html', pageUrl);
  assert.equal(absolute, 'https://books.toscrape.com/catalogue/a-light-in-the-attic_1000/index.html');
});

test('toAbsoluteUrl resolves a "../" style relative href correctly', () => {
  const pageUrl = 'https://books.toscrape.com/catalogue/page-2.html';
  const absolute = toAbsoluteUrl('../a-light-in-the-attic_1000/index.html', pageUrl);
  assert.equal(absolute, 'https://books.toscrape.com/a-light-in-the-attic_1000/index.html');
});

test('resolveAndDedupe removes duplicate links, keeping first-seen order', () => {
  const pageUrl = 'https://books.toscrape.com/catalogue/page-1.html';
  const hrefs = ['book-a/index.html', 'book-b/index.html', 'book-a/index.html'];
  const result = resolveAndDedupe(hrefs, pageUrl);
  assert.deepEqual(result, [
    'https://books.toscrape.com/catalogue/book-a/index.html',
    'https://books.toscrape.com/catalogue/book-b/index.html',
  ]);
});

test('bookCacheFileName derives a stable, safe file name from a book URL', () => {
  const url = 'https://books.toscrape.com/catalogue/a-light-in-the-attic_1000/index.html';
  assert.equal(bookCacheFileName(url), 'book-a-light-in-the-attic_1000.html');
});
