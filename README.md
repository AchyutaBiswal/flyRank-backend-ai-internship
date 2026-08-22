# 🚀 FlyRank Backend AI Engineering Internship

Welcome to my **FlyRank Backend AI Engineering Internship** repository.

This repository documents my learning journey throughout the **FlyRank Backend AI Engineering Internship**, including weekly assignments, backend engineering projects, AI-assisted development workflows, prompt engineering exercises, authentication and authorization, database integration, API development, and professional portfolio development.

---

# 📌 Repository Overview

This repository showcases my progress in:

- 📚 Weekly internship assignments
- 💻 Backend engineering projects
- 🚀 Java Spring Boot development
- 🌐 REST API development
- 🔐 Authentication & Authorization
- 🛡️ Spring Security
- 🔑 BCrypt password hashing
- 🗄️ Database integration
- 🐘 PostgreSQL
- 🟢 SQLite
- ☁️ Supabase
- 🔌 Spring JDBC
- 📄 Swagger & OpenAPI documentation
- 🤖 AI-assisted software development
- 🧠 Prompt engineering
- 🎨 Personal branding
- 🖼️ Portfolio image curation
- 📝 Technical documentation
- 🔧 Git & GitHub workflows
- 📈 Continuous learning and professional development

---

# 📂 Repository Structure

The following structure reflects the files currently committed to this repository.

