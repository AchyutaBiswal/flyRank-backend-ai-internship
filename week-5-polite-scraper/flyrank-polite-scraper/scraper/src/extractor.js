// src/extractor.js
//
// Stage 3: extract the raw records.
//
// Pulls the eight raw fields out of one book detail page. Selectors are
// aimed at the product area (.product_main / #product_description), not
// "the first thing that looks like a price" - so the page can grow other
// prices elsewhere without breaking this.
//
// This function never throws on missing optional fields: a field it can't
// find becomes null. Whether that null is acceptable is Stage 4's problem
// (schema validation) - the extractor's only job is honest extraction.

import * as cheerio from 'cheerio';

const RATING_WORDS = new Set(['One', 'Two', 'Three', 'Four', 'Five']);

function textOrNull($el) {
  if (!$el || $el.length === 0) return null;
  const text = $el.text().trim().replace(/\s+/g, ' ');
  return text.length > 0 ? text : null;
}

/**
 * Extract the raw, uncleaned record for one book detail page.
 *
 * @param {string} html         The book detail page's HTML.
 * @param {string} productUrl   Absolute, canonical URL of this book page.
 * @param {string} sourcePageUrl Absolute URL of the catalogue page it was found on.
 * @param {() => string} [now]  Injectable clock for testing.
 */
export function extractBookRecord(html, productUrl, sourcePageUrl, now = () => new Date().toISOString()) {
  const $ = cheerio.load(html);
  const $main = $('.product_main');

  const title = textOrNull($main.find('h1'));
  const priceText = textOrNull($main.find('.price_color').first());
  const availabilityText = textOrNull($main.find('.availability'));

  // Rating is encoded as a CSS class, e.g. class="star-rating Three".
  const starClasses = ($main.find('p.star-rating').attr('class') || '').split(/\s+/);
  const ratingWord = starClasses.find((cls) => RATING_WORDS.has(cls)) || null;

  // Description lives in the <p> immediately after #product_description.
  // Some books genuinely have none - that must stay null, never invented.
  const description = textOrNull($('#product_description').next('p'));

  return {
    title,
    product_url: productUrl,
    price_text: priceText,
    availability_text: availabilityText,
    rating_text: ratingWord,
    description,
    source_page: sourcePageUrl,
    fetched_at: now(),
  };
}
