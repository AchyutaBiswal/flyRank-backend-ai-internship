# Development Process

## Phase-Based Build

The project was developed in controlled phases:

1. Project scaffold
2. Responsive portfolio UI
3. Personal assets
4. Static AI assistant UI
5. Backend AI integration
6. Testing, validation and error handling
7. Documentation
8. Deployment preparation

Phase 8 deployment preparation is complete (actual cloud deployment was not performed). Phase 9 final submission preparation has not been started.

## Implementation Approach

The frontend was built with reusable React components and shared portfolio data. The backend was added as a separate Express workspace with a chat route, provider factory and provider service layer.

The implementation avoids hard-coded secrets and keeps AI provider credentials on the backend only.

## Key Decisions

- Use React/Vite for a maintainable frontend
- Use Express for a simple backend API
- Keep portfolio content in a central data file
- Use a provider factory so AI providers can be switched later
- Add a demo provider so the project can be demonstrated without an API key
- Use the existing real profile photo and resume PDF
- Keep GitHub and LinkedIn URLs empty until real URLs are provided
- Avoid project images for the Employee Task Management System

## Validation Work

Validation included:

- Frontend build checks
- Backend startup checks
- API route testing
- Browser testing for desktop, tablet and mobile
- Resume PDF verification
- Image loading checks
- Secret scanning
- Error-state testing

## Known Scope Boundaries

The project does not include deployment, final screenshot evidence or final FlyRank submission packaging yet. Those belong to later phases.
