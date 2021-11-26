package ca.jrvs.practice.codingChallenge;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ValidPalindromeTest {

  private final Logger logger = LoggerFactory.getLogger(ValidPalindromeTest.class);

  ValidPalindrome validPalindrome;

  @Before
  public void setUp() throws Exception {

    BasicConfigurator.configure();

    validPalindrome = new ValidPalindrome();
  }

  @Test
  public void validPalindromeSolution1() {
    logger.info("" + validPalindrome.validPalindromeSolution1("A man, a plan, a canal: Panama"));
    logger.info("" + validPalindrome.validPalindromeSolution1(" "));
    logger.info("" + validPalindrome.validPalindromeSolution1("racecar"));
    logger.info("" + validPalindrome.validPalindromeSolution1("racecars"));
    logger.info("" + validPalindrome.validPalindromeSolution1("%racecar%"));
    logger.info("" + validPalindrome.validPalindromeSolution1("rac-e-car"));
    logger.info("" + validPalindrome.validPalindromeSolution1("race car"));
    logger.info("" + validPalindrome.validPalindromeSolution1("racecar!"));
    logger.info("" + validPalindrome.validPalindromeSolution1("racecar34"));
    logger.info("" + validPalindrome.validPalindromeSolution1("43racecar34"));
  }

  @Test
  public void validPalindromeSolution2() {
    logger.info("" + validPalindrome.validPalindromeSolution2("A man, a plan, a canal: Panama"));
    logger.info("" + validPalindrome.validPalindromeSolution2(" "));
    logger.info("" + validPalindrome.validPalindromeSolution2("racecar"));
    logger.info("" + validPalindrome.validPalindromeSolution2("racecars"));
    logger.info("" + validPalindrome.validPalindromeSolution2("%racecar%"));
    logger.info("" + validPalindrome.validPalindromeSolution2("rac-e-car"));
    logger.info("" + validPalindrome.validPalindromeSolution2("race car"));
    logger.info("" + validPalindrome.validPalindromeSolution2("racecar!"));
    logger.info("" + validPalindrome.validPalindromeSolution2("racecar34"));
    logger.info("" + validPalindrome.validPalindromeSolution2("43racecar34"));
  }
}