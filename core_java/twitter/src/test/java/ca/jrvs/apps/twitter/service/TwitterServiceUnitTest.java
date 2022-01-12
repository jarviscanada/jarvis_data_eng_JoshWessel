package ca.jrvs.apps.twitter.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.io.IOException;
import java.net.URISyntaxException;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService service;

  @Test
  public void postTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    when(dao.create(any())).thenReturn(new Tweet());
    service.postTweet(TweetUtil.buildTweet("test10",1.0,1.0));
  }

  @Test
  public void showTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    Tweet mockTweet = new Tweet();
    mockTweet.setText("mockTweet test");
    mockTweet.setId(1466091596424921089L);
    mockTweet.setId_str("1466091596424921089");

    when(dao.findById(any())).thenReturn(mockTweet);
    service.showTweet(mockTweet.getId_str(), null);
  }

  @Test
  public void deleteTweets() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
    Tweet mockTweet = new Tweet();
    mockTweet.setText("mockTweet_test");
    mockTweet.setId(1466091596424921089L);
    mockTweet.setId_str("1466091596424921089");

    Tweet mockTweet2 = new Tweet();
    mockTweet2.setText("mockTweet_test2");
    mockTweet2.setId(1466091544834936834L);
    mockTweet2.setId_str("1466091544834936834");

    String[] tweetsToDelete = { mockTweet.getId_str(), mockTweet2.getId_str() };

    when(dao.deleteById(mockTweet.getId_str())).thenReturn(mockTweet);
    when(dao.deleteById(mockTweet2.getId_str())).thenReturn(mockTweet2);
    when(dao.findById(mockTweet.getId_str())).thenReturn(mockTweet);
    when(dao.findById(mockTweet2.getId_str())).thenReturn(mockTweet2);
    service.deleteTweets(tweetsToDelete);
  }
}