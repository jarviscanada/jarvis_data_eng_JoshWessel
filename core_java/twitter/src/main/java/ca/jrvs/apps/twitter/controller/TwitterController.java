package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.Arrays;
import java.util.stream.Stream;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.apache.tomcat.util.http.fileupload.MultipartStream.IllegalBoundaryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

public class TwitterController implements Controller {

  private final Logger logger = LoggerFactory.getLogger(TwitterController.class);

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private Service service;

  public TwitterController(Service service) { this.service = service; };

  private boolean validateTweetID(String id) {
    for (int i = 0; i < id.length(); i++) {
      if (!Character.isDigit(id.charAt(i))) {
        throw new IllegalArgumentException("Invalid ID. ID cannot contain non-digit values.");
      }
    }
    return true;
  }

  /**
   * Parse user argument and post a tweet by calling service classes
   *
   * @param args
   * @return a posted tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet postTweet(String[] args) throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {

    if (args.length != 2) {
      throw new IllegalArgumentException("Illegal number of arguments. Correct format: \"text[String]\",\"longitude[Double]:latitude[Double]\"");
    }
    if (!args[1].contains(":")) {
      throw new IllegalArgumentException("Coordinates must contain ':'. Correct format: \"text[String]\",\"longitude[Double]:latitude[Double]\"");
    }

    String text = args[0];

    String coords = args[1];
    String[] coordinates = coords.split(COORD_SEP);
    Double lon = Double.parseDouble(coordinates[0]);
    Double lat = Double.parseDouble(coordinates[1]);

    logger.info("Tweet Info: text: " + text + ", lon: " + lon+ ", lat: " + lat);

    Tweet tweet = TweetUtil.buildTweet(text, lon, lat);
    return service.postTweet(tweet);
  }

  /**
   * Parse user argument and search a tweet by calling service classes
   *
   * Source: https://www.baeldung.com/java-array-remove-first-element
   *
   * @param args
   * @return a tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet showTweet(String[] args) throws OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, IOException, OAuthCommunicationException {
    if (args.length == 0) {
      throw new IllegalArgumentException("Illegal number of arguments. Correct format: \"tweet_ID[String]\",\"optional_field_1[String]\",\"optional_field_2[String]\",\"...[String]\"");
    }
    validateTweetID(args[0]);
    String[] fields = null;
    if (args.length > 1) {
      fields = Arrays.copyOfRange(args, 1, args.length);
    }
    return service.showTweet(args[0], fields);
  }

  /**
   * Parse user argument and delete tweets by calling service classes
   *
   * @param args
   * @return a list of deleted tweets
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public List<Tweet> deleteTweet(String[] args) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
    if (args.length == 0) {
      throw new IllegalArgumentException("Illegal number of arguments. Correct format: \"tweet_ID_to_delete_1[String]\",\"optional_tweet_ID_to_delete_2[String]\",\"...[String]\"");
    }
    for (String string : args) {
      validateTweetID(string);
    }
    return service.deleteTweets(args);
  }
}
