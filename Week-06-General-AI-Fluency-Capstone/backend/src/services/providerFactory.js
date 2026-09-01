import { DemoPortfolioProvider, OpenAiCompatibleProvider } from './aiProvider.js';

export function createAiProvider() {
  const providerName = process.env.AI_PROVIDER || 'demo';
  const hasOpenAiConfig = Boolean(process.env.AI_API_KEY && process.env.AI_MODEL);

  if (providerName === 'demo' || (providerName === 'openai' && !hasOpenAiConfig)) {
    return new DemoPortfolioProvider();
  }

  if (providerName !== 'openai') {
    const error = new Error(`Unsupported AI provider: ${providerName}`);
    error.code = 'UNSUPPORTED_PROVIDER';
    throw error;
  }

  return new OpenAiCompatibleProvider({
    apiKey: process.env.AI_API_KEY,
    model: process.env.AI_MODEL,
  });
}
