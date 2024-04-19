#IMAGE
FROM maven:3.8.5-openjdk-17 AS build
#JAR
COPY src /home/app/src
COPY pom.xml /home/app
#COMANDO DE INICIO
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /home/app/target/mvp-p2p-ticket-sells-0.0.1-SNAPSHOT.jar /usr/local/lib/java-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/java-app.jar"]