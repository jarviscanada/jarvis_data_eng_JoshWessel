package ca.jrvs.apps.trading.service;

import static org.junit.jupiter.api.Assertions.*;

import ca.jrvs.apps.trading.dao.MarketDataDaoIntTest;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.TestConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
class QuoteServiceIntTest {

  private final Logger logger = LoggerFactory.getLogger(QuoteServiceIntTest.class);

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private QuoteDao quoteDao;

  @BeforeEach
  void setUp() {
    quoteDao.deleteAll();
  }

  @Test
  void updateMarketData() {
    String ticker = "FB";
    quoteDao.save(new Quote(ticker, 20.0d, 20.0d, 20, 20.0d, 20));

    Quote quoteBefore = quoteDao.findById(ticker).get();
    logger.info(quoteBefore.toString());

    quoteService.updateMarketData();

    Quote quoteAfter = quoteDao.findById(ticker).get();
    logger.info(quoteAfter.toString());
  }

  @Test
  void saveQuotes() {
    List<String> tickers = new ArrayList<>();
    tickers.add("AAPL");
    tickers.add("GOOGL");
    tickers.add("AMZN");

    quoteService.saveQuotes(tickers);
  }

  @Test
  void saveQuote() {
    Quote quote = new Quote("NKE", 25.0d, 25.0d, 25, 25.0d, 25);
    Quote quoteAfter = quoteService.saveQuote(quote);
    logger.info(quoteAfter.toString());
  }

  @Test
  void findIexQuoteByTicker() {
    String ticker = "NVDA";
    IexQuote validIexQuote = quoteService.findIexQuoteByTicker(ticker);
    logger.info(validIexQuote.toString());
  }

  @Test
  void findAllQuotes() {
    List<String> tickers = new ArrayList<>();
    tickers.add("AAPL");
    tickers.add("GOOGL");
    tickers.add("AMZN");
    quoteService.saveQuotes(tickers);

    List<Quote> quotes = quoteService.findAllQuotes();

    for (Quote quote : quotes) {
      logger.info(quote.toString());
    }
  }
}