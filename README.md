# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

REALESTATE LOAN APPLICATION

https://github.com/rp8888/RealEstateLoanApplication


### Guides
The following guides illustrate how to use some features concretely:
Import the project in eclipse or intellij
--->Build the project. Command: mvn package
--->and run the project as Springboot application

--->you can also use mvn spring-boot:run command in cmd

Application runs on localhost and the url is http://localhost:8080/swagger-ui.html

Application designed as a springboot application and the database is dynamoDB.
After the project is comitted to the GIT hub, it is automatically build and deployed in AWS.
Codepipeline is used to do this CI/CD setup. the code is deployed using AWS Codedeploy and the application is deployed on EBS.

Important points while testing the services
1) First do the customer registration
2) using the same emailId and password, login into the application and use the access token in the response to acccess the application endpoints for registering and viewing the application.
3) While filling the application please give the same emailId used for registration in the request. If no email id is present then the application will not get saved.
4) use logout service to logout of the application

Refresh access token details are stored in the database to get the values after the access token is expired.

