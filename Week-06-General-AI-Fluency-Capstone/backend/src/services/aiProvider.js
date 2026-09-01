import { systemPrompt } from '../prompts/systemPrompt.js';

const openAiChatCompletionsUrl =
  process.env.AI_BASE_URL || 'https://api.openai.com/v1/chat/completions';

const fallbackResponse =
  'That information is not available in the portfolio. Achyuta AI can only answer using verified portfolio information.';

const demoResponses = [
  {
    keywords: ['who', 'achyuta'],
    response:
      'Achyuta Biswal is a Java Full Stack Developer and Generative AI Engineer with a B.Tech in Computer Science & Engineering. His portfolio tagline is Building Intelligent Software.',
  },
  {
    keywords: ['technical', 'skills'],
    response:
      'Achyuta has technical skills in Java, JavaScript, SQL, Spring, Spring Boot, REST APIs, Spring JDBC, MySQL, HTML, CSS, Git, GitHub, Maven, Postman, Generative AI, Prompt Engineering and AI-assisted Development.',
  },
  {
    keywords: ['programming'],
    response: 'Achyuta knows Java, JavaScript and SQL.',
  },
  {
    keywords: ['technologies', 'project'],
    response:
      'The Employee Task Management System uses Java, Spring Boot, Spring JDBC, MySQL and REST API.',
  },
  {
    keywords: ['technologies'],
    response:
      'Achyuta uses Java, JavaScript, SQL, Spring, Spring Boot, REST APIs, Spring JDBC, MySQL, HTML, CSS, Git, GitHub, Maven, Postman, Generative AI, Prompt Engineering and AI-assisted Development.',
  },
  {
    keywords: ['employee', 'task', 'management'],
    response:
      'Employee Task Management System is a backend REST API for managing employees, departments, and tasks. It uses Java, Spring Boot, Spring JDBC, MySQL and REST API.',
  },
  {
    keywords: ['project'],
    response:
      'Achyuta has worked on the Employee Task Management System, a backend REST API for managing employees, departments, and tasks.',
  },
  {
    keywords: ['spring'],
    response: 'Achyuta uses Spring, Spring Boot, REST APIs and Spring JDBC for backend development.',
  },
  {
    keywords: ['internship'],
    response:
      'Achyuta is currently completing the FlyRank Backend AI Engineering Internship and this General AI Fluency capstone as part of the FlyRank internship.',
  },
  {
    keywords: ['ai', 'skills'],
    response:
      'Achyuta has AI skills in Generative AI, Prompt Engineering and AI-assisted Development.',
  },
  {
    keywords: ['education'],
    response: 'Achyuta has a B.Tech in Computer Science & Engineering.',
  },
  {
    keywords: ['email', 'contact'],
    response: 'Achyuta can be contacted at achyutabiswal977@gmail.com.',
  },
];

export class DemoPortfolioProvider {
  async generateResponse(userMessage) {
    const normalizedMessage = userMessage.toLowerCase();
    const matchedResponse = demoResponses.find(({ keywords }) =>
      keywords.every((keyword) => normalizedMessage.includes(keyword)),
    );

    return matchedResponse?.response || fallbackResponse;
  }
}

export class OpenAiCompatibleProvider {
  constructor({ apiKey, model }) {
    this.apiKey = apiKey;
    this.model = model;
  }

  validateConfig() {
    if (!this.apiKey || !this.model) {
      const error = new Error('Missing AI provider configuration.');
      error.code = 'MISSING_AI_CONFIG';
      throw error;
    }
  }

  async generateResponse(userMessage) {
    this.validateConfig();

    const providerResponse = await fetch(openAiChatCompletionsUrl, {
      method: 'POST',
      headers: {
        Authorization: `Bearer ${this.apiKey}`,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        model: this.model,
        temperature: 0.2,
        messages: [
          {
            role: 'system',
            content: systemPrompt,
          },
          {
            role: 'user',
            content: userMessage,
          },
        ],
      }),
    });

    if (!providerResponse.ok) {
      throw new Error('AI provider returned an error response.');
    }

    const data = await providerResponse.json();
    const assistantMessage = data?.choices?.[0]?.message?.content?.trim();

    if (!assistantMessage) {
      throw new Error('AI provider returned an empty response.');
    }

    return assistantMessage;
  }
}
