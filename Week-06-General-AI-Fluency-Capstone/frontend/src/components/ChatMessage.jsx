import React from 'react';

function ChatMessage({ message }) {
  const isAssistant = message.sender === 'ai';

  return (
    <div className={`message ${isAssistant ? 'message-ai' : 'message-user'}`}>
      <span>{isAssistant ? 'Achyuta AI' : 'You'}</span>
      <p>{message.text}</p>
    </div>
  );
}

export default ChatMessage;
