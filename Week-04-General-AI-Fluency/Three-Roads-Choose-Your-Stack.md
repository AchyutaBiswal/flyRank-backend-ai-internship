# Three Roads: Choose Your Stack with AI

**FlyRank Backend AI Engineering Internship — Week 4 Assignment**
**Submitted by:** Achyuta Biswal

---

## Introduction

For this assignment, I had to decide what technology stack I should use to build my personal portfolio website. I'm a Computer Science undergraduate working toward becoming a Java Full Stack Developer, and I wanted to make this decision the same way I'd make it in a real job — by actually looking at my skill level, my timeline, and what the portfolio needs to do, instead of just picking the stack that sounds most impressive.

I went in with a rough idea of what I wanted to use, but I told myself I'd only stick with it if it actually held up once I compared it honestly against the alternatives. Below are the three options I considered, followed by a pressure-test of each one, and my final decision with the reasoning behind it.

---

## Option 1: Static Portfolio (HTML + CSS + JavaScript + GitHub Pages)

**1. Technology Stack**
Plain HTML for structure, CSS for styling, and JavaScript for small interactive touches (like a mobile nav menu, a project filter, or a simple contact form validation). Hosted on GitHub Pages.

**2. How I Would Build It**
I'd structure it as a handful of static pages (or a single page with sections) — Home, About, Skills, Projects, Internships/Certificates, and Contact — matching the sitemap I planned out. Projects would be laid out as cards with images, a short description, tech stack tags, a GitHub link, and a live demo link where one exists. I'd write the CSS myself using Flexbox/Grid rather than pulling in a framework, so I fully understand every part of the site.

**3. Free Hosting Options**
GitHub Pages is the obvious choice since it's free, connects directly to my GitHub repo, and gives me a custom subdomain (or a custom domain if I want one later). Netlify or Vercel would also work for free if I ever wanted smoother CI/CD, but GitHub Pages is the simplest fit here.

**4. Is a Backend Needed?**
No. Nothing in my current sitemap needs a server — no logins, no database-driven content, no comments. It's all content I write once and update occasionally, which is exactly what a static site is for.

**5. Advantages**
- I already know HTML, CSS, and basic-to-intermediate JavaScript, so there's no new syntax or framework to learn.
- Nothing to configure — no build tools, no bundler, no `npm install` breaking on me.
- Fast to load, since there's no framework overhead.
- Deployment is basically "push to GitHub" and it's live.
- Very easy to explain in an interview — I can speak to every line of code because I wrote it directly.

**6. Real Trade-offs and Disadvantages**
- Managing repeated markup (like a navbar on every page) by hand gets tedious, and it's easy for pages to drift out of sync if I forget to update one.
- Adding a new project means manually duplicating and editing HTML instead of just adding an entry to a list.
- It doesn't show off any frontend framework skills, and recruiters looking specifically for React experience won't see it here.
- If the site grows a lot (many more projects, blog posts, etc.), plain HTML starts to feel repetitive and harder to maintain than a component-based setup.

**7. Difficulty Based on My Current Skill Level**
Low. This is squarely inside what I already know how to do. There's no learning curve — it's mostly a matter of time and design decisions, not new concepts.

**8. Can I Finish It in Two Weeks?**
Yes, comfortably. I could realistically have a working, deployed version within the first week and use the second week for content, screenshots, and polish.

**9. Can I Maintain It Myself?**
Yes. Updating a static site just means editing HTML/CSS and pushing to GitHub. There's no dependency updates, no framework version upgrades, nothing that can "break" on its own over time.

---

## Option 2: Modern Frontend Portfolio (React + Vite + Free Static Hosting)

**1. Technology Stack**
React with Vite as the build tool, deployed to a free static host like Vercel, Netlify, or GitHub Pages (with some extra config for client-side routing).

**2. How I Would Build It**
I'd break the site into components — a Navbar, a ProjectCard, a SkillBadge, page components for Home/About/Projects/Contact, etc. — and manage routing with React Router. Projects would live in a JS array or JSON file, so adding a new one means adding an object instead of writing new markup.

**3. Free Hosting Options**
Vercel or Netlify are the best fit for a Vite/React app — both have generous free tiers and deploy automatically from GitHub. GitHub Pages can also host it, but React Router needs extra configuration to work properly there.

**4. Is a Backend Needed?**
No, same as Option 1. React is still just producing static output at build time; nothing here requires a server.

