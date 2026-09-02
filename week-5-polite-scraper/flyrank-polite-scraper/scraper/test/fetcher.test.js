import { test } from 'node:test';
import assert from 'node:assert/strict';
import { shouldRetry } from '../src/fetcher.js';

test('shouldRetry says yes for a 500 server error', () => {
  assert.equal(shouldRetry({ status: 500, kind: 'http' }), true);
});

test('shouldRetry says yes for a timeout', () => {
  assert.equal(shouldRetry({ status: null, kind: 'timeout' }), true);
});

test('shouldRetry says no for a 404 - the page does not exist, retrying will not help', () => {
  assert.equal(shouldRetry({ status: 404, kind: 'http' }), false);
});

test('shouldRetry says no for a 403 - the site said no, retrying is impolite', () => {
  assert.equal(shouldRetry({ status: 403, kind: 'http' }), false);
});
