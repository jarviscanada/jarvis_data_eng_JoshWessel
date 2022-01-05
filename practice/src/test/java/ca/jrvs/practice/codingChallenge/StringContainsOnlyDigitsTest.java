package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringContainsOnlyDigitsTest {

  private final Logger logger = LoggerFactory.getLogger(StringContainsOnlyDigitsTest.class);

  StringContainsOnlyDigits stringContainsOnlyDigits;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    stringContainsOnlyDigits = new StringContainsOnlyDigits();
  }

  @Test
  public void stringContainsOnlyDigitsSolution1() {
    logger.info("1234        contains only digits: " + stringContainsOnlyDigits.stringContainsOnlyDigitsSolution1("1234"));
    logger.info("abcd        contains only digits: " + stringContainsOnlyDigits.stringContainsOnlyDigitsSolution1("abcd"));
    logger.info("a2c4        contains only digits: " + stringContainsOnlyDigits.stringContainsOnlyDigitsSolution1("a2c4"));
    logger.info("0123456789  contains only digits: " + stringContainsOnlyDigits.stringContainsOnlyDigitsSolution1("0123456789"));
    logger.info("0123456789a contains only digits: " + stringContainsOnlyDigits.stringContainsOnlyDigitsSolution1("0123456789a"));
  }

  @Test
  public void stringContainsOnlyDigitsSolution2() {
    logger.info("1234        contains only digits: " + stringContainsOnlyDigits.stringContainsOnlyDigitsSolution2("1234"));
    logger.info("abcd        contains only digits: " + stringContainsOnlyDigits.stringContainsOnlyDigitsSolution2("abcd"));
    logger.info("a2c4        contains only digits: " + stringContainsOnlyDigits.stringContainsOnlyDigitsSolution2("a2c4"));
    logger.info("0123456789  contains only digits: " + stringContainsOnlyDigits.stringContainsOnlyDigitsSolution2("0123456789"));
    logger.info("0123456789a contains only digits: " + stringContainsOnlyDigits.stringContainsOnlyDigitsSolution2("0123456789a"));
  }

  @Test
  public void stringContainsOnlyDigitsSolution3() {
    logger.info("1234        contains only digits: " + stringContainsOnlyDigits.stringContainsOnlyDigitsSolution3("1234"));
    logger.info("abcd        contains only digits: " + stringContainsOnlyDigits.stringContainsOnlyDigitsSolution3("abcd"));
    logger.info("a2c4        contains only digits: " + stringContainsOnlyDigits.stringContainsOnlyDigitsSolution3("a2c4"));
    logger.info("0123456789  contains only digits: " + stringContainsOnlyDigits.stringContainsOnlyDigitsSolution3("0123456789"));
    logger.info("0123456789a contains only digits: " + stringContainsOnlyDigits.stringContainsOnlyDigitsSolution3("0123456789a"));
  }
}