```text
flyRank-backend-ai-internship/
│
├── README.md
├── .gitignore
│
├── Week-01-General-AI-Fluency/
│   │
│   ├── 01-AI-Workflow-Audit-and-Tool-Setup/
│   │   ├── Achyuta_Biswal_FL-01_AI_Workflow_Audit.pdf
│   │   └── screenshots/
│   │       ├── anthropic-academy-profile.png
│   │       └── claude-ai-learning-assistant-project.png
│   │
│   ├── 02-Portfolio-Sitemap-and-Toolkit/
│   │   ├── Achyuta_Biswal_FL-02_Portfolio_Sitemap_and_Toolkit.pdf
│   │   └── screenshots/
│   │       ├── foundation-progress.png
│   │       ├── portfolio-sitemap.png
│   │       └── toolkit-setup.png
│   │
│   └── 03-What-Are-You-Proving/
│       └── Achyuta_Biswal_FL-03_What_Are_You_Proving.pdf
│
├── Week-02-General-AI-Fluency/
│   │
│   ├── 01-Frame-It-as-Cases/
│   │   └── flyrank_week2.pdf
│   │
│   ├── 02-The-Prompt-Ladder/
│   │   └── Achyuta_Biswal_FL-Week2_The_Prompt_Ladder.pdf
│   │
│   └── 03--Prompting-Fundamentals-on-Real-Tasks/
│       ├── FL02_PromptIterationLog.pdf
│       └── screanshots/
│           ├── prompt1_naive.png
│           ├── prompt2_role_assignment.png
│           ├── prompt3_context_motivation.png
│           ├── prompt4_few_shot.png
│           ├── prompt5_output_structure .png
│           └── prompt6_step_decomposition.png
│
├── Week-02-Backend-Foundations/
│   │
│   ├── screenshots/
│   │   ├── create-task.png
│   │   ├── delete-task.png
│   │   ├── get-by-id.png
│   │   ├── swagger-home.png
│   │   └── update-task.png
│   │
│   └── task-api/
│       └── task-api/
│           ├── pom.xml
│           ├── mvnw
│           ├── mvnw.cmd
│           │
│           ├── .mvn/
│           │   └── wrapper/
│           │       └── maven-wrapper.properties
│           │
│           ├── src/
│           │   ├── main/
│           │   │   ├── java/
│           │   │   │   └── com/achyuta/taskapi/
│           │   │   │       ├── TaskApiApplication.java
│           │   │   │       ├── controller/
│           │   │   │       │   └── TaskController.java
│           │   │   │       ├── model/
│           │   │   │       │   └── Task.java
│           │   │   │       └── service/
│           │   │   │           └── TaskService.java
│           │   │   │
│           │   │   └── resources/
│           │   │       └── application.properties
│           │   │
│           │   └── test/
│           │       └── java/
│           │           └── com/achyuta/taskapi/
│           │               └── TaskApiApplicationTests.java
│           │
│           └── .idea/
│               ├── compiler.xml
│               ├── encodings.xml
│               ├── jarRepositories.xml
│               ├── misc.xml
│               ├── modules.xml
│               ├── task-api.iml
│               └── vcs.xml
│
├── week-03-General-AI-Fluency/
│   │
│   ├── README.md
│   │
│   ├── 01-Decide-Once-Build-Your-Identity-Kit/
│   │   ├── identity-kit.pdf
│   │   └── logo.png
│   │
│   ├── 02-Kill-Your-Darlings-Curate-Your-Images/
│   │   ├── FlyRank_Week3_Curate_Your_Images_Submission.pdf
│   │   └── images/
│   │       ├── github.png
│   │       ├── hero-bg.png
│   │       ├── icons.png
│   │       ├── linkedin.png
│   │       ├── project.png
│   │       ├── rejected-image.png
│   │       └── swagger-home.png
│   │
│   └── 03-The-Through-Line-Map-Content-CTAs/
│       └── assets/
│           └── FlyRank_Week3_TheThroughLine.pptx
│
├── Week-03-Backend-Foundations/
│   │
│   └── Assignment-A2-Connecting-to-the-Database/
│       ├── README.md
│       │
│       ├── screenshots/
│       │   ├── persistence-proof.png
│       │   ├── sql-db-browser.png
│       │   ├── sql-query.png
│       │   ├── swagger-delete.png
│       │   ├── swagger-get-all.png
│       │   ├── swagger-get-by-id.png
│       │   ├── swagger-home.png
│       │   ├── swagger-post.png
│       │   └── swagger-put.png
│       │
│       └── task-api/
│           └── task-api/
│               ├── pom.xml
│               ├── mvnw
│               ├── mvnw.cmd
│               │
│               ├── .mvn/
│               │   └── wrapper/
│               │       └── maven-wrapper.properties
│               │
│               ├── src/
│               │   ├── main/
│               │   │   ├── java/
│               │   │   │   └── com/achyuta/taskapi/
│               │   │   │       ├── TaskApiApplication.java
│               │   │   │       ├── config/
│               │   │   │       │   └── DatabaseConfig.java
│               │   │   │       ├── controller/
│               │   │   │       │   └── TaskController.java
│               │   │   │       ├── model/
│               │   │   │       │   └── Task.java
│               │   │   │       └── service/
│               │   │   │           └── TaskService.java
│               │   │   │
│               │   │   └── resources/
│               │   │       └── application.properties
│               │   │
│               │   └── test/
│               │       └── java/
│               │           └── com/achyuta/taskapi/
│               │               └── TaskApiApplicationTests.java
│               │
│               ├── .gitattributes
│               └── .gitignore
│
├── Week-03-Backend-Foundations/
│   └── Assignment-A3-Containerize-Your-Stack/
│       ├── .dockerignore
│       ├── .env.example
│       ├── .gitattributes
│       ├── .gitignore
│       ├── Dockerfile
│       ├── README.md
│       ├── docker-compose.yml
│       ├── mvnw
│       ├── mvnw.cmd
│       ├── pom.xml
│       │
│       ├── .mvn/
│       │   └── wrapper/
│       │       └── maven-wrapper.properties
│       │
│       ├── src/
│       │   ├── main/
│       │   │   ├── java/
│       │   │   │   └── com/achyuta/taskapi/
│       │   │   │       ├── TaskApiApplication.java
│       │   │   │       ├── controller/
│       │   │   │       │   └── TaskController.java
│       │   │   │       ├── model/
│       │   │   │       │   └── Task.java
│       │   │   │       ├── repository/
│       │   │   │       │   ├── PostgresTaskRepository.java
│       │   │   │       │   └── TaskRepository.java
│       │   │   │       └── service/
│       │   │   │           └── TaskService.java
│       │   │   │
│       │   │   └── resources/
│       │   │       ├── application.properties
│       │   │       └── schema.sql
│       │   │
│       │   └── test/
│       │       ├── java/
│       │       │   └── com/achyuta/taskapi/
│       │       │       └── TaskApiApplicationTests.java
│       │       └── resources/
│       │           └── application.properties
│
├── Week-04-Backend-AI/
│   │
│   └── Assignment-A1-FlyRank-Auth/
│       │
│       └── flyrank-auth/
│           ├── pom.xml
│           ├── mvnw
│           ├── mvnw.cmd
│           ├── .gitattributes
│           ├── .gitignore
│           │
│           ├── .mvn/
│           │   └── wrapper/
│           │       └── maven-wrapper.properties
│           │
│           └── src/
│               ├── main/
│               │   ├── java/
│               │   │   └── com/flyrank/auth/
│               │   │       ├── FlyrankAuthApplication.java
│               │   │       ├── PasswordGenerator.java
│               │   │       ├── config/
│               │   │       │   └── SecurityConfig.java
│               │   │       ├── controller/
│               │   │       │   └── PublicController.java
│               │   │       ├── model/
│               │   │       │   └── User.java
│               │   │       ├── repository/
│               │   │       │   └── UserRepository.java
│               │   │       └── service/
│               │   │           └── UserDetailsServiceImpl.java
│               │   │
│               │   └── resources/
│               │       └── application.properties
│               │
│               └── test/
│                   └── java/
│                       └── com/flyrank/auth/
│                           └── FlyrankAuthApplicationTests.java
│
└── Week-04-General-AI-Fluency/
    │
    ├── 01-Assignment-Empty-But-Live/
    │   ├── index.html
    │   ├── script.js
    │   └── style.css
    │
    ├── 04-Ship-an-Automation-Workflow-v2/
    │   └── hibernate_loading_notes-v2.md
    │
    └── Three-Roads-Choose-Your-Stack.md
# 📅 Internship Progress



| Week | Module / Assignment | Status |
|---|---|---|
| Week 1 | AI Workflow Audit and Tool Setup | ✅ Completed |
| Week 1 | Portfolio Sitemap and Toolkit | ✅ Completed |
| Week 1 | What Are You Proving? | ✅ Completed |
| Week 2 | Frame It as Cases | ✅ Completed |
| Week 2 | The Prompt Ladder | ✅ Completed |
| Week 2 | Prompting Fundamentals on Real Tasks | ✅ Completed |
| Week 2 | Backend Foundations – CRUD Task API | ✅ Completed |
| Week 3 | Decide Once – Build Your Identity Kit | ✅ Completed |
| Week 3 | Kill Your Darlings – Curate Your Images | ✅ Completed |
| Week 3 | Backend Foundations – Connecting to the Database | ✅ Completed |
| Week 4 | Empty but Live: Ship a Blank Page | ✅ Completed |
| Week 4 | Three Roads: Choose Your Stack with AI | ✅ Completed |
| Week 4 | Ship an Automation Workflow v2 | ✅ Completed |
| Week 4 | Agent Concepts and MCP Basics | ⚠️ Not Submitted |
| Week 4 | Backend AI – Auth - Login & Protect | ✅ Submitted |

---

# 📖 Week 1 – General AI Fluency

## 🎯 Objective

Build a strong foundation in AI-assisted software development, professional workflows, technical communication, and portfolio planning.

---

## ✅ Assignment 1 – AI Workflow Audit and Tool Setup

Evaluated and documented my AI-assisted development workflow.

### Activities

- Reviewed current development workflow
- Identified opportunities for AI assistance
- Established responsible AI usage
- Documented development tools
- Improved software development productivity

### Skills Learned

- AI-assisted Development
- Developer Productivity
- AI Workflow Design
- Technical Documentation
- Responsible AI Usage

---

## ✅ Assignment 2 – Portfolio Sitemap and Toolkit

Designed a professional portfolio structure for showcasing software engineering projects and technical skills.

### Activities

- Designed portfolio architecture
- Planned project sections
- Organized technical documentation
- Identified required portfolio assets
- Planned project presentation strategy

### Skills Learned

- Portfolio Planning
- Information Architecture
- Technical Communication
- Professional Presentation
- Project Documentation

---

## ✅ Assignment 3 – What Are You Proving?

Created a professional proof statement focused on backend engineering capabilities.

### Proof Statement

> I can design and implement secure, role-based backend systems in Java and Spring Boot — specifically, JWT-based authentication with Spring Security where distinct API endpoints and authorization logic separate Admin and Employee permissions, as demonstrated in my Employee Task Management System.

### Skills Learned

- Professional Positioning
- Technical Communication
- Backend Engineering
- Security Concepts
- Portfolio Storytelling

---

# 📖 Week 2 – General AI Fluency

## ✅ Assignment 1 – Frame It as Cases

Created professional case studies based on real software development projects.

### Projects Covered

- Employee Task Management System
- Railway Reservation System
- Internship Experience
- Technical Project Case Studies

### Deliverables

- Case Studies
- Voice Card
- Professional Bio
- Project Narratives

### Skills Learned

- Case Study Writing
- Technical Storytelling
- Project Presentation
- Professional Communication

---

# ✅ Assignment 2 – The Prompt Ladder

Completed prompt refinement exercises to improve the quality of AI-assisted development.

### Focus Areas

- Basic prompts
- Context-rich prompts
- Structured prompts
- Iterative prompting
- Prompt refinement
- AI response evaluation

### Skills Learned

- Prompt Engineering
- AI Evaluation
- Structured Prompting
- Iterative Prompt Design
- AI-assisted Development

---

# ✅ Assignment 3 – Prompting Fundamentals on Real Tasks

Applied different prompting strategies to practical Java and Spring Boot development tasks.

### Prompting Techniques

- Naive Prompting
- Role Prompting
- Context Prompting
- Few-shot Prompting
- Step-by-step Prompting
- Structured Output Prompting

### Skills Learned

- Prompt Engineering
- AI-assisted Coding
- Requirement Analysis
- Technical Problem Solving
- AI Response Evaluation

---

# 📖 Week 2 – Backend Foundations

# 🚀 CRUD Task API using Spring Boot

Developed a RESTful Task Management API using Spring Boot.

The project demonstrates the fundamentals of backend API development and CRUD operations.

---

## 🎯 Objectives

- Build a REST API
- Implement CRUD operations
- Understand HTTP methods
- Create API endpoints
- Return JSON responses
- Document APIs using Swagger/OpenAPI

---

## ✨ Features

- Create Task
- Get All Tasks
- Get Task By ID
- Update Task
- Delete Task
- Health Check
- Swagger UI
- OpenAPI Documentation
- RESTful Architecture

---

## 🌐 REST Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/` | API Home |
| GET | `/health` | Health Check |
| GET | `/tasks` | Get All Tasks |
| GET | `/tasks/{id}` | Get Task By ID |
| POST | `/tasks` | Create Task |
| PUT | `/tasks/{id}` | Update Task |
| DELETE | `/tasks/{id}` | Delete Task |

