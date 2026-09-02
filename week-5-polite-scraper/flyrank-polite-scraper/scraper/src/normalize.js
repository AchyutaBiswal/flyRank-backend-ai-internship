// src/normalize.js
//
// Stage 4: clean it, check it, store it.
//
// Raw strings are ingredients; these are the recipes that turn them into
// typed values. Every parser here throws a descriptive Error on bad input
// rather than guessing - normalizeRecord() catches that and leaves the
// clean field as null, so a single bad field fails schema validation
// later instead of silently becoming a wrong number.

const RATING_WORD_TO_NUMBER = { One: 1, Two: 2, Three: 3, Four: 4, Five: 5 };

/** "£51.77" -> 51.77 */
export function parsePriceGBP(priceText) {
  if (typeof priceText !== 'string') {
    throw new Error(`price_text is missing or not a string: ${JSON.stringify(priceText)}`);
  }
  const match = priceText.match(/([0-9]+(?:\.[0-9]+)?)/);
  if (!match) {
    throw new Error(`could not find a number in price text: "${priceText}"`);
  }
  const value = Number.parseFloat(match[1]);
  if (Number.isNaN(value)) {
    throw new Error(`price text did not parse to a number: "${priceText}"`);
  }
  return value;
}

/** "Three" -> 3 */
export function parseRating(ratingText) {
  if (typeof ratingText !== 'string' || !(ratingText in RATING_WORD_TO_NUMBER)) {
    throw new Error(`unrecognized rating word: ${JSON.stringify(ratingText)}`);
  }
  return RATING_WORD_TO_NUMBER[ratingText];
}

/** Collapse whitespace/newlines from the availability block into one clean line. */
export function cleanAvailability(availabilityText) {
  if (typeof availabilityText !== 'string' || availabilityText.trim().length === 0) {
    throw new Error(`availability text is missing or empty: ${JSON.stringify(availabilityText)}`);
  }
  return availabilityText.trim().replace(/\s+/g, ' ');
}

/**
 * Turn one raw record (Stage 3 output) into a normalized record.
 * Never throws: a field that fails to parse becomes null, and the raw
 * text is always kept alongside the clean value.
 */
export function normalizeRecord(raw) {
  let price_gbp = null;
  try {
    price_gbp = parsePriceGBP(raw.price_text);
  } catch {
    price_gbp = null;
  }

  let rating = null;
  try {
    rating = parseRating(raw.rating_text);
  } catch {
    rating = null;
  }

  let availability = null;
  try {
    availability = cleanAvailability(raw.availability_text);
  } catch {
    availability = null;
  }

  return {
    title: raw.title ?? null,
    // The absolute product URL doubles as this record's canonical identity.
    product_url: raw.product_url,
    price_gbp,
    price_text: raw.price_text ?? null,
    availability,
    rating,
    rating_text: raw.rating_text ?? null,
    description: raw.description ?? null,
    source_page: raw.source_page,
    fetched_at: raw.fetched_at,
  };
}
