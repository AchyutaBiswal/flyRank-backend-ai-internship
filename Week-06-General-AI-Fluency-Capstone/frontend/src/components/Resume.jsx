import { Download, FileText } from 'lucide-react';
import React from 'react';
import resumePdf from '../assets/AchyutaBiswal-JavaDeveloper-Resume.pdf';
import { profile } from '../data/portfolio.js';
import SectionHeader from './SectionHeader.jsx';

function Resume() {
  return (
    <section className="section" id="resume">
      <SectionHeader
        eyebrow="Resume"
        title="Resume"
        description="Open Achyuta's resume in a new browser tab."
      />
      <div className="resume-panel">
        <FileText aria-hidden="true" />
        <div>
          <h3>Achyuta Biswal Resume</h3>
          <p>
            View the current resume PDF for Achyuta Biswal.
          </p>
        </div>
        <a
          className="button button-primary"
          href={profile.resumeUrl || resumePdf}
          target="_blank"
          rel="noreferrer"
        >
          Open Resume <Download size={18} />
        </a>
      </div>
    </section>
  );
}

export default Resume;