---

## 🛠 Technologies

- Java
- Spring Boot
- Spring MVC
- REST API
- JSON
- Maven
- Swagger/OpenAPI

---

## 📚 Skills Learned

- Spring Boot
- REST APIs
- CRUD Operations
- HTTP Methods
- JSON
- Dependency Injection
- API Documentation
- Backend Architecture

---

# 📖 Week 3 – General AI Fluency

# 🎨 Assignment 1 – Decide Once: Build Your Identity Kit

Created a consistent personal brand identity for use across:

- GitHub
- LinkedIn
- Resume
- Portfolio
- Technical documentation
- Software projects

---

## 👤 Personal Brand

**Name:** Achyuta Biswal

**Role:**

> Java Full Stack Developer | Generative AI Engineer

**Tagline:**

> Building Intelligent Software

---

## 🎨 Identity Kit

### Deliverables

- Professional Logo
- AB Monogram
- Identity Kit PDF
- Typography Guide
- Color Palette
- Style Guidelines
- Visual Branding Rules

### Typography

- Poppins Bold
- Inter Regular

### Skills Learned

- Personal Branding
- Visual Identity
- Portfolio Design
- Design Consistency
- Professional Documentation

---

# 🖼️ Assignment 2 – Kill Your Darlings: Curate Your Images

