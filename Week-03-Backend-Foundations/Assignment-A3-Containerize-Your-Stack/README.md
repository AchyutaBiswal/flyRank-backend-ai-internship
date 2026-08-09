# FlyRank Week 3 BE-04 - Containerize Your Stack

## Overview

This project is the A2 Task API CRUD application updated for BE-04. The local in-memory/SQLite-style persistence has been replaced with PostgreSQL, and the full stack is containerized with Docker Compose.

The service and routes/controllers were kept unchanged; only the repository implementation/storage layer was switched.

## Architecture

Client

  ↓

Spring Boot Task API

  ↓

Repository Interface

  ↓

PostgreSQL Repository

  ↓

PostgreSQL Docker Container

  ↓

Persistent Docker Volume

The controller still exposes the same Task API routes. `TaskService` keeps the same public CRUD methods and delegates storage to the `TaskRepository` interface. `PostgresTaskRepository` implements that interface with Spring JDBC and parameterized SQL queries.

## Requirements

- Docker Desktop
- Docker Compose
- Java 21
- Maven wrapper included in this project

PostgreSQL does not need to be installed directly on Windows. Docker Compose starts PostgreSQL in a container.

## Environment

Create a local `.env` file for runtime database connection values:

```env
DB_URL=jdbc:postgresql://postgres:5432/taskdb
DB_USERNAME=postgres
DB_PASSWORD=postgres
```

`.env` is ignored by Git. `.env.example` is committed with placeholder values and can be copied when setting up the project. Do not put real secrets in the README or commit them to Git.

## Run

Start the complete stack:

```powershell
docker compose up --build
```

The API will be available at:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

## API

Existing endpoints:

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/` | API information |
| `GET` | `/health` | Health check |
| `GET` | `/tasks` | Get all tasks |
| `GET` | `/tasks/{id}` | Get one task by id |
| `POST` | `/tasks` | Create a task |
| `PUT` | `/tasks/{id}` | Update a task |
| `DELETE` | `/tasks/{id}` | Delete a task |

Create task request:

```powershell
Invoke-RestMethod -Method Post -Uri http://localhost:8080/tasks -ContentType "application/json" -Body '{"title":"Prove Docker volume persistence"}'
```

Update task request:

```powershell
Invoke-RestMethod -Method Put -Uri http://localhost:8080/tasks/1 -ContentType "application/json" -Body '{"title":"Updated task title","done":true}'
```

## Persistence Proof

1. Start the stack:

```powershell
docker compose up --build
```

2. Create a task using the existing API:

```powershell
Invoke-RestMethod -Method Post -Uri http://localhost:8080/tasks -ContentType "application/json" -Body '{"title":"Persistent task"}'
```

3. Retrieve tasks and confirm the created task exists:

```powershell
Invoke-RestMethod -Method Get -Uri http://localhost:8080/tasks
```

4. Stop containers:

```powershell
docker compose down
```

5. Start again:

```powershell
docker compose up
```

6. Retrieve tasks again and confirm the same task still exists:

```powershell
Invoke-RestMethod -Method Get -Uri http://localhost:8080/tasks
```

The `postgres_data` Docker named volume preserves PostgreSQL data across `docker compose down` and `docker compose up`.

Do not use this command during the persistence test:

```powershell
docker compose down -v
```

That command deletes the named volume and removes the saved database rows.

## Project Structure

- `Dockerfile` - Multi-stage Spring Boot application image
- `docker-compose.yml` - Spring Boot app, PostgreSQL database, healthcheck, and persistent volume
- `.env` - Local environment variables, ignored by Git
- `.env.example` - Placeholder environment file for setup
- `pom.xml` - Spring Boot, Java 21, JDBC, PostgreSQL, Swagger, and test dependencies
- `src/main/java` - Application source code
- `src/main/resources/application.properties` - Spring Boot configuration using environment variables
- `src/main/resources/schema.sql` - PostgreSQL table initialization

## Design Decision

The storage change is isolated behind `TaskRepository`. The controller continues to call `TaskService`, and the service keeps the same public methods used by the controller. Because the persistence implementation is now replaceable through the repository interface, switching from in-memory/SQLite-style storage to PostgreSQL did not require route changes or database code in the controller/service layers.
