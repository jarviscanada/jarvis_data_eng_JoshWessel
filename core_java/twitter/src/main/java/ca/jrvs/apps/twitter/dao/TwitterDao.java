package ca.jrvs.apps.twitter.dao;

import static org.postgresql.util.URLCoder.encode;

import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.example.dto.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import com.sun.jndi.toolkit.url.Uri;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterDao implements CrdDao<Tweet, String> {

  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy";

  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;

  private JsonParser parser;

  @Autowired
  public TwitterDao(HttpHelper httpHelper) { this.httpHelper = httpHelper; }

  private URI getPostUri(Tweet tweet) throws URISyntaxException {
    return new URI(API_BASE_URI + POST_PATH
        + QUERY_SYM + "status" + EQUAL + encode(tweet.getText())
        + AMPERSAND + "favorited" + EQUAL + tweet.isFavorited()
        + AMPERSAND + "retweeted" + EQUAL + tweet.isRetweeted()
        + AMPERSAND + "long" + EQUAL + tweet.getCoordinates().getCoordinates().get(0)
        + AMPERSAND + "lat" + EQUAL + tweet.getCoordinates().getCoordinates().get(1)
        + AMPERSAND + "entities" + EQUAL + tweet.getEntities());
  }

  /**
   * Create an entity(Tweet) to the underlying storage
   *
   * @param tweet entity that to be created
   * @return created entity
   */
  @Override
  public Tweet create(Tweet tweet) throws URISyntaxException, OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException {
    URI uri;
    try {
      uri = getPostUri(tweet);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet input", e);
    }
    HttpResponse response = httpHelper.httpPost(uri);
    return JsonParser.toObjectFromJson(EntityUtils.toString(response.getEntity()), Tweet.class);
  }

  /**
   * Find an entity(Tweet) by its id
   *
   * @param s entity id
   * @return Tweet entity
   */
  @Override
  public Tweet findById(String s) throws URISyntaxException, OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException {
    HttpResponse response = httpHelper.httpGet(new URI(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + s));
    return JsonParser.toObjectFromJson(EntityUtils.toString(response.getEntity()), Tweet.class);
  }

  /**
   * Delete an entity(Tweet) by its ID
   *
   * @param s of the entity to be deleted
   * @return deleted entity
   */
  @Override
  public Tweet deleteById(String s) throws IOException, URISyntaxException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
    HttpResponse response = httpHelper.httpPost(new URI(API_BASE_URI + DELETE_PATH + "/" + s + ".json"));
    return JsonParser.toObjectFromJson(EntityUtils.toString(response.getEntity()), Tweet.class);
  }
}
