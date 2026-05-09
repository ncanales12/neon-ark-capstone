\# Database Setup



The project uses PostgreSQL running inside Docker.



Start the database container:



```bash

docker start neon\_ark\_postgres

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

