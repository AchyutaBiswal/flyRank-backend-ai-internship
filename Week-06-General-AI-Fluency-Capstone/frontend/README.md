# Frontend

React/Vite frontend for Achyuta AI Portfolio & Career Assistant.

## Purpose

The frontend renders the portfolio website and the Achyuta AI chat interface. It does not store API keys and does not call external AI providers directly.

## Installation

From the project root:

```bash
npm install
```

Or from this folder:

```bash
cd frontend
npm install
```

## Development

From the project root:

```bash
npm run dev --workspace frontend
```

The Vite development server normally runs at:

```text
http://localhost:5173
```

## Build

From the project root:

```bash
npm run build
```

Or:

```bash
npm run build --workspace frontend
```

## Frontend / Backend Connection

The chat UI calls the backend API:

```text
POST /api/chat
```

Configure the backend URL with:

```env
VITE_API_BASE_URL=http://localhost:5050
```

The frontend does not know whether the backend is using the demo provider or a real OpenAI-compatible provider.

## Asset Locations

Profile photo:

```text
frontend/src/assets/profile-photo.jpeg
```

Resume PDF:

```text
frontend/src/assets/AchyutaBiswal-JavaDeveloper-Resume.pdf
```

The Employee Task Management System section intentionally uses only text and technology tags. It does not use project images.

## Configuration

Portfolio content is stored in:

```text
frontend/src/data/portfolio.js
```

GitHub and LinkedIn URLs are intentionally empty until real URLs are provided.

## Security

- No AI API keys are used in frontend code
- The frontend only sends user messages to the backend
- Secrets must stay in backend environment variables

## Deployment Preparation Notes

- Set `VITE_API_BASE_URL` in the frontend hosting environment (e.g. Vercel, Netlify, Cloudflare Pages) to point to the backend URL.
- The frontend static build contains no provider secrets or credentials.
- Actual cloud deployment has not been performed in Phase 8.

