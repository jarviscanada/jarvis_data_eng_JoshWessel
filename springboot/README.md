Table of contents
* [Introduction](#Introduction)
* [Quick Start](#Quick-Start)
* [Implementation](#Implementation)
* [Test](#Test)
* [Deployment](#Deployment)
* [Improvements](#Improvements)
* [References](#References)

# Introduction

The SpringBoot project is a trading application framework that allows users to manage traders and tickers.
A PSQL database stores all traders, tickers, accounts, and security orders.
The application simulates a small stock exchange where traders can be set up and deleted, and quotes can be created and updated to match real-world data.
The user supplies at least one parameter specifying the desired command, followed by additional parameters.
The application was written in Java, using IntelliJ as the IDE.
It uses the Tomcat web servlet to allow the program to function as a web application.
It also uses the IEX Cloud to retrieve up-to-date quote data.
The testing process involved trial and error tests using JUnit to test each component.
The application was cleaned and packaged using Maven and deployed using Docker.

# Quick Start

The following steps start the application.

1. Setup Docker network
```Bash
sudo docker network create trading-net
```

2. Create Docker files
```Bash
cd ./springboot
```
Create a Docker file using the following code:
```dockerfile
#
# Build stage
#
FROM maven:3.6-jdk-8-slim AS build
COPY src /build/src
COPY pom.xml /build/
RUN mvn -f /build/pom.xml clean package -DskipTests

#
# Package stage
#
FROM openjdk:8-alpine
COPY --from=build /build/target/trading-1.0-SNAPSHOT.jar /usr/local/app/trading/lib/trading_app.jar
ENTRYPOINT ["java","-jar","/usr/local/app/trading/lib/trading_app.jar"]
```
```Bash
cd ./springboot/psql
```
Create a Docker file using the following code:
```dockerfile
FROM postgres:9.6-alpine
COPY ./init_db.sql /docker-entrypoint-initdb.d/
COPY ./schema.sql /docker-entrypoint-initdb.d/
```

3. Setup Images
```Bash
cd ./springboot/psql
docker build -t trading-psql
cd ./springboot
docker build -t trading-app
```

4. Set environment variables
```Bash
export IEX_PUB_TOKEN=[iex_token]; export PSQL_USER=postgres; export PSQL_PASSWORD=[password]; export PSQL_HOST=localhost; export PSQL_PORT=[port]; export PSQL_DB=jrvstrading
```

5. Setup and run Docker containers
```Bash
docker run --name trading-psql-dev -e POSTGRES_PASSWORD=${PSQL_PASSWORD} -e POSTGRES_DB=${PSQL_DB} -e POSTGRES_USER=${PSQL_USER} --network trading-net -d -p 5432:5432 trading-psql
docker run --name trading-app-dev -e "PSQL_URL=jdbc:postgresql://trading-psql-dev:5432/${PSQL_DB}" -e "PSQL_USER=${PSQL_USER}" -e "PSQL_PASSWORD=${PSQL_PASSWORD}" -e "IEX_PUB_TOKEN=${IEX_PUB_TOKEN}" --network trading-net -p [port]:[port] -t trading-app [command]
```

6. Run application with command
```Bash
java -jar [path]/trading-1.0-SNAPSHOT.jar [command] [...]
```

7. (Optional) Use application through Swagger UI

To use the application using Swagger UI, start the application using the steps above (use the "help" command to avoid modifying the database).
Without shutting down the application, navigate to http://localhost:8080/swagger-ui.html#/ in your browser.
Use the "try it out" button in the desired endpoint, fill in any necessary parameters, and finally, press execute.
All endpoints in the Swagger UI will remain available until the application is shut down (by pressing Ctrl+c on the command line).

![Image of Swagger UI](./assets/Swagger%20UI.PNG)

# Implementation
## Architecture

![Image of Spring Boot Trading App Architecture](./assets/Springboot%20Architecture.png)

### Controller Layer

The controller layer handles user requests.
The application uses two controllers: TraderAccountController and QuoteController.
These controllers handle the command provided by the user and call the corresponding service layer method.

### Service Layer

The service layer handles business logic.
The application uses two service layer components: TraderAccountService and QuoteService.
The service layer handles input from the controller layer and calls the corresponding data access layer methods.
Between the two service layer components, they use six different DAO objects.

### DAO Layer

The DAO layer handles database interaction.
DAO objects use SQL commands to perform CRUD operations on the database by converting SQL objects into Java objects and vice versa.
The application uses six data access objects: TraderDao, AccountDao, SecurityOrderDao, PositionDao, QuoteDao, and MarketDataDao.

### Springboot: Webservlet/Tomcat and IoC

The SpringBoot application uses Tomcat as its web servlet. Tomcat allows the application to function as a web application.
It allows the application's functionality to be accessed using a URI (http://localhost:8080) instead of exclusively through the command line.

The Spring IoC container is responsible for setting up the application's components marked as beans.
Annotations throughout the program provide the IoC container with the instructions for setting up the beans.

### PSQL & IEX

A PSQL database stores all data.
The trading-psql Docker image creates the database using a SQL script.
A second SQL script is also employed to set up four tables and one view.
The four tables include trader, account, quote, and security_order. The view is a position view, which displays data from the security_order table.

IEX is a REST API that provides real-world quote data.
When updating existing tickers in the quote table, the up-to-date data from the IEX cloud is retrieved. It is then saved as an IexQuote object then converted to a Quote object.
The Quote object then replaces the existing Quote object in the quote table.

## REST API Usage
### Swagger

Swagger is an online resource that allows users to interact with an API.
In this project, Swagger serves as a method for testing endpoints.
Swagger successfully tested each of the implemented endpoints.

### Quote Controller

The quote controller handles user requests related to the quote table in the psql database.
Quote data is retrieved from the IEX cloud, then converted into a quote object. The quote object then replaces the existing quote object stored in the quote table.

Endpoints:
- GET `/quote/dailyList`: List all securities in the quote table.
- GET `/quote/iex/ticker/{ticker}`: Display an IexQuote object for a specified ticker.
- POST `/quote/tickerId/{tickerId}`: Add a new ticker to the quote table.
- PUT `/quote/`: Update a quote in the quote table.
- PUT `/quote/iexMarketData`: Update quotes in the quote table using IEX data.

### Trader Controller

The trader controller handles user requests related to the trader table in the psql database.
It creates and deletes traders and makes deposits and withdrawals to and from the trader's account.

Endpoints:
- DELETE `/trader/traderId/{traderId}`: Delete a trader from the trader table.
- POST `/trader/`: Create a trader and an account.
- POST `/trader/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}`: Create a trader and an account.
- PUT `/trader/deposit/traderId/{traderId}/amount/{amount}`: Deposit a fund.
- PUT `/trader/withdraw/traderId/{traderId}/amount/{amount}`: Withdraw a fund.

# Test

Testing occurred throughout the development of the project.
Most of the components first underwent integration tests using JUnit to ensure each operated as intended.
The application underwent several tests within IntelliJ and from the command line to ensure the components interacted with each other correctly.
Finally, Swagger successfully tested each of the implemented endpoints.

Below is the code coverage for the project.

![Image of code coverage](./assets/Code%20Coverage.png)

# Deployment

![Image of Spring Boot Docker Diagram](./assets/Trading%20App%20Docker%20Diagram.png)

As shown in the image above, the application uses two images: trading-psql and trading-app.
The trading-psql image sets up the psql database. It runs two SQL scripts specified in the Docker file.
These SQL scripts first set up the database and then create the tables.
The openjdk:8-alpine and maven:3.6-jdk-8-slim images serve as base images for the trading-app image. The trading-app image compiles and packages the code using maven before starting the application.

# Improvements
Below are a few improvements to consider:
1. Implement OrderController to allow users to purchase and sell stocks.
2. Allow traders to have more than one account.
3. Add additional operations such as delete quote and update trader.

# References

[1] Fol, Pavel. (2020) _Java Basics: What is Apache Tomcat?_. JRebel. https://www.jrebel.com/blog/what-is-apache-tomcat

[2] Johnson, R., Hoeller, J., Donald, K., Sampanaleanu, C., Harrop, R., Risberg, T., Arendsen, A., Davison, D., Kopylenko, D., Pollack, M.,
Templier, T., Vervaet, E., Tung, P., Hale, B., Colyer, A., Lewis, J., Leau, C., Fisher, M., Brannen, S.,
... Webb, P. (2016) _The IoC container_. Spring Framework Reference Documentation. https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/beans.html