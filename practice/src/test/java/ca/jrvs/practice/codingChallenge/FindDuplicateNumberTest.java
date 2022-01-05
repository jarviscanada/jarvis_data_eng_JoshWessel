package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindDuplicateNumberTest {

  private final Logger logger = LoggerFactory.getLogger(FindDuplicateNumberTest.class);

  FindDuplicateNumber findDuplicateNumber;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    findDuplicateNumber = new FindDuplicateNumber();
  }

  @Test
  public void findDuplicateNumberSolution1() {
    int[] nums1 = { 0, 1, 2, 3, 4, 5, 5 };
    int[] nums2 = { 0, 1, 2, 3, 4, 4, 5 };
    int[] nums3 = { 0, 1, 2, 3, 3, 4, 5 };
    logger.info(String.valueOf(findDuplicateNumber.findDuplicateNumberSolution1(nums1)));
    logger.info(String.valueOf(findDuplicateNumber.findDuplicateNumberSolution1(nums2)));
    logger.info(String.valueOf(findDuplicateNumber.findDuplicateNumberSolution1(nums3)));
  }

  @Test
  public void findDuplicateNumberSolution2() {
    int[] nums1 = { 0, 1, 2, 3, 4, 5, 5 };
    int[] nums2 = { 0, 1, 2, 3, 4, 4, 5 };
    int[] nums3 = { 0, 1, 2, 3, 3, 4, 5 };
    logger.info(String.valueOf(findDuplicateNumber.findDuplicateNumberSolution2(nums1)));
    logger.info(String.valueOf(findDuplicateNumber.findDuplicateNumberSolution2(nums2)));
    logger.info(String.valueOf(findDuplicateNumber.findDuplicateNumberSolution2(nums3)));
  }
}