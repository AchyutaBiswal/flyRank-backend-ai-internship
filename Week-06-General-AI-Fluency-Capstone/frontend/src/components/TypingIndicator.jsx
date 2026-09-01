import { Loader2 } from 'lucide-react';
import React from 'react';

function TypingIndicator() {
  return (
    <div className="message message-ai typing-message" aria-label="Achyuta AI is typing">
      <span>Achyuta AI</span>
      <p>
        <Loader2 size={16} aria-hidden="true" />
        Typing response...
      </p>
    </div>
  );
}

export default TypingIndicator;
