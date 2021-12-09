package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidParenthesesTest {

  private final Logger logger = LoggerFactory.getLogger(ValidParenthesesTest.class);

  ValidParentheses validParentheses;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    validParentheses = new ValidParentheses();
  }

  @Test
  public void isValid() {

    String parentheses1 = "()";
    String parentheses2 = "[]{}";
    String parentheses3 = "[{}]";
    String parentheses4 = "([({}())])";
    String parentheses5 = "{[}]";
    String parentheses6 = "{[";
    String parentheses7 = "{)";
    String parentheses8 = "())(";

    logger.info(parentheses1 + " is valid parentheses: \t" + validParentheses.isValid(parentheses1));
    logger.info(parentheses2 + " is valid parentheses: \t" + validParentheses.isValid(parentheses2));
    logger.info(parentheses3 + " is valid parentheses: \t" + validParentheses.isValid(parentheses3));
    logger.info(parentheses4 + " is valid parentheses: \t" + validParentheses.isValid(parentheses4));
    logger.info(parentheses5 + " is valid parentheses: \t" + validParentheses.isValid(parentheses5));
    logger.info(parentheses6 + " is valid parentheses: \t" + validParentheses.isValid(parentheses6));
    logger.info(parentheses7 + " is valid parentheses: \t" + validParentheses.isValid(parentheses7));
    logger.info(parentheses8 + " is valid parentheses: \t" + validParentheses.isValid(parentheses8));
  }
}