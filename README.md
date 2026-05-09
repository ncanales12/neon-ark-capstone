\# Neon Ark Capstone Project



Neon Ark is a creature intake tracking system built using Java, Spring Boot, PostgreSQL, Docker, and Gradle. The project allows users to manage creatures, habitats, feeding schedules, observations, and admin system data through both a REST API and a command line interface (CLI).



This project was created for the COSC 4301 Modern Programming capstone course.



\---



\# Features



\## Creature Management

\- View all creatures

\- View creature details by ID

\- Register new creatures

\- Rename creatures

\- Remove creatures



\## Observation System

\- View creature observations and notes

\- Track observation author and timestamps



\## Feeding Schedule System

\- Find creatures by feeding time

\- View feeding schedule information



\## Admin Features

\- View system users



\## Technical Features

\- REST API built with Spring Boot

\- PostgreSQL database integration

\- Docker container setup

\- Java CLI application

\- DTO response objects

\- Validation and HTTP status codes

\- CRUD operations



\---



\# Technologies Used



\- Java

\- Spring Boot

\- PostgreSQL

\- Docker

\- Gradle

\- IntelliJ IDEA



\---



\# Project Structure



```text

src/main/java/com/neonark/neonarkcapstone

│

├── controller

├── entity

├── repository

├── dto

├── cli

└── config

```



\---



\# Database Setup



The project uses PostgreSQL running inside Docker.



Start the database container:



```bash

docker start neon\_ark\_postgres

```



Verify the container is running:



```bash

docker ps

```



The PostgreSQL database runs on port:



```text

5439

```



\---



\# Running the Spring Boot API



Run the application from IntelliJ:



```text

NeonArkCapstoneApplication

```



The API runs on:



```text

http://localhost:8080

```



\---



\# Example API Endpoints



\## Get all creatures



```http

GET /api/creatures

```



\## Get creature by ID



```http

GET /api/creatures/3

```



\## Create creature



```http

POST /api/creatures

```



\## Rename creature



```http

PUT /api/creatures/{id}/name

```



\## Delete creature



```http

DELETE /api/creatures/{id}

```



\## Get creature observations



```http

GET /api/creatures/3/observations

```



\## Find feedings by time



```http

GET /api/feedings?time=08:00

```



\## View admin users



```http

GET /api/admin/users

```



\---



\# Running the CLI



Run the CLI application:



```text

com.neonark.neonarkcapstone.cli.Main

```



\---



\# CLI Features



\- List creatures

\- View creature by ID

\- Register creature

\- Rename creature

\- Remove creature

\- View observations

\- Find feedings by time

\- View system users

\- Exit confirmation prompts



\---



\# Validation and Error Handling



The API includes validation and proper HTTP status codes.



Examples:



\- `400 Bad Request`

\- `404 Not Found`

\- `409 Conflict`



\---



\# Example Test Cases



\## Invalid Creature ID



```text

GET /api/creatures/999

```



Returns:



```text

404 Not Found

```



\## Blank Creature Name



```json

{

&#x20; "name": ""

}

```



Returns:



```text

400 Bad Request

```



\## Duplicate Creature Name



```json

{

&#x20; "name": "Dragon"

}

```



Returns:



```text

409 Conflict

```



\---



\# Author



Nicolas Canales

