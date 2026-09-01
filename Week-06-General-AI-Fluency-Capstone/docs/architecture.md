# Architecture

## Overview

Achyuta AI Portfolio & Career Assistant uses a React frontend and an Express backend. The frontend renders the portfolio and chat UI. The backend receives chat messages, selects the configured AI provider and returns a response.

## Request Flow / Deployment Architecture

```text
User
 ↓
Public React/Vite Website
 ↓
VITE_API_BASE_URL
 ↓
Public Express Backend
 ↓
POST /api/chat
 ↓
Provider Factory
 ↓
Demo Provider
       OR
OpenAI-compatible Provider
 ↓
Response
 ↓
React AI Assistant
```

## Frontend

The frontend is built with React and Vite. It contains reusable components for the portfolio sections and chat interface.

Main frontend areas:

- Navbar
- Hero
- About
- Skills
- Projects
- Experience
- Resume
- Achyuta AI
- Contact
- Footer

The chat UI sends messages to the backend through `frontend/src/services/chatApi.js`.

## Backend

The backend is an Express application. It exposes:

- `GET /health`
- `POST /api/chat`

The chat route validates input before sending it to the provider layer.

## Provider Layer

Provider selection is handled by `backend/src/services/providerFactory.js`.

Supported modes:

- Demo provider: deterministic local responses from verified portfolio data
- OpenAI-compatible provider: external provider support when configured with backend environment variables

If `AI_PROVIDER=openai` is set without both `AI_API_KEY` and `AI_MODEL`, the backend uses the demo provider so the app remains runnable without paid credentials.

## Security Boundary

The API key, when used, stays on the backend. React does not receive the key and does not call the external AI provider directly.

Security controls include:

- `.env` ignored by Git
- Backend-only AI credentials
- CORS configuration
- JSON request size limit
- Chat message length limit
- Safe JSON error responses
