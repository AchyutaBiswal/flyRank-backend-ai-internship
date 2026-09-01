# AI Workflow

## Development Use

AI assistance was used during development to plan the project phases, scaffold the React/Vite frontend, design the assistant UI, create the Express backend structure, write provider abstractions, improve error handling and prepare documentation.

AI assistance supported:

- Brainstorming the project structure
- Planning the portfolio sections
- Creating reusable React components
- Building the chat UI
- Designing the backend API
- Adding provider switching
- Debugging build and runtime issues
- Validating security and error handling
- Drafting documentation

## Assistant Design

Achyuta AI is designed to answer questions about verified portfolio information only. The backend system prompt includes the approved portfolio facts and explicit instructions not to invent missing information.

The assistant can answer about:

- Achyuta Biswal
- Education
- Technical skills
- Employee Task Management System
- FlyRank Backend AI Engineering Internship
- Generative AI interests and skills
- Contact information included in the portfolio

## Demo Provider

The demo provider is a development fallback that works without an API key. It is deterministic and returns predefined portfolio-grounded responses. It is not a real external LLM.

Demo mode configuration:

```env
AI_PROVIDER=demo
AI_API_KEY=
AI_MODEL=
PORT=5050
CORS_ORIGIN=http://localhost:5173
```

## Real Provider Option

The backend also supports an OpenAI-compatible provider when configured with backend-only environment variables:

```env
AI_PROVIDER=openai
AI_API_KEY=your_real_api_key_here
AI_MODEL=your_model_here
```

No real API key is stored in the repository.

## Hallucination Reduction

The project reduces hallucination risk by:

- Supplying verified portfolio facts in the system prompt
- Instructing the assistant not to invent information
- Returning a clear fallback when information is unavailable
- Keeping demo responses deterministic and portfolio-grounded
- Avoiding unsupported achievements, metrics, links or certifications

## Example Development Prompts

```text
Build a professional personal portfolio for Achyuta Biswal using only the provided information.
```

```text
Create an AI assistant that answers only from verified portfolio data and refuses unavailable information.
```

```text
Add a demo provider so the assistant works locally without an external API key.
```

## Testing

Testing covered frontend rendering, responsive behavior, chat interaction, backend validation, provider fallback behavior and security scans for secrets.
