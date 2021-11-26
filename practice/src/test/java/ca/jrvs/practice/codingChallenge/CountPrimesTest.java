package ca.jrvs.practice.codingChallenge;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CountPrimesTest {

  private final Logger logger = LoggerFactory.getLogger(CountPrimesTest.class);

  CountPrimes countPrimes;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    countPrimes = new CountPrimes();
  }

  @Test
  public void countPrimes() {
    logger.info(String.valueOf(countPrimes.countPrimes(10)));
    logger.info(String.valueOf(countPrimes.countPrimes(50)));
    logger.info(String.valueOf(countPrimes.countPrimes(100)));
  }
}