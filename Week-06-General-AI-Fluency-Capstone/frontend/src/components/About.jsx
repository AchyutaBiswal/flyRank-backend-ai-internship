import { GraduationCap, Layers, Sparkles } from 'lucide-react';
import React from 'react';
import profilePhoto from '../assets/profile-photo.jpeg';
import { profile } from '../data/portfolio.js';
import SectionHeader from './SectionHeader.jsx';

function About() {
  return (
    <section className="section" id="about">
      <SectionHeader
        eyebrow="About"
        title="A developer building practical intelligent software"
        description="Achyuta is focused on Java full stack development, backend APIs and Generative AI."
      />
      <div className="about-intro">
        <img
          src={profilePhoto}
          alt="Achyuta Biswal"
          className="about-photo"
        />
        <p>
          Achyuta Biswal is a Java Full Stack Developer and Generative AI
          Engineer focused on practical backend systems and AI-assisted
          development.
        </p>
      </div>
      <div className="about-grid">
        <article className="feature-card">
          <GraduationCap aria-hidden="true" />
          <h3>Education</h3>
          <p>{profile.education}</p>
        </article>
        <article className="feature-card">
          <Layers aria-hidden="true" />
          <h3>Backend Focus</h3>
          <p>
            Experience building backend REST API projects with Java, Spring Boot,
            Spring JDBC and MySQL.
          </p>
        </article>
        <article className="feature-card">
          <Sparkles aria-hidden="true" />
          <h3>AI Interest</h3>
          <p>
            Interested in Generative AI, prompt engineering and AI-assisted
            development as part of a growing engineering toolkit.
          </p>
        </article>
      </div>
    </section>
  );
}

export default About;
