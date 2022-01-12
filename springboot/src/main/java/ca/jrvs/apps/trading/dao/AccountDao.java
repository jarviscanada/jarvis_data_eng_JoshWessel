package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Arrays;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao extends JdbcCrudDao<Account> {

  private static final Logger logger = LoggerFactory.getLogger(AccountDao.class);

  private static final String TABLE_NAME = "account";
  private static final String ID_COLUMN = "id";

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleInsert;

  @Autowired
  public AccountDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_COLUMN);
  }

  @Override
  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  @Override
  public SimpleJdbcInsert getSimpleJdbcInsert() {
    return simpleInsert;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public String getIdColumnName() {
    return ID_COLUMN;
  }

  @Override
  Class<Account> getEntityClass() {
    return Account.class;
  }

  /**
   * Helper method that updates one quote
   *
   * @param entity
   */
  @Override
  public int updateOne(Account entity) {
    String update_sql = "UPDATE account SET trader_id=?, amount=? " +
        "WHERE " + getIdColumnName() + "=?";
    return getJdbcTemplate().update(update_sql, makeUpdateValues(entity));
  }

  private Object[] makeUpdateValues(Account account) {
    Object[] array = new Object[3];
    array[0] = account.getTraderId();
    array[1] = account.getAmount();
    array[2] = account.getId();
    logger.info("Account array: " + Arrays.toString(array));
    return array;
  }

  @Override
  public <S extends Account> Iterable<S> saveAll(Iterable<S> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(Account account) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends Account> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }
}