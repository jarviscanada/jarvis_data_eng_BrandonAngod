# Introduction
This application utilizes Twitters REST API in order to create, read, and delete tweets on twitter using HTTP Clients to send and receive data it. The program will deal with the API using HTTPHandler and handle the information by converting it into a variable. It will then return the user the information retrieved in clean into a clean and easy to read JSON file. The project is built using Maven and Tested using Mockito and Junit. After testing it was deployed to a docker container.

# Quick Start
- In order to package the program enter either command into the console
```
mvn clean install
    or 
mvn package
```
- To retrieve the program using docker enter the following command into the console.
```
docker container TODO
```

# Design
## UML diagram

![](/home/centos/dev/jarvis_data_eng_BrandonAngod/core_java/twitter/assets/1.PNG)

## Models
The main application calls upon TwitterHTTPHelper, TwitterDAO, TwitterService, and TwitterController class to handle requests by the user. Once the request from the user is received each class will work with one another to process the commands and output to user.
- TwitterHTTPHelper
  - Handles HTTP Requests and returns information recieved from each HTTP request
- TwitterDAO
  - Handles parses JSON data into a tweet variable
- TwitterService
  - Checks to make sure the information given matches Twitter's guidelines and no incorrect information is given.
- TwitterController
  - Proccesses input given to ensure the correct input is supplied and the correct amount of arguments are present. Then it will pass along required information to the TwitterService class.
## Spring
- How you managed the dependencies using Spring?

# Test
Tested using JUnit and Mockito. JUnit was mainly to perform integration tests while Mockito was used to unit test the code. Each test script is built to try out each method with example inputs and outputs to see if the program acts appropriately.
## Deployment
TODO

# Improvements
- Read replies of a tweet 
- Cleaner output that is easier for user to read
- Improve Documentation
