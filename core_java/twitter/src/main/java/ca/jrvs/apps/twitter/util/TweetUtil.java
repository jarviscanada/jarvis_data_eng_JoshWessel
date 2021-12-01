package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;

public class TweetUtil {

  public static Tweet buildTweet(String text, Double lon, Double lat) {

    Coordinates coord = new Coordinates();
    ArrayList<Double> coords = new ArrayList<>();
    coords.add(lon);
    coords.add(lat);
    coord.setCoordinates(coords);
    coord.setType("Point");

    Tweet tweet = new Tweet();
    tweet.setText(text);
    tweet.setCoordinates(coord);
    tweet.setEntities(null);
    tweet.setFavorited(false);
    tweet.setFavorite_count(0);
    tweet.setRetweeted(false);
    tweet.setRetweet_count(0);

    return tweet;
  }
}
