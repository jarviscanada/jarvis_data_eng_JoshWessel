package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckIfNumberIsEvenOrOddTest {

  private final Logger logger = LoggerFactory.getLogger(CheckIfNumberIsEvenOrOddTest.class);

  CheckIfNumberIsEvenOrOdd checkIfNumberIsEvenOrOdd;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    checkIfNumberIsEvenOrOdd = new CheckIfNumberIsEvenOrOdd();
  }

  @Test
  public void checkIfNumberIsEvenOrOddSolution1() {
    int num1 = 2;
    int num2 = 3;
    int num3 = 8;
    int num4 = 9;
    int num5 = -2;
    int num6 = -103;
    logger.info(num1 + " is " + checkIfNumberIsEvenOrOdd.checkIfNumberIsEvenOrOddSolution1(num1));
    logger.info(num2 + " is " + checkIfNumberIsEvenOrOdd.checkIfNumberIsEvenOrOddSolution1(num2));
    logger.info(num3 + " is " + checkIfNumberIsEvenOrOdd.checkIfNumberIsEvenOrOddSolution1(num3));
    logger.info(num4 + " is " + checkIfNumberIsEvenOrOdd.checkIfNumberIsEvenOrOddSolution1(num4));
    logger.info(num5 + " is " + checkIfNumberIsEvenOrOdd.checkIfNumberIsEvenOrOddSolution1(num5));
    logger.info(num6 + " is " + checkIfNumberIsEvenOrOdd.checkIfNumberIsEvenOrOddSolution1(num6));
  }

  @Test
  public void checkIfNumberIsEvenOrOddSolution2() {
    int num1 = 2;
    int num2 = 3;
    int num3 = 8;
    int num4 = 9;
    int num5 = -2;
    int num6 = -103;
    logger.info(num1 + " is " + checkIfNumberIsEvenOrOdd.checkIfNumberIsEvenOrOddSolution2(num1));
    logger.info(num2 + " is " + checkIfNumberIsEvenOrOdd.checkIfNumberIsEvenOrOddSolution2(num2));
    logger.info(num3 + " is " + checkIfNumberIsEvenOrOdd.checkIfNumberIsEvenOrOddSolution2(num3));
    logger.info(num4 + " is " + checkIfNumberIsEvenOrOdd.checkIfNumberIsEvenOrOddSolution2(num4));
    logger.info(num5 + " is " + checkIfNumberIsEvenOrOdd.checkIfNumberIsEvenOrOddSolution2(num5));
    logger.info(num6 + " is " + checkIfNumberIsEvenOrOdd.checkIfNumberIsEvenOrOddSolution2(num6));
  }
}