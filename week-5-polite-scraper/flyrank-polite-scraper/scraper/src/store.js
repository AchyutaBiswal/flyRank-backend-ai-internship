// src/store.js
//
// Stage 4: validate every record before storing it.
//
// Deduplicates by canonical URL, runs every record through the schema,
// and writes books.json / errors.json. Both files are fully overwritten
// on every run (never appended to), which is what makes reruns
// idempotent: the same 60 good inputs always produce the same 60
// records, not 120.

import fs from 'node:fs';
import path from 'node:path';
import { config } from './config.js';
import { validateBookRecord } from './schema.js';

/**
 * Remove records with a duplicate canonical URL, keeping the first seen.
 */
export function dedupeByProductUrl(records) {
  const seen = new Set();
  const unique = [];
  for (const record of records) {
    if (!seen.has(record.product_url)) {
      seen.add(record.product_url);
      unique.push(record);
    }
  }
  return unique;
}

/**
 * Validate a list of normalized records, splitting them into valid and
 * invalid buckets. Invalid records carry the schema's reason string.
 */
export function partitionByValidity(records) {
  const valid = [];
  const invalid = [];
  for (const record of records) {
    const result = validateBookRecord(record);
    if (result.ok) {
      valid.push(result.data);
    } else {
      invalid.push({ record, reason: result.reason });
    }
  }
  return { valid, invalid };
}

/**
 * Validate + dedupe + write output/books.json and output/errors.json.
 * @returns {{ valid: object[], invalid: object[] }}
 */
export function storeRecords(normalizedRecords) {
  fs.mkdirSync(config.outputDir, { recursive: true });

  const deduped = dedupeByProductUrl(normalizedRecords);
  const { valid, invalid } = partitionByValidity(deduped);

  fs.writeFileSync(config.booksOutputPath, JSON.stringify(valid, null, 2) + '\n', 'utf-8');
  fs.writeFileSync(config.errorsOutputPath, JSON.stringify(invalid, null, 2) + '\n', 'utf-8');

  return { valid, invalid };
}
