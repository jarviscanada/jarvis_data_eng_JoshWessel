package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.controller.QuoteController;
import ca.jrvs.apps.trading.controller.TraderAccountController;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.time.LocalDate;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {JdbcTemplateAutoConfiguration.class, DataSourceAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class})
public class Application implements CommandLineRunner {

  private Logger logger = LoggerFactory.getLogger(Application.class);

  //@Value("${app.init.dailyList}")
  //private String[] initDailyList;

  @Autowired
  private TraderAccountController traderAccountController;

  @Autowired
  private QuoteController quoteController;

  public static void main(String[] args) throws Exception {
    SpringApplication app = new SpringApplication(Application.class);
    app.run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    logger.info("Running application...");
    logger.info("Command: " + args[0]);
    if (validateArgs(args)) {
      if (Objects.equals(args[0], "trader")) {
        logger.info("Creating trader...");
        traderAccountController.createTrader(args[1], args[2], LocalDate.of(1990, 1, 1), "Canada",
            "email@gmail.com");
        logger.info("Trader created!");
      }
      else if (Objects.equals(args[0], "traderId")) {
        logger.info("Deleting trader...");
        traderAccountController.deleteTrader(Integer.valueOf(args[1]));
        logger.info("Trader deleted!");
      }
      else if (Objects.equals(args[0], "deposit")) {
        logger.info("Depositing...");
        traderAccountController.depositFund(Integer.valueOf(args[1]), Double.valueOf(args[2]));
        logger.info(args[2] + " deposited!");
      }
      else if (Objects.equals(args[0], "withdraw")) {
        logger.info("Withdrawing...");
        traderAccountController.withdrawFund(Integer.valueOf(args[1]), Double.valueOf(args[2]));
        logger.info(args[2] + " withdrawn!");
      }

      else if (Objects.equals(args[0], "ticker")) {
        logger.info("Creating ticker...");
        quoteController.createQuote(String.valueOf(args[1]));
        logger.info("Ticker " + args[1] + " created!");
      }
      else if (Objects.equals(args[0], "put")) {
        logger.info("Updating ticker...");
        quoteController.putQuote(new Quote(
            String.valueOf(args[1]),
            Double.valueOf(args[2]),
            Double.valueOf(args[3]),
            Integer.valueOf(args[4]),
            Double.valueOf(args[5]),
            Integer.valueOf(args[6])));
        logger.info("Ticker " + args[1] + " updated!");
      }
      else if (Objects.equals(args[0], "update")) {
        logger.info("Updating market data...");
        quoteController.updateMarketData();
        logger.info("Market data updated!");
      }

      else if (Objects.equals(args[0], "help")) {
        logger.info("Trader Commands:");
        logger.info("  trader [first_name] [last_name]");
        logger.info("  traderId [id]");
        logger.info("  deposit [id] [amount]");
        logger.info("  withdraw [id] [amount]");

        logger.info("Quote Commands:");
        logger.info("  ticker [ticker_id]");
        logger.info("  put [ticker_id] [last_price] [bid_price] [bid_size] [ask_price] [ask_size]");
        logger.info("  update");
      }
    }
  }

  public boolean validateArgs(String[] args) {
    if (!args[0].equalsIgnoreCase("trader")
        && !args[0].equalsIgnoreCase("traderId")
        && !args[0].equalsIgnoreCase("deposit")
        && !args[0].equalsIgnoreCase("withdraw")
        && !args[0].equalsIgnoreCase("ticker")
        && !args[0].equalsIgnoreCase("put")
        && !args[0].equalsIgnoreCase("update")
        && !args[0].equalsIgnoreCase("help")) {
      logger.error("Illegal operation. Available operations include trader, traderId, deposit, withdraw, ticker, put, update, and help");
      return false;
    }
    if (Objects.equals(args[0].toLowerCase(), "trader") && args.length != 3) {
      logger.error("Illegal number of arguments. Correct format: trader [first_name] [last_name]");
      return false;
    }
    else if (Objects.equals(args[0].toLowerCase(), "traderId") && args.length != 2) {
      logger.error("Illegal number of arguments. Correct format: traderId [id]");
      return false;
    }
    else if (Objects.equals(args[0].toLowerCase(), "deposit") && args.length != 3) {
      logger.error("Illegal number of arguments. Correct format: deposit [id] [amount]");
      return false;
    }
    else if (Objects.equals(args[0].toLowerCase(), "withdraw") && args.length != 3) {
      logger.error("Illegal number of arguments. Correct format: withdraw [id] [amount]");
      return false;
    }
    else if (Objects.equals(args[0].toLowerCase(), "ticker") && args.length != 2) {
      logger.error("Illegal number of arguments. Correct format: ticker [ticker_id]");
      return false;
    }
    else if (Objects.equals(args[0].toLowerCase(), "put") && args.length != 7) {
      logger.error("Illegal number of arguments. Correct format: put [ticker_id] [last_price] [bid_price] [bid_size] [ask_price] [ask_size]");
      return false;
    }
    else if (Objects.equals(args[0].toLowerCase(), "update") && args.length != 1) {
      logger.error("Illegal number of arguments. Correct format: update");
      return false;
    }
    logger.info("Arguments are valid");
    return true;
  }
}