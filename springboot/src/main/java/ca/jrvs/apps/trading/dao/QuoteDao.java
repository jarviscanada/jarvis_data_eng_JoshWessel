package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

  private static final String TABLE_NAME = "quote";
  private static final String ID_COLUMN_NAME = "ticker";

  private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public QuoteDao(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
  }

  /**
   * Hint: http://bit.ly/2sDz8hg DataAccessException family
   * @throws DataAccessException for unexpected SQL result or SQL execution failure
   */
  @Override
  public Quote save(Quote quote) {
    boolean quoteExists = existsById(quote.getId());
    logger.info("Quote Ticker " + quote.getId() + " exists: " + quoteExists);
    if (quoteExists) {
      int updateRowNo = updateOne(quote);
      logger.info("Row Updated: " + updateRowNo);
      if (updateRowNo != 1) {
        throw new DataRetrievalFailureException("Unable to update quote");
      }
    } else {
      addOne(quote);
    }
    return quote;
  }

  /**
   * Helper method that saves one quote
   * @param quote
   */
  private void addOne(Quote quote) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
    int row = simpleJdbcInsert.execute(parameterSource);
    if (row != 1) {
      throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
    }
  }

  /**
   * Helper method that updates one quote
   * @param quote
   */
  private int updateOne(Quote quote) {
    String update_sql = "UPDATE quote SET last_price=?, bid_price=?, " +
        "bid_size=?, ask_price=?, ask_size=? WHERE " + ID_COLUMN_NAME + "=?";
    return jdbcTemplate.update(update_sql, makeUpdateValues(quote));
  }

  /**
   * Helper method that makes sql update values objects
   * @param quote to be updated
   * @return UPDATE_SQL values
   */
  private Object[] makeUpdateValues(Quote quote) {
    Object[] array = new Object[6];
    array[0] = quote.getLastPrice();
    array[1] = quote.getBidPrice();
    array[2] = quote.getBidSize();
    array[3] = quote.getAskPrice();
    array[4] = quote.getAskSize();
    array[5] = quote.getId();
    logger.info("Quote array: " + Arrays.toString(array));
    return array;
  }

  @Override
  public <S extends Quote> List<S> saveAll(Iterable<S> iterable) {
    List<Quote> quotes = new ArrayList<>();
    for (Quote quote : iterable) {
      if (existsById(quote.getId())) {
        int updateRowNo = updateOne(quote);
        if (updateRowNo != 1) {
          throw new DataRetrievalFailureException("Unable to update quote");
        }
      }
      else {
        addOne(quote);
      }
      quotes.add(quote);
    }
    return (List<S>) quotes;
  }

  /**
   * Find a quote by ticker
   * @param ticker name
   * @return quote or Optional.empty if not found
   */
  @Override
  public Optional<Quote> findById(String ticker) {
    String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " = '" + ticker + "'";
    Quote quote = jdbcTemplate.queryForObject(selectSql, BeanPropertyRowMapper.newInstance(Quote.class));
    if (quote == null) {
      return Optional.empty();
    }
    return Optional.of(quote);
  }

  @Override
  public boolean existsById(String ticker) {
    if (count() <= 0) {
      return false;
    }
    String selectSql = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " = '" + ticker + "'";
    Integer count = jdbcTemplate.queryForObject(selectSql, Integer.class);
    if (count == null) {
      return false;
    }
    return count > 0;
  }

  /**
   * Return all quotes
   * @throws DataAccessException if failed to update
   */
  @Override
  public List<Quote> findAll() {
    String selectSql = "SELECT * FROM " + TABLE_NAME;
    List<Quote> quotes = jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(Quote.class));
    if (quotes == null) {
      throw new DataRetrievalFailureException("Unable to find quotes");
    }
    return quotes;
  }

  @Override
  public Iterable<Quote> findAllById(Iterable<String> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public long count() {
    String selectSql = "SELECT COUNT(*) FROM " + TABLE_NAME;
    Long count = jdbcTemplate.queryForObject(selectSql, Long.class);
    return count;
  }

  @Override
  public void deleteById(String ticker) {
    if (ticker == null) {
      throw new IllegalArgumentException("Ticker cannot be null");
    }
    String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " = '" + ticker + "'";
    jdbcTemplate.update(deleteSql);
  }

  @Override
  public void delete(Quote quote) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends Quote> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll() {
    String deleteSql = "DELETE FROM " + TABLE_NAME;
    jdbcTemplate.update(deleteSql);
  }
}