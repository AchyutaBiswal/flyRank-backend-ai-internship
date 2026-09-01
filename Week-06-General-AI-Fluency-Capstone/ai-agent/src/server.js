import cors from 'cors';
import 'dotenv/config';
import express from 'express';

const app = express();
const port = process.env.PORT || 5050;

app.use(cors());
app.use(express.json({ limit: '1mb' }));

app.get('/health', (_request, response) => {
  response.json({
    status: 'ok',
    service: 'Achyuta AI Career Agent',
    phase: 'scaffold',
  });
});

app.post('/api/chat', (_request, response) => {
  response.status(501).json({
    error: 'AI assistant endpoint is scaffolded but not implemented yet.',
  });
});

app.listen(port, () => {
  console.log(`Achyuta AI Career Agent listening on port ${port}`);
});
