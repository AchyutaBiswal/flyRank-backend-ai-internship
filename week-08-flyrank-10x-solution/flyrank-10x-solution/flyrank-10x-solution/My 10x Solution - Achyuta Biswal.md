# My 10x Solution — Achyuta Biswal

## FlyRank Week 8 Capstone: Customer Feedback Intelligence Platform

### The problem

Small and mid-sized teams collect customer feedback across support tickets, surveys,
and app reviews, but rarely have time to read all of it closely. Important signals
(a recurring bug, a pricing complaint, a delighted customer) get buried in a spreadsheet
nobody revisits. Manually tagging sentiment and writing weekly summaries is repetitive,
low-leverage work.

### The 10x solution

A small backend service that:

1. Accepts feedback through a simple, authenticated REST API.
2. Uses an **LLM** to automatically label sentiment, summarize the message, and pull
   out keywords — turning raw text into structured, actionable data in one API call.
3. **Caches** analysis results so the same feedback is never re-analyzed twice, keeping
   LLM costs and latency down.
4. Runs a **background job** every day that rolls everything up into a **PDF report**
   (total feedback, average rating, positive/neutral/negative counts) — so a manager
   can open yesterday's PDF instead of scrolling through a table.
5. Keeps everything secure and multi-tenant-aware with **JWT authentication**: regular
   users only see their own submissions, while an ADMIN role sees everything.

What used to be "someone manually reads 50 feedback entries and writes a summary email"
becomes "the system tags, summarizes, and reports on feedback automatically, all day,
every day" — a genuine 10x reduction in manual effort for a real, common business need.

### Why this design

- **Spring JDBC (`JdbcTemplate`) instead of JPA** — keeps the persistence layer
  transparent and easy to reason about for a beginner/intermediate developer; the SQL
  is right there in the repository classes.
- **LLM with an offline fallback** — the sentiment/summary logic never blocks on an
  external API key being present. This makes the project trivially demoable and
  testable without incurring API costs or requiring network access, while still using
  a real LLM integration when a key is provided.
- **Spring Cache (`ConcurrentMapCacheManager`)** — no Redis or external cache
  infrastructure needed, in line with the "avoid complex infrastructure" requirement,
  while still demonstrating the caching concept end-to-end.
- **PDFBox instead of a templating engine** — a small, dependency-light way to produce
  a real downloadable PDF artifact without extra infrastructure.
- **`@Scheduled` background job** — shows a genuine automated, unattended process
  (daily report generation) in addition to the on-demand `/api/reports/generate`
  endpoint used for live demos.

### FlyRank concept coverage

This project implements **7 of the original 7** core concepts (only 5 were required,
and 3 from the original list):

1. REST APIs
2. Database (MySQL + Spring JDBC)
3. Authentication (Spring Security + JWT)
4. Caching (Spring Cache)
5. LLM integration
6. Background job (`@Scheduled` daily report)
7. PDF reporting (Apache PDFBox)

Additional engineering practices demonstrated: Jakarta Bean Validation on every
request DTO, a global `@RestControllerAdvice` exception handler with a consistent
JSON error shape, JUnit 5 unit tests (rule-based analyzer) and MockMvc integration
tests (auth, feedback, reports) running against an in-memory H2 database, and
seed/demo data so the API is usable immediately after a fresh clone.

### What I'd add next (post-capstone)

- Pagination and filtering (`by category`, `by sentiment`, `by date range`) on the
  feedback list endpoint.
- Email or Slack notification when a report finishes generating.
- A minimal read-only dashboard (single HTML page) to view the latest report without
  needing Postman.
