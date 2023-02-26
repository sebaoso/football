# Football app
Teknik: Java 17, Spring Boot 2.7.8, 
Spring MVC, Spring Data JPA, H2, Swagger OpenAPI, JUnit, Lombok

## Kodbas
https://github.com/sebaoso/football.git

## Bygga
Maven:
mvn clean install

## Starta lokalt
I modulen fotboll-app:

mvn spring-boot:run

## URL till Football app GUI
http://localhost:8080/football

## OpenApi specifikation
http://localhost:8080/v3/api-docs

## Swagger-GUI Rest API
http://localhost:8080/swagger-ui/index.html

## In-memory databas (H2)
Konsol: http://localhost:8080/h2-console

URL: jdbc:h2:mem:testdb

Användarnamn: sa

Lösenord: password

## Test
Enhetstest Junit 5

Integrationstest GUI MockMvc

I modulen fotboll-test:

OpenApi-client test

(obs! football-app måste vara igång)

## GUI
Mustache: https://mustache.github.io

Bootstrap: https://getbootstrap.com




