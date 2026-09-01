import React from 'react';
import { profile } from '../data/portfolio.js';

function Footer() {
  return (
    <footer className="site-footer">
      <p>
        {profile.name} · {profile.tagline}
      </p>
      <p>FlyRank General AI Fluency Impact Project</p>
    </footer>
  );
}

export default Footer;
