# JsonAPIGaterway
Problem Statement:
Your task is to write a script or app, which gathers data from two endpoints asynchronously, merges the responses and displays them in any way, for example as JSON response from an REST API.

For example you could use these two endpoints:

http://jsonplaceholder.typicode.com/users/1 to obtain a user's data
http://jsonplaceholder.typicode.com/posts?userId=1 to obtain all comments written by that user

Solution:
This is Spring Boot application. It has only one endpoint.
GET /users/{userId}

Calls the following endpoints at http://jsonplaceholder.typicode.com API and combines their results into a single JSON output:

    http://jsonplaceholder.typicode.com/users/{userId}
    http://jsonplaceholder.typicode.com/posts?userId={userId}

How to run:

    mvn spring-boot:run
    go to http://localhost:8080/users/2 to see result for user with id 2

Technologies used:
    Spring Boot, Spring Web , Devtools, Swagger(Since latest spring boot app is not compatible with Swagger, used one previous version of Spring Boot)
    Tomcat 
    Maven
    Rest Template 
    Async calls
    Junit and Mockito(Tested using Swagger, Postman also)
