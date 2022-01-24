# CIKLUM Assignment Rock-paper-scissors App
Coding task: Write a program to play rounds of Rock, Paper, Scissors (https://en.wikipedia.org/wiki/Rock-paper-scissors).

Author: Daniel Gorski

This app is running at: https://roshambo-dgorski.appspot.com/

The Statistics micro-service is running at
        https://roshambo-stats-server.appspot.com/stats

## Running the Roshambo(Rock-paper-scissors) Application
The first step is to clone the GIT HUB repository locally.

* Clone the main application using git:
```
git clone  https://github.com/daniel77/roshambo.git
```

** You can also clone the stats micro-service using git:
```
git clone  https://github.com/daniel77/roshambo-stats.git
```

To run this application you will need java and maven installed/  
You can run it locally just by running the command:
```
mvn package
```
and then
```
java -jar target/roshambo-0.0.1-SNAPSHOT.jar
```
The application will run on port 8080.

You can also compile and run the application.
Using without tests:
```
mvn spring-boot:run
```

#### With Local Stats Microservice
In the roshambo-stats folder 
```
mvn spring-boot:run
```
Run it in default port 8081.
Then start roshambo app using :
```
mvn spring-boot:run -Dspring.profiles.active=local
```

## Using the Application with HTML locally 
Access the app with this url:
```
http://localhost:8080/ 
```
In this page you can play the game.
There are three buttons operations.
* Play Round
* Restart game
* General Stats

The user also can see how many rounds was played in the current game.
Every game has its own uuid to identify the game. 


## API Reference Documentation
The API documentation is available in these following urls:
* https://roshambo-dgorski.appspot.com/v2/api-docs
* http://localhost:8080/v2/api-docs

## Swagger Reference Documentation
The Swagger documentation is available in these following url:

* https://roshambo-dgorski.appspot.com/swagger-ui.html
* http://localhost:8080/swagger-ui.html

## Unit testing
The class RoshamboApplicationTests execute services/controllers tests with the test server running.
```
mvn test
```
The class GameServiceImplTest has Unit testing. 
The project has around 90% of testing coverage.

## Stats Microservice
This service has the stats of all played games using AtomicInteger to each field.

The repo:
* https://github.com/daniel77/roshambo-stats 

The stas are made by: 
- total rounds played
- total Wins for first players
- total Wins for second players
- total draws

The tests are done by RoshamboStatsApplicationTests and StatsServiceImplTest

Please check: https://roshambo-stats-server.appspot.com/stats

## Deploy Google Cloud App Engine.
The app engine was chosen to run this project in the cloud.
With the right credentials, you can build and deploy using:
* gcloud app deploy --project=roshambo-dgorski
* gcloud app deploy --project=roshambo-stats-server

