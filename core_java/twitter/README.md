# Introduction

The Twitter application allows users to post, show, and delete tweets from a command line.
It uses `HttpClient` and Twitter's REST API.
The application requires the user to supply various parameters, including consumer key and secret, access token and token secret, the Docker image, and the desired command followed by command-specific arguments.
The application was written in Java, using IntelliJ as the IDE.
The testing process involved trial and error tests using JUnit and Mockito to test each component.
The application was cleaned and packaged using Maven and deployed using Docker.

# Quick Start

The following Maven command cleans and packages the application:
```bash
mvn clean package -DskipTests
```

The following Docker command runs the application:
```bash
docker run --rm -e consumerKey=[consumer_key] -e consumerSecret=[consumer_secret] -e accessToken=[access_token] -e tokenSecret=[token_secret] [docker_image] "[post|show|delete]" "[tweet_text]" "[longitude:latitude]"
```

# Design
## UML Diagram

![Image of Twitter UML Diagram](./assets/Twitter%20UML%20Diagram.png)

As shown in the diagram above, the application uses five layers:
1. HTTP Helper
2. DAO (Data Access Object)
3. Service
4. Controller
5. CLI App

Each layer is dependent on the previous layer.
For example, The Controller layer is dependent on the Service layer, which is dependent on the Data Access layer, etc.
Below is a description of each layer.

1. `TwitterHttpHelper`

`TwitterHttpHelper` implements `HttpHelper`.
It first sets up a consumer using the consumer key, consumer secret, access token, and token secret.
It then creates an HTTP request using a given URI.
Finally, it signs and executes the request.

2. `TwitterDao`

`TwitterDao` implements `CrdDao` and is dependent on `TwitterHttpHelper`.
It either takes in the `Tweet` object to post or the id(s) of the tweet(s) to be shown or deleted.
It then creates a URI and passes it to the TwitterHttpHelper.

3. `TwitterService`

`TwitterService` implements `Service` and is dependent on `TwitterDao`.
It calls the appropriate `TwitterDao` methods to perform the desired operation.
Before returning the tweet, each method also validates the `Tweet` object.

4. `TwitterController`

`TwitterController` implements `Controller` and is dependent on `TwitterService`.
It processes the arguments provided by the user before calling the appropriate service method.
It also performs some validation on the given arguments.

5. `TwitterCLIApp`

`TwitterCLIApp` is dependent on `Controller`.
It includes a `main` method, which creates an instance of all five components, including itself.
It then calls a `run` method.
The `run` method first validates, then processes the arguments and calls the appropriate `Controller` method.

## Models

The tweet model is a simplified version of Twitter's tweet model.
It includes the following fields, each with getters and setters:

- `created_at`
- `id`
- `id_str`
- `text`
- `entities`
- `coordinates`
- `retweet_count`
- `favorite_count`
- `favorited`
- `retweeted`

Each command uses at least one tweet object to store the necessary information for posting, searching, and deleting tweets.
Commands do not necessarily use all fields. For example, the tweet object used in the post command only uses the `text` and `coordinates` fields.

## Spring

The spring framework provided dependency management using the `@ComponentScan` approach.
This approach uses the `@Component` annotation (or a specialized form) to mark classes as beans.
It also uses the `@Autowired` annotation ahead of each component's constructor to tell the IoC container to inject dependencies using the class's constructor.

# Test

The application used JUnit and Mockito to perform tests.
Each component's variation of the post, show, and delete methods was tested using JUnit and Mockito.
IntelliJ's debugger provided a helpful resource for identifying and correcting errors.
The `main` method in the `TwitterCLIApp` also provided a resource for testing the application.

## Deployment

The application was packaged using Maven and deployed using Docker.
Using Maven, the application was cleaned, compiled, and packaged.
The deployment of the application through Docker involved the creation of a Docker image, now stored on Docker Hub.

# Improvements

Below are a few improvements to consider.

1. Improve the show feature to allow users to search for tweets by fields other than the id field, such as tweet text or creation date.
2. Allow users to interact with tweets following a show command, such as the option to reply, favorite, or retweet.
3. Improve how tweets are displayed to the user to make the tweet information easier to read.