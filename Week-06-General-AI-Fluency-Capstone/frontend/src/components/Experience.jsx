import { BriefcaseBusiness } from 'lucide-react';
import React from 'react';
import { profile } from '../data/portfolio.js';
import SectionHeader from './SectionHeader.jsx';

function Experience() {
  return (
    <section className="section section-muted" id="experience">
      <SectionHeader
        eyebrow="Experience"
        title="Current learning experience"
        description="A concise view of Achyuta's current internship and capstone work."
      />
      <article className="timeline-card">
        <div className="timeline-icon">
          <BriefcaseBusiness aria-hidden="true" />
        </div>
        <div>
          <h3>{profile.internship}</h3>
          <p>
            Achyuta is currently completing the FlyRank Backend AI Engineering
            Internship and this General AI Fluency capstone as part of the
            internship learning journey.
          </p>
        </div>
      </article>
    </section>
  );
}

export default Experience;
