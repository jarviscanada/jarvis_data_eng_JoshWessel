package ca.jrvs.practice.codingChallenge;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Before;
import org.junit.Test;

public class FibonacciNumberClimbingStairsTest {

  private final Logger logger = LoggerFactory.getLogger(FibonacciNumberClimbingStairsTest.class);

  FibonacciNumberClimbingStairs fibonacciNumberClimbingStairs;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    fibonacciNumberClimbingStairs = new FibonacciNumberClimbingStairs();
  }

  @Test
  public void fibNumRecursive() {
    logger.info(String.valueOf(fibonacciNumberClimbingStairs.fibNumRecursive(20)));
  }

  @Test
  public void fibNumDP() {
    logger.info(String.valueOf(fibonacciNumberClimbingStairs.fibNumDP(20)));
  }

  @Test
  public void climbStairsRecursive() {
    logger.info(String.valueOf(fibonacciNumberClimbingStairs.climbStairsRecursive(20)));
  }

  @Test
  public void climbStairsDP() {
    logger.info(String.valueOf(fibonacciNumberClimbingStairs.climbStairsDP(20)));
  }
}