## Short Information About Project

This is a sample project to demonstrate a sales person flow on e-commerce domain. 
This project uses below technologies: 

* Maven
* Spring Boot
* PostgreSQL DB
* Redis
* Spring Data
* Spring Security

## Running Application
To run this application please follow instructions

1. First of all, create the jar of application. After cloning this project ,run the mvn clean install command to get jar of application. 
2. Then, create docker image. Navigate your path to folder which contains Dockerfile and run this command: docker build -t  ecommercepurchase:latest .  
3. Then, navigate your path to path which contains docker-compose.yml file and run this command: docker compose up -d 
   > The docker compose file contains the PostgreSQL and Redis dependencies. When you run it, you'll have the PostgreSQL and Redis.
