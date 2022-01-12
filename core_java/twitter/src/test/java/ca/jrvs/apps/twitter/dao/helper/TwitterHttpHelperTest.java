package ca.jrvs.apps.twitter.dao.helper;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.example.dto.HttpHelper;
import com.sun.jndi.toolkit.url.Uri;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

public class TwitterHttpHelperTest {

  HttpHelper twitterHttpHelper;

  @Before
  public void setUp() throws Exception {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");
    twitterHttpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
  }

  @Test
  public void httpPost() throws IOException, URISyntaxException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
    HttpResponse responsePost = twitterHttpHelper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=Testing"));
    System.out.println(EntityUtils.toString(responsePost.getEntity()));
  }

  @Test
  public void httpGet() throws IOException, URISyntaxException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
    HttpResponse responseGet = twitterHttpHelper.httpGet(new URI("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=JoshWessel4"));
    System.out.println(EntityUtils.toString(responseGet.getEntity()));
  }
}