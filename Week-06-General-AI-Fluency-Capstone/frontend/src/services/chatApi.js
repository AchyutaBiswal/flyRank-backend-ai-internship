const defaultApiBaseUrl = 'http://localhost:5050';

export async function sendChatMessage(message) {
  const apiBaseUrl = import.meta.env.VITE_API_BASE_URL || defaultApiBaseUrl;
  const response = await fetch(`${apiBaseUrl}/api/chat`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ message }),
  });

  const data = await response.json().catch(() => ({}));

  if (!response.ok) {
    throw new Error(data.error || 'Unable to get an assistant response.');
  }

  if (!data.message) {
    throw new Error('Assistant response was empty.');
  }

  return data.message;
}