**5. Advantages**
- Reusable components make it much easier to keep the design consistent and to add new projects later.
- Demonstrates frontend framework experience, which matters for a "full stack" title.
- Cleaner separation between content (data) and layout (components).
- Skills here transfer directly to future React work.

**6. Real Trade-offs and Disadvantages**
- I have to be honest here: I'm still learning React. I understand the basics, but I don't have the depth to move quickly through things like component state management, routing edge cases, or debugging build errors under time pressure.
- There's real setup overhead — Vite config, React Router config, occasionally chasing down a dependency or build error that has nothing to do with my actual content.
- If something breaks in the build pipeline, diagnosing it takes React-specific knowledge I'm still building.
- I'd be learning React *while* trying to hit a two-week portfolio deadline, which risks spending most of the time on the framework instead of the content that's supposed to represent my work.

**7. Difficulty Based on My Current Skill Level**
Medium-to-high. Not impossible, but it stretches me in a way that adds real risk to the timeline.

**8. Can I Finish It in Two Weeks?**
It's uncertain. If everything went smoothly, maybe. But between learning React patterns properly, setting up the build tooling, and still needing to write all the actual content (project write-ups, screenshots, bios), two weeks is tight, and I'd probably end up with something rushed or with a design/content quality lower than I want to submit.

**9. Can I Maintain It Myself?**
Mostly yes, but with more friction than Option 1. React apps need occasional dependency updates, and if I step away from the code for a few months, there's more to re-familiarize myself with compared to plain HTML/CSS.

---

## Option 3: Full-Stack Portfolio (React Frontend + Java Spring Boot Backend + Database + Free Hosting)

**1. Technology Stack**
React frontend, Spring Boot REST API backend, a database (MySQL or PostgreSQL) to store project/content data, deployed using free tiers on something like Render, Railway, or a similar platform for the backend, plus Vercel/Netlify for the frontend.

**2. How I Would Build It**
The backend would expose REST endpoints (`/api/projects`, `/api/certificates`, etc.) backed by a database, and the React frontend would fetch and render that data instead of having it hardcoded. This is essentially treating my portfolio like a small full-stack application with a content API.

**3. Free Hosting Options**
Free tiers exist (Render, Railway, Fly.io) but they come with real limitations — spin-down after inactivity, cold starts, limited free database storage, and sometimes time-boxed free trials rather than permanent free tiers. Keeping this fully free long-term takes more active management than the other two options.

**4. Is a Backend Needed?**
Honestly, no — not for what my portfolio actually needs right now. My content (projects, skills, certificates) doesn't change often enough to justify a database and API layer. A backend makes sense when content changes frequently, needs user interaction, or requires business logic — none of which applies here. This would be technology for its own sake rather than because the project needs it.

**5. Advantages**
- This is genuinely the closest match to my long-term goal — it uses Java, Spring Boot, REST APIs, and SQL, which are exactly the skills I want to be known for.
- It would be a strong demonstration project in its own right, separate from being "just a portfolio."
- Shows I can connect a frontend to a real backend and database, which is valuable to show employers.

**6. Real Trade-offs and Disadvantages**
- It combines the React learning curve from Option 2 *with* backend deployment complexity — CORS configuration, environment variables, database connection strings, hosting spin-down delays, and debugging across two separate deployed services instead of one.
- Free backend hosting isn't as simple as free static hosting — services sleep, cold-start delays hurt user experience, and free database tiers are limited.
- Every additional moving part (frontend, backend, database, two separate deployments) is one more thing that can break, and one more thing I have to maintain and monitor.
- For a portfolio specifically, this adds a lot of engineering overhead just to display content that doesn't actually need to be dynamic.

