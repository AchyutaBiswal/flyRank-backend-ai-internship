import { Router } from 'express';
import { createAiProvider } from '../services/providerFactory.js';

const router = Router();
const maxMessageLength = 1000;

router.post('/', async (request, response) => {
  const { message } = request.body ?? {};

  if (typeof message !== 'string') {
    return response.status(400).json({
      error: 'Message is required and must be a string.',
    });
  }

  const trimmedMessage = message.trim();

  if (!trimmedMessage) {
    return response.status(400).json({
      error: 'Message cannot be empty.',
    });
  }

  if (trimmedMessage.length > maxMessageLength) {
    return response.status(413).json({
      error: `Message must be ${maxMessageLength} characters or fewer.`,
    });
  }

  try {
    const aiProvider = createAiProvider();
    const assistantMessage = await aiProvider.generateResponse(trimmedMessage);

    return response.json({
      message: assistantMessage,
    });
  } catch (error) {
    if (error.code === 'MISSING_AI_CONFIG') {
      return response.status(503).json({
        error:
          'OpenAI-compatible provider is not configured. Set AI_API_KEY and AI_MODEL, or use AI_PROVIDER=demo for local development.',
      });
    }

    if (error.code === 'UNSUPPORTED_PROVIDER') {
      return response.status(400).json({
        error: error.message,
      });
    }

    console.error('AI provider request failed.');

    return response.status(502).json({
      error: 'AI provider request failed. Please try again later.',
    });
  }
});

router.all('/', (_request, response) =>
  response.status(405).json({
    error: 'Method not allowed. Use POST /api/chat.',
  }),
);

export default router;
