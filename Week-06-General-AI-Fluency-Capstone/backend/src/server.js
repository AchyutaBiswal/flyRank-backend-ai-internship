import cors from 'cors';
import 'dotenv/config';
import express from 'express';
import chatRouter from './routes/chat.js';

const app = express();
const port = process.env.PORT || 5050;
const allowedOrigins = (process.env.CORS_ORIGIN || 'http://localhost:5173')
  .split(',')
  .map((origin) => origin.trim())
  .filter(Boolean);

app.use(
  cors({
    origin(origin, callback) {
      if (!origin || allowedOrigins.includes(origin)) {
        return callback(null, true);
      }

      const error = new Error('Origin is not allowed by CORS.');
      error.code = 'CORS_NOT_ALLOWED';
      return callback(error);
    },
    methods: ['GET', 'POST'],
  }),
);
app.use(express.json({ limit: '1mb' }));

app.get('/health', (_request, response) => {
  response.json({
    status: 'ok',
    service: 'Achyuta AI Backend',
  });
});

app.use('/api/chat', chatRouter);

app.use((error, _request, response, _next) => {
  if (error.code === 'CORS_NOT_ALLOWED') {
    return response.status(403).json({
      error: 'Origin is not allowed by CORS.',
    });
  }

  if (error.type === 'entity.too.large') {
    return response.status(413).json({
      error: 'Request body is too large.',
    });
  }

  if (error instanceof SyntaxError) {
    return response.status(400).json({
      error: 'Invalid JSON request body.',
    });
  }

  return response.status(500).json({
    error: 'Unexpected server error.',
  });
});

app.listen(port, () => {
  console.log(`Achyuta AI Backend listening on port ${port}`);
});
