package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MissingNumberTest {

  private final Logger logger = LoggerFactory.getLogger(MissingNumberTest.class);

  MissingNumber missingNumber;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    missingNumber = new MissingNumber();
  }

  @Test
  public void missingNumberSolution1() {
    int[] nums1 = { 0, 1, 2, 3, 5, 6, 7, 8 };
    int[] nums2 = { 0, 1, 2, 3, 4, 6, 7, 8 };
    int[] nums3 = { 0, 1, 2, 3, 4, 5, 7, 8 };
    logger.info(String.valueOf(missingNumber.missingNumberSolution1(nums1)));
    logger.info(String.valueOf(missingNumber.missingNumberSolution1(nums2)));
    logger.info(String.valueOf(missingNumber.missingNumberSolution1(nums3)));
  }

  @Test
  public void missingNumberSolution2() {
    int[] nums1 = { 0, 1, 2, 3, 5, 6, 7, 8 };
    int[] nums2 = { 0, 1, 2, 3, 4, 6, 7, 8 };
    int[] nums3 = { 0, 1, 2, 3, 4, 5, 7, 8 };
    logger.info(String.valueOf(missingNumber.missingNumberSolution2(nums1)));
    logger.info(String.valueOf(missingNumber.missingNumberSolution2(nums2)));
    logger.info(String.valueOf(missingNumber.missingNumberSolution2(nums3)));
  }

  @Test
  public void missingNumberSolution3() {
    int[] nums1 = { 0, 1, 2, 3, 5, 6, 7, 8 };
    int[] nums2 = { 0, 1, 2, 3, 4, 6, 7, 8 };
    int[] nums3 = { 0, 1, 2, 3, 4, 5, 7, 8 };
    logger.info(String.valueOf(missingNumber.missingNumberSolution3(nums1)));
    logger.info(String.valueOf(missingNumber.missingNumberSolution3(nums2)));
    logger.info(String.valueOf(missingNumber.missingNumberSolution3(nums3)));
  }
}