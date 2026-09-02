// src/urls.js
//
// Small, pure, easily-tested URL helpers. Kept separate from the crawler
// so "how do relative links become absolute ones" can be unit tested
// without downloading anything.

/**
 * Resolve an href found on a page into an absolute URL, using the page's
 * own URL as the base - never by gluing strings together.
 */
export function toAbsoluteUrl(href, pageUrl) {
  return new URL(href, pageUrl).toString();
}

/**
 * Resolve a list of hrefs against a base page URL and remove duplicates,
 * preserving first-seen order.
 */
export function resolveAndDedupe(hrefs, pageUrl) {
  const seen = new Set();
  const result = [];
  for (const href of hrefs) {
    const absolute = toAbsoluteUrl(href, pageUrl);
    if (!seen.has(absolute)) {
      seen.add(absolute);
      result.push(absolute);
    }
  }
  return result;
}

/**
 * Turn a book detail URL into a stable, filesystem-safe cache file name.
 * e.g. https://books.toscrape.com/catalogue/a-light-in-the-attic_1000/index.html
 *   -> book-a-light-in-the-attic_1000.html
 */
export function bookCacheFileName(bookUrl) {
  const { pathname } = new URL(bookUrl);
  const segments = pathname.split('/').filter(Boolean); // drop empty strings
  // Expected shape: ['catalogue', '<slug>', 'index.html']
  const slug = segments.length >= 2 ? segments[segments.length - 2] : segments.join('-');
  const safeSlug = slug.replace(/[^a-zA-Z0-9_-]/g, '-');
  return `book-${safeSlug}.html`;
}

export function cataloguePageCacheFileName(pageNumber) {
  return `catalogue-page-${pageNumber}.html`;
}
