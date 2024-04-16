# DubDrop-Network

## Dev Database
- Create a database in your local machine
- Use the following credentials to connect to the database
  - Database Name: trocaDataBase
  - Username: postgres
  - Password: root
  - Host: localhost
  - Port: 3008
- Run the following command to create a docker image
  - `docker run --name trocaDataBase -e POSTGRES_PASSWORD=root -e POSTGRES_USER=postgres -e POSTGRES_DB=trocaDataBase -p 3008:5432 -d postgres`
  
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

```properties
spring_profiles_active = **   
PROD_DB_HOST= **
PROD_DB_PORT= **
PROD_DB_NAME= **
PROD_DB_USERNAME= **
PROD_DB_PASSWORD= **
```
