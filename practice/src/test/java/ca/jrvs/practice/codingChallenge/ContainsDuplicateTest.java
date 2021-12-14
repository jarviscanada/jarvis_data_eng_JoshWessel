package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContainsDuplicateTest {

  private final Logger logger = LoggerFactory.getLogger(ContainsDuplicateTest.class);

  ContainsDuplicate containsDuplicate;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    containsDuplicate = new ContainsDuplicate();
  }

  @Test
  public void containsDuplicateSolution1() {
    int[] nums1 = { 0, 1, 2, 3, 4, 5 };
    int[] nums2 = { 0, 1, 2, 3, 4, 5, 5 };
    logger.info(String.valueOf(containsDuplicate.containsDuplicateSolution1(nums1)));
    logger.info(String.valueOf(containsDuplicate.containsDuplicateSolution1(nums2)));
  }

  @Test
  public void containsDuplicateSolution2() {
    int[] nums1 = { 0, 1, 2, 3, 4, 5 };
    int[] nums2 = { 0, 1, 2, 3, 4, 5, 5 };
    logger.info(String.valueOf(containsDuplicate.containsDuplicateSolution2(nums1)));
    logger.info(String.valueOf(containsDuplicate.containsDuplicateSolution2(nums2)));
  }
}