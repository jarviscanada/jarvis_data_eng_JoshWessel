package ca.jrvs.practice.codingChallenge;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TwoSumTest {

  private final Logger logger = LoggerFactory.getLogger(TwoSumTest.class);

  private TwoSum twoSum;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    twoSum = new TwoSum();
  }

  @Test
  public void twoSumSolution1() {
    int[] test = { 0, 1, 2, 3, 4, 5 };
    int[] results = twoSum.twoSumSolution1(test, 8);
    for (int result : results) {
      logger.info(String.valueOf(result));
    }
  }

  @Test
  public void twoSumSolution2() {
    int[] test = { 0, 1, 2, 3, 4, 5 };
    int[] results = twoSum.twoSumSolution2(test, 8);
    for (int result : results) {
      logger.info(String.valueOf(result));
    }
  }
}