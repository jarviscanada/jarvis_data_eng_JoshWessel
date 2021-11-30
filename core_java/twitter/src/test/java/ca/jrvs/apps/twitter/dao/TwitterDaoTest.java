package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import java.io.IOException;
import java.net.URISyntaxException;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoTest {

  TwitterDao dao;
  TwitterHttpHelper httpHelper;
  Tweet tweet;

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
    tweet = new Tweet();
    //tweet.setCreated_at("Tue Nov 30 12:42:20 +0000 2021");
    //tweet.setId(1465745451999809539L);
    //tweet.setId_str("1465738590508531716L");
    tweet.setText("This_is_Text");
    tweet.setCoordinates(null);
    tweet.setEntities(null);
    tweet.setFavorited(true);
    tweet.setFavorite_count(0);
    tweet.setRetweeted(false);
    tweet.setRetweet_count(0);

    tweet = dao.create(tweet);
    System.out.println(JsonParser.toJson(tweet, false, false));
  }

  @Test
  public void findById() throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    Tweet returnedTweet = dao.findById("1465760955325898758");
    System.out.println(JsonParser.toJson(returnedTweet, false, false));
  }

  @Test
  public void deleteById() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
    Tweet deletedTweet = dao.deleteById("1465760955325898758");
    System.out.println(JsonParser.toJson(deletedTweet, false, false));
  }
}