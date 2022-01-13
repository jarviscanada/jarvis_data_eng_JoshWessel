package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.controller.TraderAccountController;
import ca.jrvs.apps.trading.service.QuoteService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
  private TraderAccountController controller;

  public static void main(String[] args) throws Exception {
    SpringApplication app = new SpringApplication(Application.class);
    app.run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    logger.info("Running application...");
    logger.info("Command: " + args[0]);
    if (validateArgs(args)) {
      if (Objects.equals(args[0], "create")) {
        logger.info("Creating trader...");
        controller.createTrader(args[1], args[2], LocalDate.of(1990, 1, 1), "Canada",
            "email@gmail.com");
        logger.info("Trader created!");
      }
      else if (Objects.equals(args[0], "delete")) {
        logger.info("Deleting trader...");
        controller.deleteTrader(Integer.valueOf(args[1]));
        logger.info("Trader deleted!");
      }
      else if (Objects.equals(args[0], "deposit")) {
        logger.info("Depositing...");
        controller.depositFund(Integer.valueOf(args[1]), Double.valueOf(args[2]));
        logger.info(args[2] + " deposited!");
      }
      else if (Objects.equals(args[0], "withdraw")) {
        logger.info("Withdrawing...");
        controller.withdrawFund(Integer.valueOf(args[1]), Double.valueOf(args[2]));
        logger.info(args[2] + " withdrawn!");
      }
      else if (Objects.equals(args[0], "help")) {
        logger.info("Commands:");
        logger.info("  create [first_name] [last_name]");
        logger.info("  delete [id]");
        logger.info("  deposit [id] [amount]");
        logger.info("  withdraw [id] [amount]");
      }
    }
  }

  public boolean validateArgs(String[] args) {
    if (!args[0].equalsIgnoreCase("create")
        && !args[0].equalsIgnoreCase("delete")
        && !args[0].equalsIgnoreCase("deposit")
        && !args[0].equalsIgnoreCase("withdraw")
        && !args[0].equalsIgnoreCase("help")) {
      logger.error("Illegal operation. Available operations include create, delete, deposit and withdraw");
      return false;
    }
    if (Objects.equals(args[0].toLowerCase(), "create") && args.length != 3) {
      logger.error("Illegal number of arguments. Correct format: create [first_name] [last_name]");
      return false;
    }
    else if (Objects.equals(args[0].toLowerCase(), "delete") && args.length != 2) {
      logger.error("Illegal number of arguments. Correct format: delete [id]");
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
    logger.info("Arguments are valid");
    return true;
  }
}