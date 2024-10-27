## Build Instructions

The application can be built with the following Maven command

`mvn clean install`

The Application can be started via the following Maven Spring Boot command

`mvn spring-boot:start`

This application will deploy onto the localhost of your machine and port 8080.
Requests can be made to the service via the endpoint `/api/user/{user}`

Example: `GET http://localhost:8080/api/user/octocat`