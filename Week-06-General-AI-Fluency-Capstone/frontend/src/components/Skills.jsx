import { Code2 } from 'lucide-react';
import React from 'react';
import { skills } from '../data/portfolio.js';
import SectionHeader from './SectionHeader.jsx';

function Skills() {
  return (
    <section className="section section-muted" id="skills">
      <SectionHeader
        eyebrow="Skills"
        title="Technical stack"
        description="Organized around backend engineering, frontend fundamentals and AI fluency."
      />
      <div className="skills-grid">
        {skills.map((group) => (
          <article className="skill-card" key={group.category}>
            <div className="card-title-row">
              <Code2 aria-hidden="true" />
              <h3>{group.category}</h3>
            </div>
            <div className="tag-list">
              {group.items.map((item) => (
                <span className="tag" key={item}>
                  {item}
                </span>
              ))}
            </div>
          </article>
        ))}
      </div>
    </section>
  );
}

export default Skills;
