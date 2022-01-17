package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;

import ca.jrvs.apps.trading.model.domain.Account;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class AccountDaoIntTest {

  @Autowired
  private TraderDao traderDao;

  @Autowired
  private AccountDao accountDao;
  private Account account;

  @Before
  public void insertOne() {
    Trader trader = new Trader();
    trader.setId(1);
    trader.setFirstName("Bruce");
    trader.setLastName("Wayne");
    trader.setDob(Date.valueOf("1990-01-01"));
    trader.setCountry("Canada");
    trader.setEmail("brucewayne@gmail.com");
    traderDao.save(trader);

    account = new Account();
    account.setId(1);
    account.setTraderId(1);
    account.setAmount(200.0D);
    accountDao.save(account);
  }

  @After
  public void deleteOne() {
    accountDao.deleteById(account.getId());
  }

  @Test
  public void findAllById() {
    List<Account> accounts = Lists.newArrayList(accountDao.findAll());
    assertEquals(1, accounts.size());
    assertEquals(account.getAmount(), accounts.get(0).getAmount());
  }
}