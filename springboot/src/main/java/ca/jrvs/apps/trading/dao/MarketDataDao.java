package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * MarketDataDao is responsible for getting Quotes from IEX
 */
@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

  private final String HOST;
  private final String TOKEN;
  private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
  private final String PATH_START;
  private final String PATH_END;
  private final String IEX_BATCH_URL;

  private OAuthConsumer consumer;

  private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
  private HttpClientConnectionManager httpClientConnectionManager;

  @Autowired
  //public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager, MarketDataConfig marketDataConfig) {
  public MarketDataDao(MarketDataConfig marketDataConfig) {
    //this.httpClientConnectionManager = httpClientConnectionManager;
    HOST = marketDataConfig.getHost();
    TOKEN = marketDataConfig.getToken();
    PATH_START = HOST + "stock/market/batch?symbols=";
    PATH_END = "&types=quote&token=" + TOKEN;
    IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
  }

  @Override
  public Optional<IexQuote> findById(String ticker) {
    Optional<IexQuote> iexQuote;
    List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

    if (quotes.size() == 0) {
      return Optional.empty();
    }
    else if (quotes.size() == 1) {
      iexQuote = Optional.of(quotes.get(0));
    }
    else {
      throw new DataRetrievalFailureException("Unexpected number of quotes");
    }

    return iexQuote;
  }

  @Override
  public List<IexQuote> findAllById(Iterable<String> tickers) {
    List<String> tickerList = new ArrayList<>();
    tickers.forEach(tickerList::add);

    List<IexQuote> quotes = new ArrayList<>();

    for (int i = 0; i < tickerList.size(); i++) {
      try {
        String path = PATH_START + tickerList.get(i) + PATH_END;

        Gson gson = new Gson();
        String jsonString = executeHttpGet(path).get();
        logger.info("JSON string: " + jsonString);
        Type type = new TypeToken<Map<String, Map<String, IexQuote>>>() {}.getType();
        Map<String, Map<String, IexQuote>> map = gson.fromJson(jsonString, type);
        IexQuote quote = new IexQuote();

        String corporation = map.keySet().stream().findFirst().get();
        quote.setSymbol(map.get(corporation).get("quote").getSymbol());
        quote.setIexAskPrice(map.get(corporation).get("quote").getIexAskPrice());
        quote.setIexBidPrice(map.get(corporation).get("quote").getIexBidPrice());
        quote.setIexAskSize(map.get(corporation).get("quote").getIexAskSize());
        quote.setIexBidSize(map.get(corporation).get("quote").getIexBidSize());
        quote.setLatestPrice(map.get(corporation).get("quote").getLatestPrice());
        quotes.add(quote);

      } catch (IOException e) {
        throw new DataRetrievalFailureException("404 response");
      }
    }

    return quotes;
  }

  @Override
  public boolean existsById(String s) {
    throw new UnsupportedOperationException("Not Implemented");
  }

  @Override
  public List<IexQuote> findAll() {
    throw new UnsupportedOperationException("Not Implemented");
  }

  @Override
  public long count() {
    throw new UnsupportedOperationException("Not Implemented");
  }

  @Override
  public <S extends IexQuote> S save(S s) {
    throw new UnsupportedOperationException("Not Implemented");
  }

  @Override
  public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
    throw new UnsupportedOperationException("Not Implemented");
  }

  @Override
  public void deleteById(String s) {
    throw new UnsupportedOperationException("Not Implemented");
  }

  @Override
  public void delete(IexQuote iexQuote) {
    throw new UnsupportedOperationException("Not Implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends IexQuote> iterable) {
    throw new UnsupportedOperationException("Not Implemented");
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("Not Implemented");
  }

  private Optional<String> executeHttpGet(String url) throws IOException {
    HttpGet request = new HttpGet(url);
    HttpClient httpClient = getHttpClient();
    HttpResponse response = httpClient.execute(request);
    HttpEntity entity = response.getEntity();
    String responseString = EntityUtils.toString(entity, "UTF-8");
    return Optional.of(responseString);
  }

  private CloseableHttpClient getHttpClient() {
    return HttpClients.custom()
        .setConnectionManager(httpClientConnectionManager)
        // prevent connectionManager shutdown when calling httpCLient.close()
        .setConnectionManagerShared(true)
        .build();
  }
}