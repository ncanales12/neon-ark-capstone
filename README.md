# Neon Ark Capstone

A Java Spring Boot REST API and CLI application for managing creatures, feeding schedules, observations, and system users inside the Neon Ark facility.

This project was built for the COSC 4301 Modern Programming Capstone assignment.

---

# Features

## Creature Management
- List all creatures
- View creature by ID
- Register new creatures
- Rename creatures
- Soft delete creatures using REMOVED status
- Track ACTIVE and REMOVED creature states

## Observations
- View creature observations and notes
- Add timestamped observations tied to creatures

## Feeding Schedules
- Search feedings by time
- Handle empty feeding schedules
- Validate invalid feeding inputs

## Admin System Users
- View system users
- Store:
    - Full name
    - Email
    - Phone number
    - Role

## CLI Features
- Professional table formatting
- Input validation
- Confirmation prompts
- Error handling
- Menu-driven interface

---

# Technologies Used

## Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate

## Database
- PostgreSQL 16
- Docker

## Tools
- IntelliJ IDEA
- Gradle
- PowerShell
- GitHub

---

# Project Structure

```text
src/main/java/com/neonark/neonarkcapstone
│
├── cli
├── controller
├── dto
├── entity
├── repository
```

---

# Database Setup

## Start PostgreSQL Container

```powershell
docker run --name neon_ark_postgres `
  -e POSTGRES_PASSWORD=postgres `
  -e POSTGRES_DB=neonark `
  -p 5439:5432 `
  -d postgres:16
```

## Verify Container

```powershell
docker ps
```

---

# Application Configuration

## application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5439/neonark
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# Running the Application

## Start Spring Boot

Run:

```text
NeonArkCapstoneApplication.java
```

Expected startup:

```text
Tomcat started on port 8080
Started NeonArkCapstoneApplication
```

---

# Running the CLI

Run:

```text
Main.java
```

Main menu:

```text
1. List all creatures
2. View creature by ID
3. Register new creature
4. Rename creature
5. Remove creature
6. View creature observations/notes
7. Find creatures by feeding time

--- Admin Only ---
8. View all system users

0. Exit
```

---

# API Endpoints

## Creatures

### Get All Creatures

```http
GET /api/creatures
```

### Get Creature By ID

```http
GET /api/creatures/{id}
```

### Register Creature

```http
POST /api/creatures
```

### Rename Creature

```http
PUT /api/creatures/{id}/name
```

### Soft Delete Creature

```http
DELETE /api/creatures/{id}
```

---

## Observations

### Get Creature Observations

```http
GET /api/creatures/{id}/observations
```

### Add Observation

```http
POST /api/creatures/{id}/observations
```

---

## Feedings

### Search Feedings By Time

```http
GET /api/feedings?time=08:00
```

---

## Admin Users

### Get All Users

```http
GET /api/admin/users
```

### Create User

```http
POST /api/admin/users
```

---

# PowerShell API Testing

## Get Creatures

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/creatures" -Method GET
```

## Get Feedings

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/feedings?time=08:00" -Method GET
```

## Get Admin Users

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/admin/users" -Method GET
```

---

# Validation and Error Handling

The application handles:

- Invalid creature IDs
- Duplicate creature names
- Invalid feeding times
- Empty requests
- Cancelled rename/delete operations
- Soft delete behavior

Example responses:

```text
Not found.
```

```text
Conflict. That creature name already exists.
```

```text
Bad request. Check your input.
```

---

# Soft Delete Behavior

Creatures are not permanently removed from the database.

Deleting a creature changes the status to:

```text
REMOVED
```

The creature can still be viewed later for tracking and audit purposes.

---

# Screenshots

The project includes screenshots showing:

- CLI functionality
- API testing
- PostgreSQL Docker container
- Spring Boot startup
- IntelliJ project structure
- Validation handling
- Admin users
- Feeding schedules
- Soft delete behavior

---

# Author

Nicolas Canales

COSC 4301 - Modern Programming Capstone

Spring 2026