# FlyRank Week 8 Capstone — Customer Feedback Intelligence Platform

A Spring Boot backend that lets a team collect customer feedback, get it auto-analyzed
for sentiment by an LLM, and generate PDF analytics reports — with JWT authentication,
caching, validation, and a scheduled background job.

## Concepts covered (7 of the original 7)

| # | Concept | Where |
|---|---------|-------|
| 1 | REST APIs | `controller/` — Auth, Feedback, Report controllers |
| 2 | Database (MySQL + Spring JDBC) | `repository/` (`JdbcTemplate`), `schema.sql`, `data.sql` |
| 3 | Authentication (Spring Security + JWT) | `security/`, `config/SecurityConfig.java` |
| 4 | Caching (Spring Cache) | `LlmAnalysisService.analyze()` cached by feedback id |
| 5 | LLM integration | `LlmAnalysisService.java` (OpenAI-compatible API, with offline fallback) |
| 6 | Background job (`@Scheduled`) | `ReportSchedulerService.java` |
| 7 | PDF reporting | `PdfReportService.java` (Apache PDFBox) |

Plus: Jakarta Bean Validation, a global `@RestControllerAdvice` exception handler,
JUnit 5 unit + MockMvc integration tests, and seed/demo data.

## Tech stack

Java 17 · Spring Boot 3.2 · Spring Web · Spring Security · JWT (jjwt) · Spring JDBC
(`JdbcTemplate`, no JPA) · MySQL · Spring Cache · Jakarta Validation · Apache PDFBox ·
Maven · JUnit 5 · H2 (test profile only)

## Project structure

```
flyrank-10x-solution/
├── src/main/java/com/flyrank/feedback/
│   ├── config/        SecurityConfig, CacheConfig, SchedulingConfig, RestTemplateConfig,
│   │                  LlmProperties, ReportProperties
│   ├── security/       JWT filter, JwtService, UserDetailsService, principal
│   ├── controller/     AuthController, FeedbackController, ReportController
│   ├── dto/            Request/response payloads (validated)
│   ├── model/           User, Feedback, FeedbackAnalysis, Report
│   ├── repository/      JdbcTemplate-based repositories
│   ├── service/         AuthService, FeedbackService, LlmAnalysisService,
│   │                    PdfReportService, ReportSchedulerService
│   └── exception/       GlobalExceptionHandler + custom exceptions
├── src/main/resources/  application.yml, schema.sql, data.sql
├── src/test/            Unit test (LLM fallback) + MockMvc integration tests (H2)
├── database/            Copy of schema.sql / seed-data.sql for quick reference
├── postman/             Postman collection
├── reports/             Generated PDF reports are written here at runtime
├── .env.example
├── .gitignore
├── pom.xml
└── My 10x Solution - Achyuta Biswal.md
```

## How it works

1. A user **registers/logs in** and receives a JWT (`/api/auth/register`, `/api/auth/login`).
2. An authenticated user **submits feedback** (`POST /api/feedback`) — validated with
   Jakarta Bean Validation (rating 1–5, required fields, length limits).
3. Calling `POST /api/feedback/{id}/analyze` sends the feedback text to an
   **LLM** (any OpenAI-compatible chat completions endpoint) to get a sentiment label,
   a one-line summary, and keywords. The result is **cached** per feedback id
   (Spring Cache), so repeated calls don't re-invoke the LLM. If no `LLM_API_KEY`
   is configured, a deterministic **rule-based fallback analyzer** is used automatically,
   so the app works fully offline for local development and grading.
4. `POST /api/reports/generate` (and a daily **`@Scheduled` background job**, default
   `01:00`) aggregates all feedback into a **PDF report** (Apache PDFBox) with total
   count, average rating, and a sentiment breakdown, stored under `./reports` and
   downloadable via `GET /api/reports/{id}/download`.
5. All errors (validation, not-found, duplicate email, bad credentials, etc.) go through
   a **global exception handler** and return a consistent JSON error shape.

## Prerequisites

- Java 17+
- Maven 3.8+
- MySQL 8.x running locally (or reachable via `DB_URL`)

## Setup

1. Create a MySQL database (or let the app create it automatically — the JDBC URL
   uses `createDatabaseIfNotExist=true`):
   ```sql
   CREATE DATABASE flyrank_feedback;
   ```
2. Copy the environment template and fill in your own local values:
   ```bash
   cp .env.example .env
   ```
   Then either export those variables in your shell, or just set the same values as
   environment variables / IDE run configuration (Spring Boot reads them via
   `${DB_URL}`, `${DB_USERNAME}`, etc. in `application.yml`, with safe local defaults
   if unset).
3. Build and run:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   The app starts on `http://localhost:8080`. On first run, `schema.sql` creates the
   tables and `data.sql` inserts demo/seed data automatically.

## Demo/seed accounts

| Email | Password | Role |
|-------|----------|------|
| `admin@flyrank.com` | `admin123` | ADMIN (sees all users' feedback/reports) |
| `john@flyrank.com`  | `password123` | USER (sees only their own feedback) |

5 sample feedback entries are pre-loaded for `john@flyrank.com`.

## Running tests

```bash
mvn test
```

Tests run against an in-memory H2 database (`test` profile) and a mocked/disabled LLM
call (fallback analyzer), so they run without any external dependency, MySQL, or API key.

## API summary

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | `/api/auth/register` | — | Create a new user account |
| POST | `/api/auth/login` | — | Get a JWT access token |
| POST | `/api/feedback` | JWT | Submit feedback |
| GET | `/api/feedback` | JWT | List feedback (own, or all if ADMIN) |
| GET | `/api/feedback/{id}` | JWT | Get one feedback item |
| POST | `/api/feedback/{id}/analyze` | JWT | Run/get cached LLM sentiment analysis |
| POST | `/api/reports/generate` | JWT | Generate a PDF analytics report now |
| GET | `/api/reports` | JWT | List generated reports |
| GET | `/api/reports/{id}/download` | JWT | Download a report PDF |

A ready-to-import Postman collection is in `postman/FlyRank-Feedback-Platform.postman_collection.json`.

## Configuration reference (`.env.example`)

| Variable | Purpose | Default |
|----------|---------|---------|
| `SERVER_PORT` | HTTP port | `8080` |
| `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` | MySQL connection | local MySQL on `3306` |
| `JWT_SECRET`, `JWT_EXPIRATION_MS` | JWT signing key & token lifetime | dev-only default / `86400000` (24h) |
| `LLM_API_URL`, `LLM_API_KEY`, `LLM_MODEL`, `LLM_ENABLED` | LLM integration | OpenAI-compatible endpoint; empty key ⇒ offline fallback |
| `REPORTS_DIR`, `REPORTS_CRON` | Background report job | `./reports`, daily at 01:00 |

No real secrets are committed anywhere in this repository — `.env.example` contains
placeholders only, and `.env` is git-ignored.

## Notes on scope

This project intentionally avoids Docker, Kubernetes, microservices, and a complex
frontend, per the FlyRank Week 8 scope — it is a single Spring Boot module meant to be
run with `mvn spring-boot:run` and exercised via Postman/curl.
