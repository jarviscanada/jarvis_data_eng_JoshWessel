package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;

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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDao dao;

//  @Before
//  public void setUp() throws Exception {
//    String CONSUMER_KEY = System.getenv("consumerKey");
//    String CONSUMER_SECRET = System.getenv("consumerSecret");
//    String ACCESS_TOKEN = System.getenv("accessToken");
//    String TOKEN_SECRET = System.getenv("tokenSecret");
//
//    mockHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
//    dao = new TwitterDao(mockHelper);
//  }

  @Test
  public void create() throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    String text = "testing";
    Double lat = 1d;
    Double lon = -1d;

    TwitterDao spyDao = Mockito.spy(dao);

    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      Tweet tweet = TweetUtil.buildTweet(text, lon, lat);
      tweet = spyDao.create(tweet);
      System.out.println(JsonParser.toJson(tweet, false, false));
    } catch (RuntimeException e) {
      assertTrue(true);
    }
  }

  @Test
  public void findById() throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    TwitterDao spyDao = Mockito.spy(dao);
    when(mockHelper.httpGet(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      Tweet returnedTweet = spyDao.findById("1466065191100162049");
      System.out.println(JsonParser.toJson(returnedTweet, false, false));
    } catch (RuntimeException e) {
      assertTrue(true);
    }
  }

  @Test
  public void deleteById() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
    TwitterDao spyDao = Mockito.spy(dao);
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      Tweet deletedTweet = spyDao.deleteById("1466065191100162049");
      System.out.println(JsonParser.toJson(deletedTweet, false, false));
    } catch (RuntimeException e) {
      assertTrue(true);
    }
  }
}