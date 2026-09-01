import { ExternalLink, FolderKanban } from 'lucide-react';
import React from 'react';
import { projects } from '../data/portfolio.js';
import SectionHeader from './SectionHeader.jsx';

function Projects() {
  return (
    <section className="section" id="projects">
      <SectionHeader
        eyebrow="Projects"
        title="Selected project work"
        description="Focused project work using Java backend technologies and database-backed APIs."
      />
      <div className="project-grid">
        {projects.map((project) => (
          <article className="project-card" key={project.title}>
            <div className="project-icon">
              <FolderKanban aria-hidden="true" />
            </div>
            <div>
              <h3>{project.title}</h3>
              <p>{project.description}</p>
            </div>
            <div className="tag-list">
              {project.technologies.map((technology) => (
                <span className="tag" key={technology}>
                  {technology}
                </span>
              ))}
            </div>
            {project.url ? (
              <a className="button button-secondary" href={project.url}>
                View Project <ExternalLink size={17} />
              </a>
            ) : (
              <button className="button button-disabled" type="button" disabled>
                Project Link Coming Later
              </button>
            )}
          </article>
        ))}
      </div>
    </section>
  );
}

export default Projects;
