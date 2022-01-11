package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class QuoteService {

  private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

  private QuoteDao quoteDao;
  private MarketDataDao marketDataDao;

  @Autowired
  public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
  //public QuoteService(MarketDataDao marketDataDao) {
    this.quoteDao = quoteDao;
    this.marketDataDao = marketDataDao;
  }

  /**
   * Update quote table against IEX source
   * - Get all quotes from the database
   * - foreach ticker get iexQuote
   * - convert iexQuote to quote entity
   * persist quote to database
   *
   * @throws org.springframework.dao.DataAccessException if unable to retrieve data
   * @throws IllegalArgumentException for invalid input
   */
  public void updateMarketData() {
    List<Quote> quotesInDB = quoteDao.findAll();
    for (int i = 0; i < quotesInDB.size(); i++) {
      saveQuote(quotesInDB.get(i).getTicker());
    }
  }

  /**
   * Helper method. Map a IexQuote to a Quote entity.
   * Note: 'iexQuote.getLatestPrice() == null' if the stock market is closed.
   * Make sure to set a default value for number fields.
   */
  protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote) {
    Quote quote = new Quote(null, 0.0d, 0.0d, 0, 0.0d, 0);
    quote.setTicker(iexQuote.getSymbol());
    quote.setLastPrice(iexQuote.getLatestPrice());
    quote.setBidPrice(iexQuote.getIexBidPrice());
    quote.setBidSize(iexQuote.getIexBidSize());
    quote.setAskPrice(iexQuote.getIexAskPrice());
    quote.setAskSize(iexQuote.getIexAskSize());
    return quote;
  }

  /**
   * Validate (against IEX) and save given tickers to quote table.
   *
   * - Get iexQuote(s)
   * - Convert each iexQuote to Quote entity
   * - Persist the quote to database
   *
   * @param tickers a list of tickers/symbols
   * @throws IllegalArgumentException if ticker is not found from IEX
   */
  public List<Quote> saveQuotes(List<String> tickers) {
    List<Quote> quotes = new ArrayList<>();
    for (int i = 0; i < tickers.size(); i++) {
      quotes.add(saveQuote(tickers.get(i)));
    }
    return quotes;
  }

  /**
   * Helper method
   */
  public Quote saveQuote(String ticker) {
    IexQuote iexQuote = findIexQuoteByTicker(ticker);
    if (iexQuote.getLatestPrice() == null) {
      throw new IllegalArgumentException("Ticker not found from IEX");
    }
    Quote quote = buildQuoteFromIexQuote(iexQuote);
    quoteDao.save(quote);
    return quote;
  }

  /**
   * Find an IexQuote
   *
   * @param ticker id
   * @return IexQuote object
   * @throws IllegalArgumentException if ticker is invalid
   */
  public IexQuote findIexQuoteByTicker(String ticker) {
    return marketDataDao.findById(ticker).orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid"));
  }

  /**
   * Update a given quote to quote table without validation
   * @param quote entity
   */
  public Quote saveQuote(Quote quote) {
    return quoteDao.save(quote);
  }

  /**
   * Find all quotes from the quote table
   * @return a list of quotes
   */
  public List<Quote> findAllQuotes() {
    return quoteDao.findAll();
  }
}