Curated a professional image library for use in portfolios, GitHub, LinkedIn, and technical presentations.

---

## 📸 Selected Visual Assets

- GitHub Screenshot
- Spring Boot Project Screenshot
- Swagger UI Screenshot
- LinkedIn Screenshot
- AI-generated Hero Background
- Technology Icons
- Supporting Portfolio Images

---

## 🎯 Objective

The goal was to select visuals that:

- Communicate technical skills
- Support project storytelling
- Maintain visual consistency
- Avoid unnecessary decoration
- Improve professional presentation

### Skills Learned

- Image Curation
- Visual Communication
- Portfolio Storytelling
- AI Image Generation
- Design Consistency

---

# 📖 Week 3 – Backend Foundations

# 🗄️ Assignment A2 – Connecting to the Database

Enhanced the CRUD Task API by integrating a persistent **SQLite database** using **Spring JDBC**.

---

## 🎯 Objectives

- Connect Spring Boot with a database
- Implement persistent storage
- Use Spring JDBC
- Execute SQL queries
- Store and retrieve tasks
- Verify database operations

---

## ✨ Features

- SQLite Database Integration
- Spring JDBC
- Persistent Storage
- Automatic Table Creation
- Initial Sample Data
- Full CRUD Operations
- SQL Query Execution
- Swagger API Testing
- Database Verification

---

## 🗄️ Database

**Database:** SQLite

**Table:** `tasks`

### Columns

| Column | Description |
|---|---|
| `id` | Unique task identifier |
| `title` | Task title |
| `done` | Task completion status |

---

## 🌐 REST API

| Method | Endpoint | Description |
|---|---|---|
| GET | `/tasks` | Get All Tasks |
| GET | `/tasks/{id}` | Get Task By ID |
| POST | `/tasks` | Create Task |
| PUT | `/tasks/{id}` | Update Task |
| DELETE | `/tasks/{id}` | Delete Task |

---

## 🛠 Technologies

- Java
- Spring Boot
- Spring JDBC
- SQLite
- SQL
- Maven
- Swagger/OpenAPI

---

## 📚 Skills Learned

- Database Integration
- SQL
- SQLite
- Spring JDBC
- DataSource Configuration
- Persistent Storage
- REST API Testing

---

# 📖 Week 4 – Backend AI

# 🔐 Assignment A1 – FlyRank Auth

Built a database-backed authentication service using:

- Java
- Spring Boot
- Spring Security
- Spring JDBC
- PostgreSQL
- Supabase
- BCrypt
- REST APIs

