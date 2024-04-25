# TROCA

## Overview

>TROCA
: is a digital ticketing platform that operates through a website. We serve as an intermediary between buyers and resellers of electronic music event tickets. Our platform provides users with a secure, straightforward, and efficient environment to explore a variety of ticket options and prices.

## Features
- **Secure Environment:** We prioritize user safety and ensure that transactions are conducted securely.
- **Simplicity:** Our user-friendly interface makes it easy for users to navigate and find the tickets they need.
- **Variety:** Explore a wide range of ticket options for electronic music events.
- **Competitive Pricing:** Compare prices and choose the best deal for your favorite events.

Join us today and experience the convenience of TROCA! 🎵🎟️

## Members
- Adriana Brizuela 
- Agustin Lorca https://www.linkedin.com/in/agustin-lorca/
- Ivan Rojas https://www.linkedin.com/in/ivanrojasorg/
- Matias Aaron 
- Nestor Frias
- Santiago Vergara https://www.linkedin.com/in/santiago-vergara-87b4b9233/



## Dev Database
- Create a database in your local machine
- Use the following credentials to connect to the database
  - Database Name: trocaDataBase
  - Username: postgres
  - Password: root
  - Host: localhost
  - Port: 3008
- Run the following command to create a docker image
  ```bash
  docker run --name trocaDataBase -e POSTGRES_PASSWORD=root -e POSTGRES_USER=postgres -e POSTGRES_DB=trocaDataBase -p 3008:5432 -d postgres
  ```
  
## Dev Server
- Run maven install to build the project
- Run the following command to start the server
  - `mvn spring-boot:run`
  - The server will start on port 8080
  - The server will start on the following URL: http://localhost:8080/api/v1/troca

## Swagger
- The swagger documentation can be accessed at the following URL
  - http://localhost:8080/api/v1/troca/swagger-ui/index.html

## PROD Server
- Run maven install to build the project
- Run the following command to start the server
  - `mvn spring-boot:run`

For more information, visit our official website or check out our GitHub repository. You can also catch us on the Disc Golf Network. 🚀🎉


```properties
spring_profiles_active = **   
PROD_DB_HOST= **
PROD_DB_PORT= **
PROD_DB_NAME= **
PROD_DB_USERNAME= **
PROD_DB_PASSWORD= **
```
