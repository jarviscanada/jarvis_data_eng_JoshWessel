package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.dto.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterControllerIntTest {

  private final Logger logger = LoggerFactory.getLogger(TwitterControllerIntTest.class);

  TwitterController twitterController;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();

    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);

    TwitterDao dao = new TwitterDao(httpHelper);

    Service service = new TwitterService(dao);

    twitterController = new TwitterController(service);
  }

  @Test
  public void postTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    String[] strings = { "Testing Twitter REST API...[3]", "-1:1"};
    twitterController.postTweet(strings);
  }

  @Test
  public void showTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    String tweetID = "1466829404403904524";
    String[] args = { tweetID };
    Tweet tweet = twitterController.showTweet(args);
    logger.info("Tweet Info: text: " + tweet.getText() + ", coordinates: " + tweet.getCoordinates().getCoordinates().get(0) + ":" + tweet.getCoordinates().getCoordinates().get(1));
  }

  @Test
  public void deleteTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
    String[] idsToDelete = { "1466845369074081799", "1466845428247375874" };
    List<Tweet> deletedTweets = twitterController.deleteTweet(idsToDelete);
    for (Tweet tweet : deletedTweets) {
      logger.info("Deleted tweet: id: " + tweet.getId_str() + ", text: " + tweet.getText());
    }
  }
}