import { Github, Linkedin, Mail } from 'lucide-react';
import React from 'react';
import { profile, profileLinks } from '../data/portfolio.js';
import SectionHeader from './SectionHeader.jsx';

function Contact() {
  return (
    <section className="section" id="contact">
      <SectionHeader
        eyebrow="Contact"
        title="Get in touch"
        description="Use the available contact information below. Social profile URLs can be added later."
      />
      <div className="contact-grid">
        <a className="contact-card" href={`mailto:${profile.email}`}>
          <Mail aria-hidden="true" />
          <span>Email</span>
          <strong>{profile.email}</strong>
        </a>
        {profileLinks.github ? (
          <a className="contact-card" href={profileLinks.github}>
            <Github aria-hidden="true" />
            <span>GitHub</span>
            <strong>Open profile</strong>
          </a>
        ) : (
          <button className="contact-card contact-card-disabled" type="button" disabled>
            <Github aria-hidden="true" />
            <span>GitHub</span>
            <strong>URL can be added later</strong>
          </button>
        )}
        {profileLinks.linkedin ? (
          <a className="contact-card" href={profileLinks.linkedin}>
            <Linkedin aria-hidden="true" />
            <span>LinkedIn</span>
            <strong>Open profile</strong>
          </a>
        ) : (
          <button className="contact-card contact-card-disabled" type="button" disabled>
            <Linkedin aria-hidden="true" />
            <span>LinkedIn</span>
            <strong>URL can be added later</strong>
          </button>
        )}
      </div>
    </section>
  );
}

export default Contact;
