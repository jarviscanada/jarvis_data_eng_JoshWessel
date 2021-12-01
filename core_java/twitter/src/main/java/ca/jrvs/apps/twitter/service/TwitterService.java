package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterService implements Service {

  private CrdDao dao;

  @Autowired
  public TwitterService(CrdDao dao) { this.dao = dao; }

  /**
   * Validate and post a user input Tweet
   *
   * @param tweet tweet to be created
   * @return created tweet
   * @throws IllegalArgumentException if text exceed max number of allowed characters or lat/long
   *                                  out of range
   */
  @Override
  public Tweet postTweet(Tweet tweet) throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    validatePostTweet(tweet);
    return (Tweet) dao.create(tweet);
  }

  private void validatePostTweet(Tweet tweet) {
    if (tweet.getText().length() > 140) {
      throw new IllegalArgumentException("Tweet text exceeds maximum of 140 characters");
    }
    if (tweet.getCoordinates().getCoordinates().get(0) > 180
        || tweet.getCoordinates().getCoordinates().get(0) < -180
        || tweet.getCoordinates().getCoordinates().get(1) > 90
        || tweet.getCoordinates().getCoordinates().get(1) < -90) {
      throw new IllegalArgumentException("Invalid coordinates");
    }
  }

  /**
   * Search a tweet by ID
   *
   * @param id     tweet id
   * @param fields set fields not in the list to null
   * @return Tweet object which is returned by the Twitter API
   * @throws IllegalArgumentException if id or fields param is invalid
   */
  @Override
  public Tweet showTweet(String id, String[] fields) throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    Tweet tweet = (Tweet) dao.findById(id);
    validateShowTweet(tweet);

    if (fields != null) {
      List<String> fieldsList = Arrays.asList(fields);
      if (!fieldsList.contains("created_at")) { tweet.setCreated_at(null); }
      if (!fieldsList.contains("id")) { tweet.setId(null); }
      if (!fieldsList.contains("id_str")) { tweet.setId_str(null); }
      if (!fieldsList.contains("text")) { tweet.setText(null); }
      if (!fieldsList.contains("entities")) { tweet.setEntities(null); }
      if (!fieldsList.contains("coordinates")) { tweet.setCoordinates(null); }
      if (!fieldsList.contains("retweet_count")) { tweet.setRetweet_count(null); }
      if (!fieldsList.contains("favorite_count")) { tweet.setFavorite_count(null); }
      if (!fieldsList.contains("favorited")) { tweet.setFavorited(null); }
      if (!fieldsList.contains("retweeted")) { tweet.setRetweeted(null); }
    }
    return tweet;
  }

  private void validateShowTweet(Tweet tweet) {
    if (tweet.getId_str().length() != 19) {
      throw new IllegalArgumentException("Invalid Tweet ID");
    }
  }

  /**
   * Delete Tweet(s) by id(s).
   *
   * @param ids tweet IDs which will be deleted
   * @return A list of Tweets
   * @throws IllegalArgumentException if one of the IDs is invalid.
   */
  @Override
  public List<Tweet> deleteTweets(String[] ids) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
    List<Tweet> tweets = new ArrayList<>();
    for (String id : ids) {
      Tweet tweetToTest = (Tweet) dao.findById(id);
      validateShowTweet(tweetToTest);
      tweets.add((Tweet) dao.deleteById(id));
    }
    return tweets;
  }
}
