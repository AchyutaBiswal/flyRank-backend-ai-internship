import { AlertCircle, Bot, RotateCcw, Send } from 'lucide-react';
import React from 'react';
import { useEffect, useRef, useState } from 'react';
import { suggestedQuestions } from '../data/portfolio.js';
import { sendChatMessage } from '../services/chatApi.js';
import ChatMessage from './ChatMessage.jsx';
import SectionHeader from './SectionHeader.jsx';
import TypingIndicator from './TypingIndicator.jsx';

const minimumLoadingTimeMs = 350;

function AiAssistant() {
  const [messages, setMessages] = useState([]);
  const [inputValue, setInputValue] = useState('');
  const [isTyping, setIsTyping] = useState(false);
  const [error, setError] = useState('');
  const chatBodyRef = useRef(null);

  useEffect(() => {
    chatBodyRef.current?.scrollTo({
      top: chatBodyRef.current.scrollHeight,
      behavior: 'smooth',
    });
  }, [messages, isTyping]);

  const sendMessage = async (question) => {
    if (isTyping) {
      return;
    }

    const trimmedQuestion = question.trim();

    if (!trimmedQuestion) {
      setError('Please enter a question before sending.');
      return;
    }

    setError('');
    setMessages((currentMessages) => [
      ...currentMessages,
      {
        id: `user-${Date.now()}`,
        sender: 'user',
        text: trimmedQuestion,
      },
    ]);
    setInputValue('');
    setIsTyping(true);

    try {
      const [assistantResponse] = await Promise.all([
        sendChatMessage(trimmedQuestion),
        new Promise((resolve) => {
          window.setTimeout(resolve, minimumLoadingTimeMs);
        }),
      ]);
      setMessages((currentMessages) => [
        ...currentMessages,
        {
          id: `ai-${Date.now()}`,
          sender: 'ai',
          text: assistantResponse,
        },
      ]);
    } catch (requestError) {
      setError(requestError.message);
    } finally {
      setIsTyping(false);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    sendMessage(inputValue);
  };

  const handleInputKeyDown = (event) => {
    if (event.key === 'Enter' && !event.shiftKey) {
      event.preventDefault();
      sendMessage(inputValue);
    }
  };

  const handleClear = () => {
    setMessages([]);
    setInputValue('');
    setError('');
    setIsTyping(false);
  };

  return (
    <section className="section section-muted" id="ai-assistant">
      <SectionHeader
        eyebrow="AI Assistant"
        title="Achyuta AI"
        description="Ask Achyuta AI about verified portfolio information."
      />
      <div className="assistant-layout">
        <aside className="suggestions" aria-label="Suggested questions">
          <h3>Suggested questions</h3>
          {suggestedQuestions.map((question) => (
            <button
              className="suggestion-button"
              type="button"
              key={question}
              onClick={() => sendMessage(question)}
              disabled={isTyping}
            >
              {question}
            </button>
          ))}
        </aside>

        <article className="chat-card" aria-label="Achyuta AI chat">
          <div className="chat-header">
            <div>
              <span className="assistant-avatar">
                <Bot size={20} aria-hidden="true" />
              </span>
              <div>
                <h3>Achyuta AI</h3>
                <p>Portfolio career assistant</p>
              </div>
            </div>
            <button
              className="icon-button"
              type="button"
              aria-label="Clear chat"
              onClick={handleClear}
            >
              <RotateCcw size={18} />
            </button>
          </div>

          {error ? (
            <div className="chat-error" role="alert">
              <AlertCircle size={18} aria-hidden="true" />
              <span>{error}</span>
            </div>
          ) : null}

          <div className="chat-body" ref={chatBodyRef} aria-live="polite">
            {messages.length === 0 ? (
              <div className="empty-state">
                <Bot size={28} aria-hidden="true" />
                <h3>Welcome to Achyuta AI</h3>
                <p>Ask about Achyuta&apos;s skills, project, internship or AI interests.</p>
              </div>
            ) : (
              messages.map((message) => (
                <ChatMessage message={message} key={message.id} />
              ))
            )}
            {isTyping ? <TypingIndicator /> : null}
          </div>

          <form className="chat-input-row" aria-label="Ask Achyuta AI" onSubmit={handleSubmit}>
            <label className="sr-only" htmlFor="assistant-message">
              Ask something
            </label>
            <input
              id="assistant-message"
              type="text"
              placeholder="Ask something..."
              value={inputValue}
              onChange={(event) => setInputValue(event.target.value)}
              onKeyDown={handleInputKeyDown}
              disabled={isTyping}
            />
            <button className="button button-primary" type="submit" disabled={isTyping}>
              Send <Send size={17} />
            </button>
          </form>
        </article>
      </div>
    </section>
  );
}

export default AiAssistant;
