# Capstone Brief

## Project Title

Achyuta AI Portfolio & Career Assistant

## Problem / Opportunity

Early-career developers need a clear way to present their technical identity, projects and learning journey. A static portfolio is useful, but visitors may still need to search through sections to find specific information.

## Goal

Build a professional personal portfolio and career assistant that demonstrates General AI Fluency through a real website, a working agent interface and a secure backend architecture.

## Target Users

- Internship reviewers
- Recruiters or technical reviewers
- Visitors who want a quick summary of Achyuta Biswal's skills, project and internship context

## Solution

The project combines a responsive React portfolio with Achyuta AI, a chat assistant that answers questions from verified portfolio information. The assistant is served through an Express backend so API keys, when used, remain outside the frontend.

## Main Features

- Responsive portfolio website
- Profile photo and resume PDF support
- Skills organized by category
- Employee Task Management System project card
- FlyRank Backend AI Engineering Internship section
- Achyuta AI chat interface
- Demo AI provider for no-key local demonstrations
- Optional OpenAI-compatible provider support
- Safe error handling and input validation

## AI Assistant Purpose

Achyuta AI helps visitors ask questions about Achyuta Biswal's portfolio, including education, technical skills, AI interests, internship and the Employee Task Management System.

The assistant must not invent information. If a fact is not available in the portfolio, it should say the information is not available.

## Technology Choices

- React and Vite for a maintainable frontend
- CSS for responsive layout and styling
- Lucide React for lightweight icons
- Node.js and Express for the backend API
- dotenv for environment configuration
- Provider factory pattern for switching between demo and real providers

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

## User Flow

1. A visitor opens the portfolio.
2. The visitor reviews the sections or opens Achyuta AI.
3. The visitor asks a question or selects a suggested question.
4. The frontend sends the message to `POST /api/chat`.
5. The backend chooses the configured provider.
6. The provider returns a portfolio-grounded answer.
7. The answer appears in the chat.

## Security Considerations

- API keys are backend-only.
- `.env` files are ignored.
- `.env.example` files contain placeholders only.
- Chat input is validated.
- Request body size is limited.
- Safe error messages are returned.
- CORS is configured through environment variables.

## Demo Provider

The demo provider works without an API key and returns deterministic answers from verified portfolio data. It is intended for development and capstone demonstration only. It is not a real external LLM.

## Future Improvements

- Add production deployment
- Add final FlyRank evidence screenshots
- Add real GitHub and LinkedIn links when available
- Add a real AI provider by configuring backend environment variables
- Expand portfolio content only when verified information is available
