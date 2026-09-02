# The Polite Scraper

FlyRank Internship · Backend Track · Week 5 · Assignment A9

## Overview

This project is a small, polite scraping pipeline for [Books to Scrape](https://books.toscrape.com/),
a public sandbox built specifically for practising web scraping. It downloads
the first three catalogue pages, visits all 60 book pages they link to, turns
the messy HTML into clean, schema-checked JSON records, survives a broken
page without crashing, and ends every run with an honest report of what
happened.

The pipeline follows one shape end to end: **fetch → extract → normalize →
validate → store → report**.

## Target classification (Stage 0)

- **Site:** `https://books.toscrape.com/` — the "Books to Scrape - Sandbox".
  The site's own homepage says *"We love being scraped!"* and every page
  carries the banner *"This is a demo website for web scraping purposes.
  Prices and ratings here were randomly assigned and have no real meaning."*
  That is explicit, on-page permission, and it's the only kind of site this
  project touches.
- **Scope:** the first 3 catalogue pages only (`index.html`, `page-2.html`,
  `page-3.html`), and the 60 book detail pages they link to. Nothing else on
  the site is requested.
- **robots.txt result:** `GET https://books.toscrape.com/robots.txt` returns
  **HTTP 404 — no robots file found**. A missing file is not itself
  permission; the actual permission here comes from the site's own banner
  text and its stated purpose as a scraping sandbox.
- **What is collected:** book title, price, availability, star rating, and
  description — all data the site publishes openly for this exact purpose.
- **Why this is appropriate:** the target is a purpose-built practice
  sandbox, the scope is small and fixed, and every request is identified,
  throttled, and capped well below anything that could affect the site.

**I will not reuse this code on another site without checking its rules and terms first.**

## Features

- Downloads exactly 3 catalogue pages and discovers all 60 unique book URLs
  by following the site's own "next" link — no hardcoded book list.
- Visits every book detail page and extracts 8 raw fields, always including
  every key even when a value is missing (`description: null`, never
  invented text).
- Cleans raw text into typed values: `"£51.77"` → `51.77`, `"Three"` → `3`,
  messy whitespace → one clean availability line — while keeping the
  original raw text alongside every clean value.
- Validates every record against a Zod schema before it's stored; anything
  that fails goes to `errors.json` with a human-readable reason.
- Deduplicates by canonical URL and overwrites output on every run, so
  reruns are idempotent: always 60 records, never 120.
- Survives a broken page: one bad catalogue page or book page is logged and
  skipped, and the rest of the run finishes normally.
- On-disk cache: every downloaded page is saved once and reused on
  subsequent runs, so repeated development runs don't re-hit the site.
- Ends every run with `output/run-report.json` — honest counts of what
  happened, including any failures.

## Technologies

- **Node.js 20+** — runtime, built-in `fetch`, built-in `node:test` runner
- **Cheerio** — HTML parsing/selection (jQuery-style API over the DOM)
- **Zod** — schema definition and validation
- No database, no paid API, no browser automation — none of it is needed
  for data that already arrives in the server's HTML.

## Project structure

```
scraper/
├── src/
│   ├── index.js       # orchestrates the whole pipeline (the entry point)
│   ├── config.js       # every tunable: URL, user-agent, delay, timeout, retries
│   ├── fetcher.js       # politeFetch(): cache, timeout, retry rules
│   ├── urls.js            # pure URL helpers: absolute resolution, dedupe, cache names
│   ├── crawler.js          # Stage 2: discover the 3 catalogue pages + book URLs
│   ├── extractor.js         # Stage 3: raw 8-field record from one book page
│   ├── normalize.js          # Stage 4: price/rating/availability cleaning
│   ├── schema.js               # Stage 4: the Zod schema, the "recipe"
│   ├── store.js                 # Stage 4/5: dedupe, validate, write JSON
│   └── report.js                 # Stage 5: build & write run-report.json
├── test/
│   ├── fixtures/            # small saved HTML files used to test without the network
│   └── *.test.js             # unit tests (node:test)
├── cache/                      # gitignored - recreated by running the scraper
├── output/
│   ├── books.json               # valid, validated records
│   ├── errors.json                # invalid records + why they failed
│   └── run-report.json             # honest numbers from the last run
├── package.json
└── .gitignore
```

## How it works

1. **Fetch** — download a page with an honest User-Agent, a timeout, and a
   status check. Only HTTP 200 is treated as "the page arrived."
2. **Wait politely** — at least 500ms between real requests to the site.
   Cached pages need no delay; they never leave your computer.
3. **Parse** — Cheerio reads the saved HTML and pulls out the fields aimed
   at the product area of the page, not "the first thing that looks like a
   price."
4. **Clean** — raw strings become typed values: currency text → number,
   rating word → number, messy whitespace → one clean line.
5. **Validate** — every record is checked against the Zod schema before it
   is allowed into `books.json`. Failures go to `errors.json` with a reason.
6. **Store & report** — good records are deduplicated and written; the run
   ends with a report of what happened, good and bad.

## Record schema

Each record in `output/books.json` has this shape:

| Field            | Type              | Notes                                             |
|------------------|-------------------|----------------------------------------------------|
| `title`          | string            | non-empty                                          |
| `product_url`    | string (URL)      | absolute; also this record's canonical identity    |
| `price_gbp`      | number            | parsed from `price_text`, must be positive         |
| `price_text`     | string            | the original raw price text, e.g. `"£51.77"`       |
| `availability`   | string            | cleaned, single-line                               |
| `rating`         | integer 1–5       | parsed from `rating_text`                          |
| `rating_text`    | string            | the original raw rating word, e.g. `"Three"`       |
| `description`    | string or `null`  | `null` when the page has no description            |
| `source_page`    | string (URL)      | which catalogue page this book was discovered on   |
| `fetched_at`     | string (ISO-8601) | when the detail page was fetched                   |

## Politeness practices

- **User-Agent:** `FlyRankInternshipA9/1.0 (+https://github.com/<your-username>/<your-repo>)`
  — replace the placeholder with your real repo link before your submission run.
- **Timeout:** every request gives up after 8 seconds rather than hanging.
- **Delay:** at least 500ms between real (non-cached) requests.
- **No concurrency:** requests are sent one at a time, never in parallel.
- **Cache:** every page is saved to `cache/` after its first successful
  fetch and read from disk on every later run.
- **Retry rules:** a timeout or 5xx gets one retry after a short wait; a
  404 (page doesn't exist) or 403 (site said no) is never retried.

## Error handling (Stage 5)

Every page is handled independently. If one book page is broken (timeout,
404, 500, malformed HTML), it is logged to the console and to
`run-report.json`'s `failed_page_details`, and the run continues — the other
59 good records are unaffected. The same applies to a broken catalogue page,
though a broken catalogue page also means the crawler can't discover what
came after it (it doesn't know where "next" pointed).

Malformed or incomplete book pages don't crash the extractor either: any
field the parser can't find becomes `null` in the raw record. Schema
validation is what ultimately decides if a record is good enough to store —
a `null` where a string or number was required fails validation and the
record (with the schema's exact reason) lands in `errors.json` instead of
silently entering `books.json`.

## Why this assignment needed no browser

The book data (title, price, availability, rating, description) is already
present in the plain HTML the server sends for each page — there is no
JavaScript-rendered content to wait for. Using a headless browser here would
only add startup cost and memory for no extra data.

## How to run

```bash
cd scraper
npm install

# Normal run: 3 catalogue pages, 60 book pages, clean JSON + report
npm start

# Same, but with one deliberately broken book URL appended, to prove
# Stage 5's failure handling (this never sends extra traffic to the real
# site beyond the one 404 the fake URL itself produces):
npm run start:fault
```

Run it twice: the first run prints `FETCH` for every page; the second run
prints `CACHE HIT` for everything, and `output/books.json` still has exactly
60 records — not 120.

## How to test

```bash
npm test
```

This runs the unit tests under `test/` (Node's built-in test runner — no
extra test framework needed). They cover, without any network access:

- price normalization (`"£51.77"` → `51.77`, and rejecting bad input)
- relative → absolute URL resolution, including duplicate-link removal
- a fixture with a missing description (`description` stays `null`)
- a fixture with malformed/missing markup (extractor never throws)
- schema validation, including a specific rejection reason
- deduplication by canonical URL
- the retry decision rule (retry 5xx/timeout, never retry 404/403)

## How to verify the final JSON

After `npm start`:

- `output/books.json` should contain exactly 60 objects, each with all 10
  schema fields, `price_gbp` as a number, and `product_url`/`source_page`
  starting with `https://`.
- `output/errors.json` should be empty (`[]`) on a normal run against the
  live site.
- `output/run-report.json` should show `valid_records: 60`,
  `invalid_records: 0`, and `failed_pages: 0`.

## Sample run-report.json

This is a representative report from a run with `--inject-fault` (one
deliberately broken book URL appended on purpose), showing the shape and
that a single failure doesn't take down the other 60 good records:

```json
{
  "started_at": "2026-09-02T05:31:32.810Z",
  "finished_at": "2026-09-02T05:32:19.811Z",
  "duration_ms": 47001,
  "catalogue_pages_fetched": 3,
  "catalogue_pages_cache_hits": 0,
  "detail_pages_fetched": 60,
  "detail_pages_cache_hits": 0,
  "total_cache_hits": 0,
  "unique_books_discovered": 61,
  "valid_records": 60,
  "invalid_records": 0,
  "failed_pages": 1,
  "failed_page_details": [
    {
      "url": "https://books.toscrape.com/catalogue/this-book-does-not-exist-000000/index.html",
      "stage": "detail",
      "reason": "Unexpected status 404 for https://books.toscrape.com/catalogue/this-book-does-not-exist-000000/index.html"
    }
  ]
}
```

*(Replace this with your own real `run-report.json` output before submitting —
this one was generated to show the expected shape.)*

## Ethics note

- Prefer an official API over scraping whenever one exists; this assignment
  only touches a site that explicitly invites scraping.
- Never bypass logins, paywalls, CAPTCHAs, or a site's explicit blocks.
- Collect only the data you actually need, at a pace a human wouldn't
  notice, and identify yourself honestly in every request.

## Known limitation

The crawler stops discovering further catalogue pages the moment one
catalogue page fails, since a broken page can't tell it where "next"
points. Books already discovered from earlier pages are still processed
normally — only pages after the break are skipped.

## Requirements checklist

- [x] One documented command (`npm start`) processes exactly the first 3
      catalogue pages and discovers 60 unique book URLs.
- [x] Every detail page produces the 8 raw fields, plus a numeric `price_gbp`.
- [x] Records are schema-validated before storage; failures land in
      `errors.json` with a reason.
- [x] `output/books.json` holds exactly 60 unique records, after the first
      run and after a rerun.
- [x] Every real request sends an identifying User-Agent, has a timeout,
      waits ≥500ms between requests, and checks the status code;
      development reads from cache.
- [x] The README documents the target classification and the robots check
      result.
- [x] One deliberately broken URL is logged and skipped (`npm run start:fault`);
      the run finishes and the good records survive.
- [x] `output/run-report.json` reports counts, failures, cache hits, and
      duration.
- [ ] Public GitHub repo with 7+ meaningful commits — see Git commands below.
- [ ] Browser cost comparison (stretch, optional) — not implemented in this
      submission.
- [ ] AI rematch (bonus stage, optional) — not implemented in this submission.

## Git commands for submission

Suggested commit history, one commit per stage (adjust as you actually work
through it):

```bash
git init
git add scraper/README.md scraper/.gitignore
git commit -m "Stage 0: classify scraping target"

git add scraper/src/config.js scraper/src/fetcher.js
git commit -m "Stage 1: fetch and cache HTML"

git add scraper/src/crawler.js scraper/src/urls.js
git commit -m "Stage 2: discover three catalogue pages"

git add scraper/src/extractor.js
git commit -m "Stage 3: extract book details"

git add scraper/src/normalize.js scraper/src/schema.js scraper/src/store.js
git commit -m "Stage 4: validate normalized records"

git add scraper/src/report.js scraper/src/index.js
git commit -m "Stage 5: survive failures, report the run"

git add scraper/test/ scraper/output/ scraper/README.md
git commit -m "Stage 6: publish scraper evidence"

git branch -M main
git remote add origin <YOUR_GITHUB_REPOSITORY_URL>
git push -u origin main
```

Replace `<YOUR_GITHUB_REPOSITORY_URL>` with your real repository URL — don't
invent one.
