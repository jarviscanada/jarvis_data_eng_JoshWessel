package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class AppConfig {

  private String iex_host = "https://cloud.iexapis.com/v1/";
  private Logger logger = LoggerFactory.getLogger(AppConfig.class);

  private String jdbcUrl;
  private String user;
  private String password;

  @Bean
  public MarketDataConfig marketDataConfig() {
    if (StringUtils.isEmpty(System.getenv("IEX_PUB_TOKEN")) || StringUtils.isEmpty(iex_host)) {
      throw new IllegalArgumentException("ENV: IEX_PUB_TOKEN or property: iex_host is not set");
    }
    MarketDataConfig marketDataConfig = new MarketDataConfig();
    marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));
    marketDataConfig.setHost(iex_host);
    return marketDataConfig;
  }

  @Bean
  public DataSource dataSource() {
    jdbcUrl = "jdbc:postgresql://" +
        System.getenv("PSQL_HOST") + ":" +
        System.getenv("PSQL_PORT") + "/" +
        System.getenv("PSQL_DB");
    user = System.getenv("PSQL_USER");
    password = System.getenv("PSQL_PASSWORD");

    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(jdbcUrl);
    basicDataSource.setUsername(user);
    basicDataSource.setPassword(password);
    return basicDataSource;
  }
}