**7. Difficulty Based on My Current Skill Level**
High, not because of Spring Boot or Java (I'm genuinely comfortable there), but because it stacks the React uncertainty from Option 2 on top of full-stack deployment complexity, all at once.

**8. Can I Finish It in Two Weeks?**
Realistically, no — not to a quality I'd be proud to submit. Between React, the API layer, the database schema, backend hosting quirks, and frontend-backend integration, two weeks is not enough time on top of everything else in the internship.

**9. Can I Maintain It Myself?**
Yes, but with the most ongoing effort of the three — monitoring two deployments, keeping both frontend and backend dependencies updated, and dealing with free-tier hosting limitations (like servers sleeping) over time.

---

## Pressure-Testing the Options

**What breaks or becomes difficult if I choose the simplest option (Option 1)?**
The main pain point is repetition — shared elements like the navbar have to be updated by hand across pages, and the site doesn't "scale" as gracefully if I add a large number of projects later. It also doesn't showcase React skills directly. But nothing about it *breaks*; it's a maintenance style trade-off, not a functional one.

**What additional maintenance work would I have if I choose the most powerful option (Option 3)?**
I'd be responsible for two deployments instead of one, database uptime/backups, environment configuration for both services, CORS between frontend and backend, and monitoring for free-tier limitations like spin-downs. Any time I wanted to add or edit a project, I'd be touching the database or writing an admin flow, instead of just editing a file.

**Can I realistically finish each option within two weeks?**
- Option 1: Yes, with room to spare for content and polish.
- Option 2: Possible but risky — real chance of running out of time or submitting something rushed.
- Option 3: No, not at the quality level I want to represent me.

**Does each option display my projects, screenshots, GitHub links, demos, certificates, and written content properly?**
Yes — all three. This is an important honest point: none of the three options fail at the actual *content requirements*. HTML/CSS/JS handles images, links, and long-form text just as well as React does for a site of this size. The difference between the options isn't "can it display my work," it's "how much engineering effort does it take to get there, and is that effort worth it right now."

**Is a backend actually necessary for my current portfolio?**
No. My requirements explicitly rule out authentication, user accounts, admin dashboards, database-driven content, and comments. A backend earns its place when there's a real reason for one — dynamic content, user interaction, business logic. My portfolio has none of that yet. Adding Spring Boot and a database here would be building infrastructure for a problem I don't currently have.

---

## My Final Decision and Rationale

**The stack I chose:** Option 1 — HTML + CSS + JavaScript, deployed on GitHub Pages.

**The two alternatives I considered:** A React + Vite static portfolio (Option 2), and a full React + Spring Boot + database full-stack portfolio (Option 3).

**Why I did not choose them:**
I didn't rule out Option 2 or Option 3 because they're bad ideas — they're actually the more "impressive-looking" stacks on paper, and Option 3 in particular is the closest to my actual career goal of being a Java Full Stack Developer. But I ruled them out because they don't match where I honestly am right now. I'm still learning React, not confidently using it, and layering React (and in Option 3's case, a full backend deployment too) on top of a two-week portfolio deadline creates real risk of either missing the deadline or submitting something half-finished. Neither option is *needed* by my current requirements either — I explicitly don't need dynamic content, user accounts, or a database, so building Option 3 would mean adding complexity that solves a problem I don't have, just to look more advanced.

**Why the chosen option matches my current needs:**
Everything in my sitemap — Home, About, Skills, Projects with screenshots and links, Internship/Certificates, Contact — is static content that I write once and occasionally update. HTML, CSS, and JavaScript are more than capable of displaying image galleries, GitHub links, live demo links, and long-form project write-ups. I don't need a framework to do any of that well.

**Why the chosen option is free:**
GitHub Pages hosting is completely free, with no spin-down delays, no cold starts, and no usage limits I'd realistically hit for a personal portfolio. There's no database or backend service to pay for or worry about aging out of a free trial.

**Whether I can maintain this stack:**
Yes, easily. Updating the site is just editing HTML/CSS/JS files and pushing to GitHub. There's no framework version to keep updated, no build pipeline to babysit, and no backend service that might go to sleep or need re-deploying.

**Whether it displays my work well:**
Yes. Project cards with images, descriptions, tech stack tags, GitHub links, and demo links all work fine in plain HTML/CSS. The pressure-test above confirmed that content display isn't actually a weak point of this option — it's just as capable here as the more complex stacks, for a site of this size.

**Why I am honestly choosing not to use a backend yet:**
Because I don't have a genuine reason to need one. My content doesn't change often enough, there's no user interaction, and there's no business logic to run. Using Spring Boot and a database here would be about *appearing* full-stack rather than actually needing full-stack architecture. I'd rather demonstrate my Spring Boot and SQL skills through dedicated backend projects — like the Employee Task Management System and Railway Reservation System already in my Projects section — where a backend is actually solving a real problem, than force one into a portfolio site that doesn't need it.

**Looking ahead:**
This doesn't mean I'm avoiding React or Spring Boot for good — it means I'm sequencing things sensibly. Once the static portfolio is live and doing its job, rebuilding the frontend in React (Option 2) is a reasonable next step I can take without a hard deadline hanging over it, once I've had more time to actually get comfortable with React rather than learning it under pressure. For now, the simplest option is the honest choice, not because it's the easiest way out, but because it's the one that actually fits my current skills, my timeline, and what this portfolio needs to do.
