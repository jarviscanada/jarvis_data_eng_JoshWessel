package ca.jrvs.apps.twitter.dao.helper;

import ca.jrvs.apps.twitter.example.dto.HttpHelper;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class TwitterHttpHelper implements HttpHelper {

  /**
   * Dependencies are specified as private member variables
   */
  private OAuthConsumer consumer;
  private HttpClient httpClient;

  /**
   * Constructor
   * Setup dependencies using secrets
   *
   * @param consumerKey
   * @param consumerSecret
   * @param accessToken
   * @param tokenSecret
   */
  public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);
    /**
     * Default = single connection.
     */
    httpClient = new DefaultHttpClient();
  }

  /**
   * Execute a HTTP Post call
   *
   * @param uri
   * @return
   */
  @Override
  public HttpResponse httpPost(URI uri) throws IOException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {

    HttpPost request = new HttpPost(uri);
    consumer.sign(request);

    HttpClient httpClient = HttpClientBuilder.create().build();
    return httpClient.execute(request);
  }

  /**
   * Execute a HTTP Get call
   *
   * @param uri
   * @return
   */
  @Override
  public HttpResponse httpGet(URI uri) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, IOException {

    HttpGet request = new HttpGet(uri);
    consumer.sign(request);

    HttpClient httpClient = HttpClientBuilder.create().build();
    return httpClient.execute(request);
  }
}