The project demonstrates how Spring Security can authenticate users stored in a PostgreSQL database.

---

# 🎯 Assignment Objective

The objective of this assignment was to build a secure authentication foundation that can:

- Authenticate users from a database
- Load users using `UserDetailsService`
- Secure passwords using BCrypt
- Support user roles
- Enable/disable users
- Protect API endpoints
- Allow public endpoints
- Connect Spring Boot to Supabase PostgreSQL
- Use environment variables for database credentials

---

# 🏗️ Application Architecture

```text
                    ┌─────────────────────┐
                    │       Client        │
                    │ Postman / Browser   │
                    └──────────┬──────────┘
                               │
                               │ HTTP Request
                               ▼
                    ┌─────────────────────┐
                    │   Spring Security  │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │ UserDetailsService │
                    │       Impl         │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   UserRepository   │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │ PostgreSQL /       │
                    │ Supabase Database   │
                    └─────────────────────┘
# 📖 Week 4 – General AI Fluency

Week 4 focused on applying AI tools to practical software development, technology-stack decisions, automation, deployment, and emerging AI-agent concepts.

---

## 🌐 Assignment 1 – Empty but Live: Ship a Blank Page

**Track:** General AI Fluency  
**Workload:** 2 hours  
**Status:** ✅ Submitted

### 🎯 Objective

Build and deploy a minimal but live web page to understand the fundamentals of shipping a project from development to production.

### Key Activities

- Created a minimal web page
- Prepared the project for deployment
- Deployed the page to a live hosting platform
- Verified that the deployed page was publicly accessible
- Practiced the basic development-to-deployment workflow

### Skills Learned

- Web Deployment
- Hosting
- Git/GitHub Workflow
- Production Basics
- Deployment Verification
- Shipping Software

---

## 🧠 Assignment 2 – Three Roads: Choose Your Stack with AI

**Track:** General AI Fluency  
**Workload:** 2 hours  
**Status:** ✅ Submitted

### 🎯 Objective

Use AI to evaluate different technology-stack options and make an informed technical decision rather than selecting technologies based only on familiarity.

### Key Activities

- Compared different technology-stack options
- Used AI to research and evaluate technologies
- Considered project requirements
- Compared advantages and disadvantages
- Evaluated development complexity
- Selected an appropriate technology stack
- Documented the reasoning behind the decision

### Skills Learned

- AI-assisted Decision Making
- Technology Evaluation
- Stack Selection
- Requirement Analysis
- Technical Reasoning
- Prompt Engineering
- AI-assisted Research

---

## 🤖 Assignment 3 – Ship an Automation Workflow v2

**Track:** General AI Fluency  
**Workload:** 7 hours  
**Status:** ✅ Submitted

### 🎯 Objective

Design and ship an improved automation workflow using AI-assisted development techniques.

### Key Activities

- Designed an automation workflow
- Identified repetitive tasks suitable for automation
- Used AI to assist with workflow development
- Improved an existing automation approach
- Tested the workflow
- Documented the implementation
- Focused on practical developer productivity

### Skills Learned

- Workflow Automation
- AI-assisted Development
- Automation Design
- Prompt Engineering
- Debugging
- Testing
- Developer Productivity
- Technical Documentation

---

## 🧩 Assignment 4 – Agent Concepts and MCP Basics

**Track:** General AI Fluency  
**Workload:** 5 hours  
**Status:** ⚠️ Not Submitted

### 🎯 Objective

Understand the fundamentals of AI agents and Model Context Protocol (MCP), including how modern AI systems can interact with tools and external resources.

### Key Topics

- AI Agent Concepts
- Agentic Workflows
- Tool Usage
- Model Context Protocol (MCP)
- AI Tool Integration
- Context Management
- AI-assisted Software Development

### Skills to Develop

- AI Agent Fundamentals
- MCP Fundamentals
- Tool Calling Concepts
- Agentic Workflow Design
- AI System Architecture
- Modern AI Development

> **Current Status:** This assignment is still pending submission.

---

# 🎤 Week 4 General AI Fluency Events

## 🌐 AI Tool Landscape: Choosing the Right Tool For the Job

**Track:** General AI Fluency  
**Date:** July 30, 2026  
**Duration:** 90 minutes  
**Status:** ✅ Completed

### Focus

Understanding how to choose appropriate AI tools based on the problem, requirements, workflow, and expected outcome.

### Key Learning Areas

- AI tool selection
- Comparing AI tools
- Matching tools to tasks
- AI-assisted workflows
- Developer productivity
- Practical AI usage

---

## 💼 Creating Value at Work: The Advice Nobody Gives You

**Track:** General AI Fluency  
**Date:** August 4, 2026  
**Duration:** 60 minutes  
**Status:** ✅ Completed

### Focus

Understanding how to create meaningful value in a professional environment and approach work with an outcome-oriented mindset.

### Key Learning Areas

- Professional development
- Creating value
- Workplace communication
- Problem solving
- Ownership
- Professional mindset

---

# 📚 Week 4 General AI Fluency Skills

Through Week 4, I developed practical experience in:

- 🤖 AI-assisted Development
- 🧠 Prompt Engineering
- 🔧 AI Tool Selection
- ⚙️ Workflow Automation
- 🚀 Software Deployment
- 🧩 AI Agent Concepts
- 🔌 MCP Fundamentals
- 🛠️ Technology Stack Evaluation
- 🔍 Technical Research
- 📊 AI-assisted Decision Making
- 💼 Professional Problem Solving
- 📄 Technical Documentation
- ⚡ Developer Productivity
```

