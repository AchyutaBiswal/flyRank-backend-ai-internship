# Backend

Express backend for Achyuta AI, the portfolio career assistant.

## Purpose

The backend provides a secure API layer between the React frontend and the configured AI provider. It keeps AI credentials on the server side and returns portfolio-grounded assistant responses to the frontend.

## Requirements

- Node.js 18 or newer
- npm

## Installation

From the project root:

```bash
npm install
```

Or from this folder:

```bash
cd backend
npm install
```

## Environment Variables

Create a local `backend/.env` file. Do not commit it.

Demo mode:

```env
PORT=5050
CORS_ORIGIN=http://localhost:5173
AI_PROVIDER=demo
AI_API_KEY=
AI_MODEL=
```

Demo mode does not require an API key. It is a development/demo fallback that returns deterministic answers from verified portfolio information. It is not a real external LLM.

OpenAI-compatible mode:

```env
PORT=5050
CORS_ORIGIN=http://localhost:5173
AI_PROVIDER=openai
AI_API_KEY=your_real_api_key_here
AI_MODEL=your_model_here
```

If `AI_PROVIDER=openai` is used without both `AI_API_KEY` and `AI_MODEL`, the backend falls back to demo mode.

## Running The Server

From the project root:

```bash
npm run dev --workspace backend
```

Production-style start:

```bash
npm run start --workspace backend
```

## Health Endpoint

```text
GET /health
```

Expected response:

```json
{
  "status": "ok",
  "service": "Achyuta AI Backend"
}
```

## Chat Endpoint

```text
POST /api/chat
```

Request:

```json
{
  "message": "Who is Achyuta Biswal?"
}
```

Response:

```json
{
  "message": "Achyuta AI response"
}
```

## Error Responses

- `400`: missing message, non-string message, empty message or invalid JSON
- `405`: wrong HTTP method for `/api/chat`
- `413`: message over 1000 characters or request body over 1MB
- `400`: unsupported provider
- `502`: external provider request failure

Errors are returned as JSON:

```json
{
  "error": "Safe error message"
}
```

## Provider Switching

Provider selection is handled by `src/services/providerFactory.js`.

- `AI_PROVIDER=demo`: use the local demo provider
- `AI_PROVIDER=openai` with valid `AI_API_KEY` and `AI_MODEL`: use the OpenAI-compatible provider
- `AI_PROVIDER=openai` without complete config: automatically use demo mode

## Security Notes

- Do not commit `backend/.env`
- Do not place API keys in frontend code
- Do not log API keys
- CORS is configured through `CORS_ORIGIN`
- JSON body size is limited to `1mb`
- Chat messages are limited to 1000 characters
- The system prompt instructs the assistant to use only verified portfolio information

## Deployment Preparation Notes

- Configure environment variables (`PORT`, `CORS_ORIGIN`, `AI_PROVIDER`, `AI_API_KEY`, `AI_MODEL`) on the Node host (e.g. Render, Railway, AWS).
- Set `CORS_ORIGIN` to the production frontend URL (e.g., `https://your-production-frontend.example`). Do NOT use `*` in production.
- Demo mode requires no API key. Real API keys stay strictly on the backend host environment.
- Actual cloud deployment has not been performed in Phase 8.

