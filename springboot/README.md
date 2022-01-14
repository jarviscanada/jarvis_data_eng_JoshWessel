Table of contents
* [Introduction](#Introduction)
* [Quick Start](#Quick Start)
* [Implementation](#Implementation)
* [Test](#Test)
* [Deployment](#Deployment)
* [Improvements](#Improvements)

# Introduction



# Quick Start



# Implementation
## Architecture

![Image of Spring Boot Trading App Architecture](./assets/Springboot%20Architecture.png)

## REST API Usage
### Swagger



### Quote Controller



### Trader Controller



# Test

Testing occurred throughout the development of the project. Most of the components were first tested using integration tests.
These tests used JUnit to ensure each component operated as intended.
The application as a whole underwent several tests within the IDE and from the command line.

# Deployment

![Image of Spring Boot Docker Diagram](./assets/Trading%20App%20Docker%20Diagram.png)

# Improvements
Below are a few improvements to consider:
1. Implement OrderController to allow users to purchase and sell stocks.
2. Allow traders to have more than one account.
3. Add additional operations such as delete quote and update trader.