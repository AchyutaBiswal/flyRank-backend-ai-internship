# Capstone Project Submission

## Project
General AI Fluency — Impact Project

## Project Name
Achyuta Biswal — Personal Portfolio & AI Assistant

## Purpose

The project demonstrates a complete personal developer portfolio combined with an integrated AI career assistant (Achyuta AI). It presents Achyuta Biswal's verified professional profile, technical skills, projects, and experience while allowing portfolio visitors to interactively ask career-focused questions powered by a secure, provider-abstracted Express backend.

## Main Features

- **Responsive Personal Portfolio**: Modern single-page website supporting desktop, tablet, and mobile displays.
- **Hero Section**: Introduces Achyuta Biswal with portfolio title, tagline, and profile photo.
- **About Section**: Personal summary and background in Java Full Stack Development and Generative AI.
- **Technical Skills**: Categorized skills covering programming languages, backend frameworks, database management, frontend design, tools, and AI concepts.
- **Projects Section**: Highlights verified project experience (Employee Task Management System).
- **Experience Section**: Features current internship details (FlyRank Backend AI Engineering Internship).
- **Resume Access**: Clean link to view/download the verified resume PDF directly.
- **AI Assistant Interface**: Interactive chat interface with suggested questions, loading indicators, empty message validation, clear chat option, and fallback logic.
- **Contact Section & Footer**: Direct email link and profile references.
- **Responsive Mobile Navigation**: Accessible mobile menu with focus management and backdrop blur styling.

## AI Implementation

- **Architecture**: React frontend calls Express backend endpoint `POST /api/chat`.
- **Provider Abstraction Layer**: Built with a provider factory (`providerFactory.js`) to decouple frontend UI from specific AI providers.
- **Demo AI Provider**: Local deterministic provider answering verified portfolio questions without needing external API keys.
- **Optional Real Provider Support**: OpenAI-compatible provider integration ready for production.
- **Backend-Only Credentials**: Secrets reside exclusively in backend environment variables (`AI_API_KEY`, `AI_MODEL`) and are never exposed to the frontend.
- **No Key Required for Demo**: Demo mode runs seamlessly out-of-the-box without paid API keys.

## Featured Project

- **Project Name**: Employee Task Management System
- **Description**: "A backend REST API for managing employees, departments, and tasks."
- **Technologies**: Java, Spring Boot, Spring JDBC, MySQL, REST API

*(Note: In accordance with project brief guidelines, this section uses text descriptions and tech tags only; project screenshots are intentionally omitted.)*

## Testing Summary

- **Frontend Build**: `npm run build` executed cleanly with 0 errors (Vite production bundle generated in 2.93s).
- **Backend Health Check**: `GET /health` returned `200 OK` (`{ status: 'ok', service: 'Achyuta AI Backend' }`).
- **Chat API Verification**: `POST /api/chat` returned `200 OK` with grounded portfolio responses for all 3 standard questions:
  - *"Who is Achyuta Biswal?"*
  - *"What are Achyuta's technical skills?"*
  - *"Tell me about the Employee Task Management System."*
- **AI Assistant Interface**: Verified empty message rejection, enter-to-send, suggested question fills, clear chat, and unknown query fallback.
- **Responsive Design**: Tested desktop (1280x800) and mobile (375x812) viewports; no horizontal overflow detected.
- **Security Check**: Verified `.env` and `backend/.env` are ignored by Git. Zero API keys, secrets, or fake credentials committed. Production CORS uses explicit `CORS_ORIGIN` allowlist (no wildcard `*`).

## Deployment Preparation

Phase 8 deployment preparation is complete. The application is configured with environment variables (`VITE_API_BASE_URL` on frontend; `PORT`, `CORS_ORIGIN`, `AI_PROVIDER`, `AI_API_KEY`, `AI_MODEL` on backend) to allow independent deployment of frontend and backend. **Actual cloud deployment was intentionally not performed in Phase 8/9.**

## Evidence Screenshots

The following real screenshots were captured directly from the running local application and saved in the `screenshots/` directory:

1. `01-homepage.png` — Homepage / Hero section with tagline and profile photo.
2. `02-about.png` — About section detailing career profile.
3. `03-skills.png` — Technical Skills grid.
4. `04-projects.png` — Projects section featuring Employee Task Management System.
5. `05-experience.png` — Experience section highlighting FlyRank internship.
6. `06-resume.png` — Resume section with verified PDF action button.
7. `07-ai-assistant.png` — Achyuta AI assistant initial state with suggested questions.
8. `08-ai-conversation.png` — Achyuta AI in action returning grounded response to *"Who is Achyuta Biswal?"*.
9. `09-contact-footer.png` — Contact section and footer.
10. `10-mobile-view.png` — Responsive layout rendered at mobile viewport (375px width).
