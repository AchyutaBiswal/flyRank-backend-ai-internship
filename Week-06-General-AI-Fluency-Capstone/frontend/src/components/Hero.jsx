import { ArrowRight, Bot, Mail } from 'lucide-react';
import React from 'react';
import profilePhoto from '../assets/profile-photo.jpeg';
import { profile } from '../data/portfolio.js';

function Hero() {
  return (
    <section className="hero section" id="home">
      <div className="hero-content">
        <p className="eyebrow">Hi, I&apos;m {profile.name}</p>
        <h1>
          Java Full Stack Developer
          <span>Generative AI Engineer</span>
        </h1>
        <p className="hero-tagline">{profile.tagline}</p>
        <div className="hero-actions" aria-label="Primary actions">
          <a className="button button-primary" href="#projects">
            View Projects <ArrowRight size={18} />
          </a>
          <a className="button button-secondary" href="#ai-assistant">
            Ask My AI <Bot size={18} />
          </a>
          <a className="button button-ghost" href="#contact">
            Contact Me <Mail size={18} />
          </a>
        </div>
      </div>

      <div className="hero-panel" aria-label="Portfolio highlights">
        <div className="profile-photo-shell">
          <img
            src={profilePhoto}
            alt="Achyuta Biswal"
            className="profile-photo"
          />
        </div>
        <div className="terminal-line">
          <span className="prompt">$</span> profile --focus
        </div>
        <div className="hero-chip-grid">
          {profile.primaryRoles.map((role) => (
            <span key={role}>{role}</span>
          ))}
          <span>{profile.education}</span>
          <span>FlyRank Internship</span>
        </div>
      </div>
    </section>
  );
}

export default Hero;
