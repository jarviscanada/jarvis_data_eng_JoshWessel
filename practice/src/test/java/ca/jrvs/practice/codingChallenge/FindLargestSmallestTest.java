package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindLargestSmallestTest {

  private final Logger logger = LoggerFactory.getLogger(FindLargestSmallestTest.class);

  FindLargestSmallest findLargestSmallest;

  int[] arr = { 3, 7, 0, -5, 12, 35, 2, -20, 500 };

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    findLargestSmallest = new FindLargestSmallest();
  }

  @Test
  public void findLargestApproach1() {
    logger.info(String.valueOf(findLargestSmallest.findLargestApproach1(arr)));
  }

  @Test
  public void findSmallestApproach1() {
    logger.info(String.valueOf(findLargestSmallest.findSmallestApproach1(arr)));
  }

  @Test
  public void findLargestApproach2() {
    logger.info(String.valueOf(findLargestSmallest.findLargestApproach2(arr)));
  }

  @Test
  public void findSmallestApproach2() {
    logger.info(String.valueOf(findLargestSmallest.findSmallestApproach2(arr)));
  }

  @Test
  public void findLargestApproach3() {
    logger.info(String.valueOf(findLargestSmallest.findLargestApproach3(arr)));
  }

  @Test
  public void findSmallestApproach3() {
    logger.info(String.valueOf(findLargestSmallest.findSmallestApproach3(arr)));
  }
}