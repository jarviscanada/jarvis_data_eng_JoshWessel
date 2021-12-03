package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.example.dto.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterCLIApp {

  private final Logger logger = LoggerFactory.getLogger(TwitterCLIApp.class);

  Controller controller;

  TwitterCLIApp(Controller controller) { this.controller = controller; };

  public static void main(String[] args) throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {

    BasicConfigurator.configure();

    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
    TwitterDao dao = new TwitterDao(httpHelper);
    Service service = new TwitterService(dao);
    Controller controller = new TwitterController(service);
    TwitterCLIApp twitterCLIApp = new TwitterCLIApp(controller);

    twitterCLIApp.run(args);
  }

  public void run(String[] args) throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    if (validateArgs(args)) {
      String operation = args[0].toLowerCase();
      if (Objects.equals(operation, "post")) {
        String[] postArgs = { args[1], args[2] };
        printTweet(controller.postTweet(postArgs));
      }
      else if (Objects.equals(operation, "show")) {
        String[] showArgs = { args[1] };
        printTweet(controller.showTweet(showArgs));
      }
      else if (Objects.equals(operation, "delete")) {
        int numTweetsToDelete = args.length - 1;
        String[] tweetsToDelete = new String[numTweetsToDelete];
        for (int i = 0; i < numTweetsToDelete; i++) {
          tweetsToDelete[i] = args[i + 1];
        }
        controller.deleteTweet(tweetsToDelete).forEach(this::printTweet);
      }
    }
  }

  public boolean validateArgs(String[] args) {
    if (!args[0].equalsIgnoreCase("post")  && !args[0].equalsIgnoreCase("show") && !args[0].equalsIgnoreCase("delete")) {
      logger.error("Illegal operation. Available operations include post, show, and delete");
      return false;
    }
    if (Objects.equals(args[0].toLowerCase(), "post") && args.length != 3) {
      logger.error("Illegal number of arguments. Correct format: post [text] [longitude] [latitude]");
      return false;
    }
    else if (Objects.equals(args[0].toLowerCase(), "show") && args.length != 2) {
      logger.error("Illegal number of arguments. Correct format: show [tweet_id]");
      return false;
    }
    else if (Objects.equals(args[0].toLowerCase(), "delete") && args.length <= 1) {
      logger.error("Illegal number of arguments. Correct format: delete [tweet_id] ...");
      return false;
    }
    logger.info("Arguments are valid");
    return true;
  }

  private void printTweet(Tweet tweet) {
    try {
      logger.info(JsonParser.toJson(tweet, false, false));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Unable to convert Tweet to String", e);
    }
  }
}