---



# 🔐 Authentication Flow

```text
Client
  │
  │ Username + Password
  ▼
Spring Security
  │
  ▼
UserDetailsServiceImpl
  │
  ▼
UserRepository
  │
  ▼
PostgreSQL / Supabase
  │
  ▼
User Found
  │
  ▼
BCrypt Password Verification
  │
  ├── Invalid → Authentication Failed
  │
  └── Valid → Authentication Successful
```

---

# 🔑 Security Components

## `SecurityConfig`

The `SecurityConfig` class configures Spring Security.

### Responsibilities

- Configure HTTP security
- Define public endpoints
- Protect authenticated endpoints
- Configure authentication
- Configure password encoding
- Configure authorization rules

---

# 👤 `User`

The `User` model represents a user stored in the database.

Typical user information includes:

```text
id
username
password
role
enabled
```

---

# 🗃️ `UserRepository`

The repository is responsible for retrieving user information from PostgreSQL.

Example operation:

```java
findByUsername(String username)
```

This allows Spring Security to find the user during authentication.

---

# 👨‍💻 `UserDetailsServiceImpl`

Implemented Spring Security's:

```java
UserDetailsService
```

The implementation retrieves the user from the database.

Example:

```java
@Override
public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {

    User user = userRepository.findByUsername(username)
            .orElseThrow(() ->
                    new UsernameNotFoundException(
                            "User not found: " + username
                    )
            );

    return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .roles(user.getRole())
            .disabled(!user.isEnabled())
            .build();
}
```

---

# 🔒 Password Security

Passwords are stored using **BCrypt hashing**.

The database stores a BCrypt hash rather than the original password.

Example:

```text
$2a$10$...
```

### Authentication Process

```text
Original Password
       │
       ▼
Spring Security
       │
       ▼
BCrypt Password Encoder
       │
       ▼
Compare with Stored Hash
       │
       ├── Match → Authentication Success
       │
       └── No Match → Authentication Failure
```

### Important

The BCrypt hash stored in the database is **not the password that should be entered into Basic Authentication**.

When testing with Basic Auth:

```text
Username → Original username
Password → Original plain-text password
```

Spring Security performs the BCrypt comparison internally.

---

# 🗄️ Database Integration

## ☁️ Supabase PostgreSQL

The FlyRank Auth application uses PostgreSQL hosted through Supabase.

### Database Stack

```text
Spring Boot
     │
Spring JDBC
     │
JDBC Driver
     │
PostgreSQL
     │
Supabase
```

---

# 🔧 Database Configuration

Database credentials are loaded through environment variables.

The committed `application.properties` contains:

```properties
spring.application.name=flyrank-auth
server.port=8080

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
```

### Environment Variables

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

This keeps sensitive credentials outside the source code.

---

# 🔐 Environment Configuration

Create a local `.env` or configure environment variables in IntelliJ IDEA.

Example:

```text
DB_URL=jdbc:postgresql://<supabase-host>:5432/postgres
DB_USERNAME=postgres
DB_PASSWORD=<your-password>
```

> Replace the placeholder values with your own Supabase database credentials.

**Never commit real database passwords, API keys, tokens, or secrets to GitHub.**

---

# 🌐 Application

The Spring Boot application runs on:

```text
http://localhost:8080
```

---

# 🌍 Public Endpoint

The project includes a public controller.

Example:

```text
GET /public/hello
```

Public endpoints can be accessed without authentication.

Protected endpoints require valid authentication.

---

# 🧪 API Testing

The application can be tested using:

- Postman
- IntelliJ IDEA HTTP Client
- cURL
- Browser for public endpoints

---

# 🔑 Basic Authentication

For protected endpoints, use:

```text
Authentication Type: Basic Auth

Username: your username
Password: your original password
```

Do **not** use the BCrypt hash as the Basic Auth password.

---

# 🛡️ Security Features

The application provides:

