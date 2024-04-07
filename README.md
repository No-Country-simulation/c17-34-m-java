# DubDrop-Network

## Dev Database
- Create a database in your local machine
- Use the following credentials to connect to the database
  - Database Name: DubDropDataBase
  - Username: postgres
  - Password: root
  - Host: localhost
  - Port: 3008
- Run the following command to create a docker image
  - `docker run --name DubDropDataBase -e POSTGRES_PASSWORD=root -e POSTGRES_USER=postgres -e POSTGRES_DB=dubdropdatabase -p 3008:5432 -d postgres`
  
## Dev Server
- Run maven install to build the project
- Run the following command to start the server
  - `mvn spring-boot:run`
  - The server will start on port 8080
  - The server will start on the following URL: http://localhost:8080/api/v1/dubdrop

## Swagger
- The swagger documentation can be accessed at the following URL
  - http://localhost:8080/api/v1/dubdrop/swagger-ui/index.html