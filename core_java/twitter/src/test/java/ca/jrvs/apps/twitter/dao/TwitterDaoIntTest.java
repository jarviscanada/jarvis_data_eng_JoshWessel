package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.io.IOException;
import java.net.URISyntaxException;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {

  TwitterHttpHelper httpHelper;

  TwitterDao dao;

  @Before
  public void setUp() throws Exception {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
    dao = new TwitterDao(httpHelper);
  }

  @Test
  public void create() throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    String text = "testing: " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    Tweet tweet = TweetUtil.buildTweet(text, lon, lat);
    tweet = dao.create(tweet);
    System.out.println(JsonParser.toJson(tweet, false, false));
  }

  @Test
  public void findById() throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    Tweet returnedTweet = dao.findById("1467203043787784195");
    System.out.println(JsonParser.toJson(returnedTweet, false, false));
  }

  @Test
  public void deleteById() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
    Tweet deletedTweet = dao.deleteById("1467203043787784195");
    System.out.println(JsonParser.toJson(deletedTweet, false, false));
  }
}