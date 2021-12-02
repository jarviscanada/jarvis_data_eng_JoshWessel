package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintLetterWithNumberTest {

  private final Logger logger = LoggerFactory.getLogger(PrintLetterWithNumberTest.class);

  PrintLetterWithNumber printLetterWithNumber;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    printLetterWithNumber = new PrintLetterWithNumber();
  }

  @Test
  public void printLetterWithNumber() {
    logger.info(String.valueOf(printLetterWithNumber.printLetterWithNumber("aAbB")));
    logger.info(String.valueOf(printLetterWithNumber.printLetterWithNumber("z Z")));
    logger.info(String.valueOf(printLetterWithNumber.printLetterWithNumber("aaabbb ccc abc")));
    logger.info(String.valueOf(printLetterWithNumber.printLetterWithNumber("a b c d e f g h i j k l m n o p q r s t u v w x y z")));
    logger.info(String.valueOf(printLetterWithNumber.printLetterWithNumber("a 8 & * i -")));
  }
}