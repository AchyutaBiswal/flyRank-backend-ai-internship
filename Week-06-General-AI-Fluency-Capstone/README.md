# Achyuta AI Portfolio & Career Assistant

## Overview

Achyuta AI Portfolio & Career Assistant is a professional personal portfolio website with an integrated AI career assistant. It was created for the FlyRank General AI Fluency Impact Project capstone.

The project demonstrates a complete beginner-friendly AI stack:

- A responsive React portfolio website
- A chat-based personal assistant
- An Express backend API
- A configurable AI provider layer
- A safe demo provider that works without an API key
- Backend-only support for a real OpenAI-compatible provider

## Purpose

The purpose of this project is to present Achyuta Biswal's verified portfolio information in a professional website and allow visitors to ask career-focused questions through Achyuta AI.

Achyuta AI answers questions about:

- Achyuta Biswal
- Education
- Technical skills
- Java Full Stack Development
- Generative AI
- Employee Task Management System
- FlyRank Backend AI Engineering Internship
- Contact/profile information included in the portfolio

## FlyRank Capstone

This project supports the FlyRank General AI Fluency capstone requirement:

> Master the AI stack, build a personal brand with a real website, ship a personal agent.

## Key Features

- Professional single-page portfolio
- Responsive desktop, tablet and mobile layout
- Sticky navigation with mobile menu
- Hero section with profile photo
- About, Skills, Projects, Experience, Resume, AI Assistant and Contact sections
- Real resume PDF connected from project assets
- Achyuta AI chat interface
- Suggested portfolio questions
- Loading, empty and error states
- Express `/api/chat` endpoint
- Demo AI provider for local use without an API key
- Optional OpenAI-compatible provider support
- Backend input validation and safe error responses

## Technology Stack

Frontend:

- React
- Vite
- JavaScript
- CSS
- Lucide React

Backend:

- Node.js
- Express
- CORS
- dotenv

AI:

- Provider factory abstraction
- Demo portfolio provider
- Optional OpenAI-compatible chat completions provider

## Architecture

```text
User
 ↓
React Portfolio
 ↓
Achyuta AI Chat
 ↓
POST /api/chat
 ↓
Express Backend
 ↓
Provider Factory
 ↓
Demo Provider / OpenAI-compatible Provider
 ↓
Response
 ↓
React Chat
```

The API key, when used, stays on the backend. The React frontend only calls the backend API and never receives or stores private AI credentials.

## Project Structure

```text
General-AI-Fluency-Capstone/
├── backend/
│   ├── src/
│   │   ├── prompts/
│   │   ├── routes/
│   │   ├── services/
│   │   └── server.js
│   ├── .env.example
│   ├── .gitignore
│   ├── package.json
│   └── README.md
├── frontend/
│   ├── public/
│   ├── src/
│   │   ├── assets/
│   │   ├── components/
│   │   ├── data/
│   │   ├── pages/
│   │   ├── services/
│   │   ├── App.jsx
│   │   └── main.jsx
│   ├── package.json
│   └── README.md
├── ai-agent/
├── docs/
├── screenshots/
├── .env.example
├── .gitignore
├── CAPSTONE_BRIEF.md
├── package.json
└── README.md
```

## Local Setup

Install dependencies from the project root:

```bash
npm install
```

Or install inside each app folder:

```bash
cd frontend
npm install
```

```bash
cd backend
npm install
```

## Environment Variables

Create `backend/.env` for local backend configuration. Do not commit it.

Demo mode:

```env
AI_PROVIDER=demo
AI_API_KEY=
AI_MODEL=
PORT=5050
CORS_ORIGIN=http://localhost:5173
```

Demo mode does not require an API key. It is a development/demo fallback, not a real external LLM.

Optional real provider mode:

```env
AI_PROVIDER=openai
AI_API_KEY=your_real_api_key_here
AI_MODEL=your_model_here
PORT=5050
CORS_ORIGIN=http://localhost:5173
```

Use placeholders only in committed files. Never commit real API keys.

## Running Locally

Backend:

```bash
npm run dev --workspace backend
```

Frontend:

```bash
npm run dev --workspace frontend
```

The frontend expects the backend at:

```text
http://localhost:5050
```

The frontend environment variable is:

```env
VITE_API_BASE_URL=http://localhost:5050
```

## API Endpoint

Health check:

```text
GET /health
```

Chat:

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

## Testing

Run the frontend production build:

```bash
npm run build
```

Phase 6 validation covered:

- Frontend sections and navigation
- Responsive desktop, tablet and mobile layout
- Profile image loading
- Resume PDF link
- Achyuta AI chat behavior
- Backend health and chat routes
- Empty, missing, invalid, long and malformed requests
- Demo provider responses
- Security scans for secrets

## Security

- `.env` is ignored by Git
- Backend `.env` is ignored by Git
- API keys remain backend-only
- No secrets are stored in React source
- Frontend calls only `POST /api/chat`
- Backend validates input
- Backend limits JSON body size to `1mb`
- Chat messages are limited to 1000 characters
- Backend returns safe JSON error messages
- CORS is configured through `CORS_ORIGIN`

## Deployment Preparation

The project is prepared for independent frontend and backend deployment, but **no actual cloud deployment has taken place in Phase 8**.

### Architecture & Configuration Summary

- **Frontend Environment Variable**: `VITE_API_BASE_URL` (points to the public backend endpoint URL).
- **Backend Environment Variables**:
  - `PORT`: Server port (e.g., 5050).
  - `CORS_ORIGIN`: Comma-separated allowlist of allowed frontend origins (e.g., `http://localhost:5173,https://your-production-frontend.example`). Wildcard `*` must NOT be used for production CORS.
  - `AI_PROVIDER`: `demo` or `openai`.
  - `AI_API_KEY`: API key for external LLM (backend-only, empty for demo mode).
  - `AI_MODEL`: Model name (backend-only).

### Key Security & Architecture Principles

- **Demo Mode**: Requires no API key and operates deterministically on verified portfolio data.
- **Backend-Only Secrets**: Any real provider API keys reside strictly in backend environment variables and are never bundled into or accessible by the React frontend.
- **Independent Deployment**: The React frontend (e.g., Vercel / Netlify / Static Hosting) and Express backend (e.g., Render / Railway / Node host) can be deployed separately.
- **CORS Allowlist**: The production frontend URL must be explicitly listed in `CORS_ORIGIN` on the backend.

## Limitations

- Demo provider is deterministic and portfolio-grounded; it is not a real external AI model.
- Real AI provider usage requires a valid backend API key and model.
- GitHub and LinkedIn URLs are intentionally empty until real links are provided.
- No project screenshots are used for the Employee Task Management System.
- Real evidence screenshots and submission documentation are saved in the `screenshots/` directory.
