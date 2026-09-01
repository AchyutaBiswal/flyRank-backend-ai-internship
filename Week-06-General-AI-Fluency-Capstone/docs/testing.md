# Testing

## Frontend Checklist

- [x] Navbar renders
- [x] Hero section renders
- [x] About section renders
- [x] Skills section renders
- [x] Projects section renders
- [x] Experience section renders
- [x] Resume section renders
- [x] Achyuta AI section renders
- [x] Contact section renders
- [x] Footer renders
- [x] Navigation links work
- [x] Mobile menu opens
- [x] Profile image loads
- [x] Resume PDF opens
- [x] Desktop layout has no horizontal overflow
- [x] Tablet layout has no horizontal overflow
- [x] Mobile layout has no horizontal overflow
- [x] Focus styles are present

## AI Assistant Checklist

- [x] Suggested questions work
- [x] Manual question sending works
- [x] Enter-to-send works
- [x] Loading indicator appears
- [x] Assistant response appears
- [x] Multiple messages work
- [x] Clear chat works
- [x] Empty input shows an error
- [x] Whitespace-only input shows an error
- [x] Unknown question returns a safe fallback
- [x] Very long message returns an error

## Backend Checklist

- [x] `GET /health` returns `200 OK`
- [x] `POST /api/chat` accepts valid JSON
- [x] Empty message returns `400`
- [x] Missing message returns `400`
- [x] Invalid JSON returns `400`
- [x] Wrong method returns `405`
- [x] Message over 1000 characters returns `413`
- [x] Request body over 1MB returns `413`
- [x] Demo provider works without an API key
- [x] Unsupported provider returns `400`

## Security Checklist

- [x] `.env` is ignored in the root project
- [x] `backend/.env` is ignored
- [x] No real API keys are committed
- [x] `.env.example` files contain placeholders only
- [x] API keys remain backend-only
- [x] CORS is configured through environment variables
- [x] Backend validates input
- [x] Backend returns safe JSON error messages

## Build Command

```bash
npm run build
```

Latest Phase 6 build completed successfully.
