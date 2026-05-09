\# Neon Ark Capstone Project



Neon Ark is a creature intake tracking system built with Spring Boot, PostgreSQL, and Java. The project allows users to manage creatures, habitats, feeding schedules, observations, and admin system data through both a REST API and a command line interface (CLI).



This project was created for the COSC 4301 Modern Programming capstone course.



\## Features



\- View all creatures

\- View creature details by ID

\- Register new creatures

\- Rename creatures

\- Remove creatures

\- View creature observations and notes

\- Find creatures by feeding time

\- View admin system users

\- PostgreSQL database integration

\- REST API endpoints

\- Java CLI application

\- DTO response objects

\- Validation and HTTP status codes



\## Technologies Used



\- Java

\- Spring Boot

\- PostgreSQL

\- Docker

\- Gradle

\- IntelliJ IDEA



\## Database



The project uses PostgreSQL running in Docker.



Example Docker container:



```bash

docker start neon\_ark\_postgres

