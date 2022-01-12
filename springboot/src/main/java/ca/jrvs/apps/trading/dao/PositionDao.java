package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDao {

  private static final Logger logger = LoggerFactory.getLogger(PositionDao.class);

  private static final String VIEW_NAME = "position";
  private static final String ID_COLUMN = "account_id";

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public PositionDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  public String getViewName() {
    return VIEW_NAME;
  }

  public String getIdColumnName() {
    return ID_COLUMN;
  }

  Class<Position> getEntityClass() {
    return Position.class;
  }

  public Optional<Position> findById(Integer id) {
    Optional<Position> entity = Optional.empty();
    String selectSql = "SELECT * FROM " + getViewName() + " WHERE " + getIdColumnName() + " =?";

    try {
      entity = Optional.ofNullable(getJdbcTemplate().queryForObject(selectSql, BeanPropertyRowMapper.newInstance(getEntityClass()), id));
    } catch (IncorrectResultSizeDataAccessException e) {
      logger.debug("Can't find account id: " + id, e);
    }
    return entity;
  }

  public boolean existsById(Integer id) {
    if (count() <= 0) {
      return false;
    }
    String selectSql = "SELECT COUNT(*) FROM " + getViewName() + " WHERE " + getIdColumnName() + " = '" + id + "'";
    Integer count = getJdbcTemplate().queryForObject(selectSql, Integer.class);
    if (count == null) {
      return false;
    }
    return count > 0;
  }

  public List<Position> findAll() {
    String selectSql = "SELECT * FROM " + getViewName();
    List<Position> list = getJdbcTemplate().query(selectSql, BeanPropertyRowMapper.newInstance(getEntityClass()));
    if (list == null) {
      throw new DataRetrievalFailureException("Unable to find entities");
    }
    return list;
  }

  public List<Position> findAllById(Iterable<Integer> ids) {
    throw new UnsupportedOperationException("Not implemented");
  }

  public long count() {
    String selectSql = "SELECT COUNT(*) FROM " + getViewName();
    return getJdbcTemplate().queryForObject(selectSql, Long.class);
  }
}