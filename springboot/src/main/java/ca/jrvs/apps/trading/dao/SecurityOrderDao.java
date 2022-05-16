package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.SecurityOrder;
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
public class SecurityOrderDao extends JdbcCrudDao<SecurityOrder> {

  private static final Logger logger = LoggerFactory.getLogger(SecurityOrderDao.class);

  private static final String TABLE_NAME = "security_order";
  private static final String ID_COLUMN = "id";

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleInsert;

  @Autowired
  public SecurityOrderDao(DataSource dataSource) {
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
  Class<SecurityOrder> getEntityClass() {
    return SecurityOrder.class;
  }

  /**
   * Helper method that updates one quote
   *
   * @param entity
   */
  @Override
  public int updateOne(SecurityOrder entity) {
    String update_sql = "UPDATE security_order SET account_id=?, status=?, " +
        "ticker=?, size=?, price=?, notes=? WHERE " + getIdColumnName() + "=?";
    return getJdbcTemplate().update(update_sql, makeUpdateValues(entity));
  }

  private Object[] makeUpdateValues(SecurityOrder securityOrder) {
    Object[] array = new Object[7];
    array[0] = securityOrder.getAccountId();
    array[1] = securityOrder.getStatus();
    array[2] = securityOrder.getTicker();
    array[3] = securityOrder.getSize();
    array[4] = securityOrder.getPrice();
    array[5] = securityOrder.getNotes();
    array[6] = securityOrder.getId();
    logger.info("Security Order array: " + Arrays.toString(array));
    return array;
  }

  @Override
  public <S extends SecurityOrder> Iterable<S> saveAll(Iterable<S> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(SecurityOrder securityOrder) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends SecurityOrder> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }
}