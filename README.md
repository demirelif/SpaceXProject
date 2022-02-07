# SpaceXProject

## How to run the program
You need Java 11 and maven to run this project. 

To run the back-end
```
mvn clean install 
mvn spring-boot:run
```
The spring boot will be started at localhost:8080

The source code for the user interface can be found [here](https://github.com/demirelif/SpaceXUI/).

To run the front-end
```
npm install
npm start 
```
The back-end checks the port the request comes from; therefore, the front-end will only be able to fetch the response from back-end by localhost:3000
