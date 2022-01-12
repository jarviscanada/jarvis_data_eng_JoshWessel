package ca.jrvs.apps.trading.dao;

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
public class TraderDao extends JdbcCrudDao<Trader> {

  private static final Logger logger = LoggerFactory.getLogger(TraderDao.class);

  private static final String TABLE_NAME = "trader";
  private static final String ID_COLUMN = "id";

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleInsert;

  @Autowired
  public TraderDao(DataSource dataSource) {
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
  Class<Trader> getEntityClass() {
    return Trader.class;
  }

  /**
   * Helper method that updates one quote
   *
   * @param entity
   */
  @Override
  public int updateOne(Trader entity) {
    String update_sql = "UPDATE trader SET first_name=?, last_name=?, " +
        "dob=?, country=?, email=? WHERE " + getIdColumnName() + "=?";
    return getJdbcTemplate().update(update_sql, makeUpdateValues(entity));
  }

  private Object[] makeUpdateValues(Trader trader) {
    Object[] array = new Object[6];
    array[0] = trader.getFirstName();
    array[1] = trader.getLastName();
    array[2] = trader.getDob();
    array[3] = trader.getCountry();
    array[4] = trader.getEmail();
    array[5] = trader.getId();
    logger.info("Trader array: " + Arrays.toString(array));
    return array;
  }

  @Override
  public <S extends Trader> Iterable<S> saveAll(Iterable<S> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(Trader trader) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends Trader> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }
}