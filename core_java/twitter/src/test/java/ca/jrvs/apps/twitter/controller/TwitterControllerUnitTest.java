package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

  @Mock
  TwitterService service;

  @InjectMocks
  TwitterController twitterController;

  @Test
  public void postTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    String[] strings = { "Testing Twitter REST API...[3]", "-1:1"};
    when(service.postTweet(any())).thenReturn(new Tweet());
    twitterController.postTweet(strings);
  }

  @Test
  public void showTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    Tweet mockTweet = new Tweet();
    mockTweet.setText("mockTweet test");
    mockTweet.setId(1466845487600914434L);
    mockTweet.setId_str("1466845487600914434");

    String tweetID = "1466845487600914434";
    String[] args = { tweetID };

    when(service.showTweet(any(), any())).thenReturn(mockTweet);
    twitterController.showTweet(args);
  }

  @Test
  public void deleteTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
    Tweet mockTweet = new Tweet();
    mockTweet.setText("mockTweet test");
    mockTweet.setId(1466845487600914434L);
    mockTweet.setId_str("1466845487600914434");

    Tweet mockTweet2 = new Tweet();
    mockTweet2.setText("mockTweet2 test");
    mockTweet2.setId(1466845487600914435L);
    mockTweet2.setId_str("1466845487600914435");

    List<Tweet> mockTweets = new ArrayList<>();
    mockTweets.add(mockTweet);
    mockTweets.add(mockTweet2);

    String[] idsToDelete = { "1466845369074081799", "1466845428247375874" };

    when(service.deleteTweets(any())).thenReturn(mockTweets);
    twitterController.deleteTweet(idsToDelete);
  }
}