- 🔐 Spring Security
- 🔑 BCrypt Password Hashing
- 👤 Database-backed Authentication
- 🗃️ Custom `UserDetailsService`
- 👥 Role-based Authorization
- ✅ Enabled/Disabled User Support
- 🔒 Protected Endpoints
- 🌐 Public Endpoints
- ☁️ Supabase PostgreSQL
- 🔧 Environment-based Configuration

---

# 🧰 Project Configuration

## Maven

The project uses Maven for:

- Dependency Management
- Project Build
- Testing
- Running Spring Boot

### Maven Wrapper

The project includes:

```text
mvnw
mvnw.cmd
```

This allows Maven commands to be executed without requiring a globally installed Maven version.

---

# ▶️ How to Run the Project

## 1️⃣ Clone the Repository

```bash
git clone https://github.com/AchyutaBiswal/flyRank-backend-ai-internship.git
```

---

## 2️⃣ Navigate to the Project

```bash
cd flyRank-backend-ai-internship
```

Then:

```bash
cd Week-04-Backend-AI/Assignment-A1-FlyRank-Auth/flyrank-auth
```

---

## 3️⃣ Configure Environment Variables

Set:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

---

## 4️⃣ Run Using Maven Wrapper

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

---

## 5️⃣ Run from IntelliJ IDEA

Open the project in IntelliJ IDEA and run:

```text
FlyrankAuthApplication
```

The application should start on:

```text
http://localhost:8080
```

---

# 🧪 Testing

Run the test suite using:

### Windows

```powershell
.\mvnw.cmd test
```

### Linux / macOS

```bash
./mvnw test
```

---

# 🗂️ Important Project Files

| File | Purpose |
|---|---|
| `FlyrankAuthApplication.java` | Main Spring Boot application |
| `SecurityConfig.java` | Spring Security configuration |
| `PublicController.java` | Public API endpoints |
| `User.java` | User model |
| `UserRepository.java` | Database access |
| `UserDetailsServiceImpl.java` | Loads users for Spring Security |
| `PasswordGenerator.java` | Password utility |
| `application.properties` | Application and database configuration |
| `pom.xml` | Maven dependencies |

---

# 🧠 What I Learned in Week 4

Through this assignment, I learned how to:

- Configure Spring Security
- Implement custom authentication
- Create a custom `UserDetailsService`
- Retrieve users from a database
- Configure password hashing
- Use BCrypt
- Implement role-based authorization
- Connect Spring Boot to PostgreSQL
- Use Supabase as a hosted PostgreSQL database
- Protect database credentials using environment variables
- Test Basic Authentication
- Understand the Spring Security authentication flow

---

# 🛠️ Technologies Used

## Programming Language

- Java 23

## Backend Framework

- Spring Boot 4.1
- Spring MVC
- Spring Security
- Spring JDBC

## Security

- Spring Security 7
- BCrypt
- Basic Authentication
- Role-based Authorization

## Database

- PostgreSQL
- Supabase
- JDBC

## API

- REST API
- JSON
- Swagger / OpenAPI

## Build Tool

- Maven

## IDE

- IntelliJ IDEA
- VS Code

## Version Control

- Git
- GitHub

## AI Tools

- ChatGPT
- Claude AI
- AI-assisted development workflows

---

# 🎯 Overall Skills Developed

### ☕ Java

- Object-Oriented Programming
- Collections
- Exception Handling
- Backend Development

### 🌱 Spring Boot

- Spring MVC
- REST APIs
- Dependency Injection
- Spring JDBC
- Spring Security

### 🔐 Security

- Authentication
- Authorization
- BCrypt
- Basic Authentication
- Role-based Access Control
- UserDetailsService

### 🗄️ Databases

- SQL
- SQLite
- PostgreSQL
- Database Persistence
- JDBC
- Supabase

### 🌐 Backend

- REST API Design
- CRUD Operations
- HTTP Methods
- JSON
- API Testing

### 🤖 AI

- AI-assisted Development
- Prompt Engineering
- Prompt Refinement
- AI Workflow Design
- AI Evaluation

### 🧰 Developer Tools

- IntelliJ IDEA
- VS Code
- Maven
- Git
- GitHub
- Postman

### 📄 Documentation

- Swagger
- OpenAPI
- README Documentation
- Technical Writing

---

# 🏆 Internship Achievements

- ✅ Completed Week 1 General AI Fluency
- ✅ Completed Week 2 General AI Fluency
- ✅ Completed Week 2 Backend Foundations
- ✅ Completed Week 3 General AI Fluency
- ✅ Completed Week 3 Backend Foundations
- ✅ Completed Week 4 Backend AI
- ✅ Built a RESTful CRUD API
- ✅ Integrated SQLite Database
- ✅ Integrated PostgreSQL Database
- ✅ Connected Spring Boot with Supabase
- ✅ Implemented Spring Security
- ✅ Implemented Database-backed Authentication
- ✅ Implemented BCrypt Password Hashing
- ✅ Implemented Role-based Authorization
- ✅ Used Environment Variables for Secrets
- ✅ Created Professional Identity Kit
- ✅ Curated Portfolio Images
- ✅ Practiced AI-assisted Development
- ✅ Practiced Prompt Engineering
- ✅ Maintained an Organized GitHub Repository

