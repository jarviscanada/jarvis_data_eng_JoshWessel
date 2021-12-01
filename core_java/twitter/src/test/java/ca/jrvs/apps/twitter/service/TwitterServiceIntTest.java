package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.example.dto.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.io.IOException;
import java.net.URISyntaxException;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {

  CrdDao dao;

  TwitterService service;

  @Before
  public void setUp() throws Exception {
    TwitterHttpHelper httpHelper;
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
    dao = new TwitterDao(httpHelper);

    service = new TwitterService(dao);
  }

  @Test
  public void postTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    service.postTweet(TweetUtil.buildTweet("test9",50.0,0.0));
  }

  @Test
  public void showTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    Tweet returnedTweet = service.showTweet("1466091596424921089", null);
    System.out.println(JsonParser.toJson(returnedTweet, false, false));
  }

  @Test
  public void deleteTweets() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
    String[] tweetsToDelete = {"1466075978699087876", "1466076115848638473"};
    service.deleteTweets(tweetsToDelete);
  }
}