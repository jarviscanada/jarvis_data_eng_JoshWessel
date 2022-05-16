package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TestConfig;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.sql.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
public class TraderAccountServiceIntTest {

  private static final Logger logger = LoggerFactory.getLogger(TraderAccountServiceIntTest.class);

  private Position view;

  @Autowired
  private TraderAccountService traderAccountService;

  @Autowired
  private TraderDao traderDao;
  private Trader savedTrader;

  @Autowired
  private AccountDao accountDao;

  @Before
  public void setup() {
    savedTrader = new Trader();
    savedTrader.setId(1);
    savedTrader.setFirstName("Bruce");
    savedTrader.setLastName("Wayne");
    savedTrader.setDob(Date.valueOf("1990-01-01"));
    savedTrader.setCountry("Canada");
    savedTrader.setEmail("brucewayne@gmail.com");
  }

  @After
  public void delete() {
    traderAccountService.deleteTraderById(savedTrader.getId());
  }

  @Test
  public void test() {
    traderAccountService.createTraderAndAccount(savedTrader);
    logger.info("Amount After creation: " + accountDao.findById(savedTrader.getId()).get().getAmount());
    traderAccountService.deposit(savedTrader.getId(), 200.0D);
    logger.info("Amount After $200.0 deposit: " + accountDao.findById(savedTrader.getId()).get().getAmount());
    traderAccountService.withdraw(savedTrader.getId(), 50.0D);
    logger.info("Amount After $50.0 withdrawl: " + accountDao.findById(savedTrader.getId()).get().getAmount());
    traderAccountService.withdraw(savedTrader.getId(), 150.0D);
    logger.info("Amount After $150.0 withdrawl: " + accountDao.findById(savedTrader.getId()).get().getAmount());
  }
}