// src/schema.js
//
// Stage 4: the schema is the recipe.
//
// This is the single source of truth for what a "valid, storable" book
// record looks like. Anything that doesn't match goes to errors.json
// with the reason, instead of quietly landing in books.json.

import { z } from 'zod';

export const BookRecordSchema = z.object({
  title: z.string().min(1, 'title must be a non-empty string'),
  product_url: z.string().url('product_url must be an absolute URL'),
  price_gbp: z.number().positive('price_gbp must be a positive number'),
  price_text: z.string(),
  availability: z.string().min(1, 'availability must be a non-empty string'),
  rating: z.number().int().min(1).max(5, 'rating must be an integer from 1 to 5'),
  rating_text: z.string(),
  description: z.string().nullable(),
  source_page: z.string().url('source_page must be an absolute URL'),
  fetched_at: z.string().datetime({ message: 'fetched_at must be an ISO-8601 timestamp' }),
});

/**
 * Validate one normalized record.
 * @returns {{ ok: true, data: object } | { ok: false, reason: string }}
 */
export function validateBookRecord(record) {
  const result = BookRecordSchema.safeParse(record);
  if (result.success) {
    return { ok: true, data: result.data };
  }
  const reason = result.error.issues
    .map((issue) => `${issue.path.join('.') || '(root)'}: ${issue.message}`)
    .join('; ');
  return { ok: false, reason };
}
