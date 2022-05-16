package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.sql.Date;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class SecurityOrderDaoIntTest {

  @Autowired
  private QuoteDao quoteDao;
  private Quote savedQuote;

  @Autowired
  private TraderDao traderDao;
  private Trader savedTrader;

  @Autowired
  private AccountDao accountDao;
  private Account account;

  @Autowired
  private SecurityOrderDao securityOrderDao;
  private SecurityOrder securityOrder;

  @Before
  public void insertOne() {

    // Quote Setup
    savedQuote = new Quote();
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(80);
    savedQuote.setId("AAPL");
    savedQuote.setLastPrice(10.1d);
    quoteDao.save(savedQuote);

    // Trader Setup
    savedTrader = new Trader();
    savedTrader.setId(1);
    savedTrader.setFirstName("Bruce");
    savedTrader.setLastName("Wayne");
    savedTrader.setDob(Date.valueOf("1990-01-01"));
    savedTrader.setCountry("Canada");
    savedTrader.setEmail("brucewayne@gmail.com");
    traderDao.save(savedTrader);

    // Account Setup
    account = new Account();
    account.setId(1);
    account.setTraderId(1);
    account.setAmount(200.0D);
    accountDao.save(account);

    // Security Order Setup
    securityOrder = new SecurityOrder();
    securityOrder.setId(1);
    securityOrder.setAccountId(1);
    securityOrder.setStatus("FILLED");
    securityOrder.setTicker("AAPL");
    securityOrder.setSize(10);
    securityOrder.setPrice(20.0D);
    securityOrder.setNotes("Int testing...");
    securityOrderDao.save(securityOrder);
  }

  @After
  public void deleteOne() {
//    securityOrderDao.deleteById(securityOrder.getId());
//    accountDao.deleteById(account.getId());
//    traderDao.deleteById(savedTrader.getId());
//    quoteDao.deleteById(savedQuote.getId());
  }

  @Test
  public void findAllById() {
    List<SecurityOrder> securityOrders = Lists.newArrayList(securityOrderDao.findAll());
    assertEquals(1, securityOrders.size());
    assertEquals(securityOrder.getPrice(), securityOrders.get(0).getPrice());
  }
}