---

# 📊 Repository Statistics

| Metric | Value |
|---|---:|
| Internship Weeks Completed | 4 |
| Assignments Completed | 11+ |
| Backend Projects | 3 |
| Database Projects | 2 |
| Authentication Projects | 1 |
| REST APIs Built | 3 |
| Programming Language | Java |
| Primary Framework | Spring Boot |
| Documentation | Comprehensive |

---

# 📈 Current Progress

| Module | Status |
|---|---|
| Week 1 – General AI Fluency | ✅ Completed |
| Week 2 – General AI Fluency | ✅ Completed |
| Week 2 – Backend Foundations | ✅ Completed |
| Week 3 – General AI Fluency | ✅ Completed |
| Week 3 – Backend Foundations | ✅ Completed |
| Week 4 – Backend AI | ✅ Completed |
| Remaining Internship Modules | 🚧 In Progress |

---

# 🔒 Security & GitHub

This repository follows basic security practices.

Sensitive information is **not stored directly in the source code**.

### Environment Variables

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

### `.gitignore`

Sensitive environment files are excluded:

```text
.env
.env.*
!.env.example
```

Build output is also excluded:

```text
target/
```

IDE-specific files are excluded:

```text
.idea/
*.iml
.vscode/
```

### Security Rule

> Never commit database passwords, API keys, access tokens, private keys, or other sensitive credentials to GitHub.

---

# 🔄 Git Workflow

The repository is maintained using Git and GitHub.

Typical workflow:

```bash
git status

git add .

git commit -m "Week 4: Added FlyRank Auth"

git pull --rebase origin main

git push origin main
```

Using:

```bash
git pull --rebase origin main
```

helps integrate remote changes before pushing local commits.

---

# 📚 Learning Journey

This internship has provided hands-on experience across multiple areas of software engineering.

```text
AI Fluency
     │
     ▼
Prompt Engineering
     │
     ▼
Java Backend Development
     │
     ▼
Spring Boot
     │
     ▼
REST APIs
     │
     ▼
Database Integration
     │
     ▼
Spring Security
     │
     ▼
Authentication & Authorization
     │
     ▼
AI-assisted Backend Engineering
```

---

# 🚀 Career Focus

Through this internship, I am developing practical skills toward my career goal of becoming a:

> **Java Full Stack Developer | Generative AI Engineer**

My current technical focus includes:

- Java
- Spring Boot
- Spring Security
- REST APIs
- SQL
- PostgreSQL
- Spring JDBC
- Git & GitHub
- Generative AI
- AI-assisted Software Development
- Data Structures & Algorithms

---

# 👨‍💻 Author

## Achyuta Biswal

🎓 **B.Tech – Computer Science & Engineering**

💼 **Backend AI Engineering Intern – FlyRank**

### 📧 Email

**achyutabiswal977@gmail.com**

### 🐙 GitHub

**https://github.com/AchyutaBiswal**

### 💼 LinkedIn

**https://www.linkedin.com/in/achyuta-biswal**

---

# 🙏 Acknowledgements

Special thanks to the **FlyRank mentors and team** for providing an industry-oriented internship focused on:

- Backend Engineering
- AI-assisted Software Development
- Prompt Engineering
- Spring Boot
- Database Systems
- Authentication
- Security
- Professional Portfolio Development

---

# 🌟 Repository Highlights

- 📚 Weekly Internship Assignments
- ☕ Java Backend Development
- 🌱 Spring Boot Projects
- 🚀 REST API Development
- 🔐 Spring Security
- 🔑 BCrypt Password Hashing
- 👥 Authentication & Authorization
- 🗄️ SQLite Database Integration
- 🐘 PostgreSQL Integration
- ☁️ Supabase Integration
- 🔌 Spring JDBC
- 📄 Swagger / OpenAPI
- 🤖 AI-assisted Development
- 🧠 Prompt Engineering
- 🎨 Personal Branding
- 🖼️ Portfolio Image Curation
- 📝 Technical Documentation
- 🔧 Git & GitHub
- 📈 Continuous Learning

---

# ⭐ Support

If you find this repository useful or interesting, consider giving it a ⭐ on GitHub.

Thank you for visiting my **FlyRank Backend AI Engineering Internship** repository! 🚀

---

**© 2026 Achyuta Biswal — FlyRank Backend AI Engineering Internship**
