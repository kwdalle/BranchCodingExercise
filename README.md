## Build Instructions

The application can be built with the following Maven command

`mvn clean install`

The Application can be started via the following Maven Spring Boot command

`mvn spring-boot:start`

This application will deploy onto the localhost of your machine and port 8080.
Requests can be made to the service via the endpoint `/api/user/{user}`

Example: `GET http://localhost:8080/api/user/octocat`

## Discussion/Reasoning

For this exercise I located the main logic within the Controller class GitHubController.java, from this class
the calling of the APIs was done via a Retrofit implementation that is generated by Retrofit (an easy to set up and use
API client library) and adherent to the interface GitHubUserService.java. Results of these API calls are held within the
two DTOs GitHubUserInfo.java and GitHubUserRepoInfo.java which are then used as part of the interface of the Retrofit
service Interface. GitHubUserResponse.java is the returning Object of the Spring Boot Applications only endpoint and is
created from the two Retrofit service Objects. Lastly, there is a single configuration class GitHubConfiguration.java
that provides the Bean of the GitHubUserService.java to be injected into the controller.

I chose to use Retrofit as my API library due to its easiness to set up and get running, as well as my familiarity of
working with it in my current projects.