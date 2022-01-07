package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoveDuplicatesFromSortedArrayTest {

  private final Logger logger = LoggerFactory.getLogger(RemoveDuplicatesFromSortedArrayTest.class);

  RemoveDuplicatesFromSortedArray removeDuplicates;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    removeDuplicates = new RemoveDuplicatesFromSortedArray();
  }

  @Test
  public void removeDuplicates() {
    int[] nums1 = { 0, 1, 1, 2, 2, 3 };
    int[] nums2 = { 0, 1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 8 };
    int[] nums3 = { 0, 0, 1, 1, 1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 8, 8, 9 };
    logger.info("nums1 before duplicate removal: " + Arrays.toString(nums1));
    removeDuplicates.removeDuplicates(nums1);
    logger.info("nums1 after duplicate removal:  " + Arrays.toString(nums1));
    logger.info("");
    logger.info("nums2 before duplicate removal: " + Arrays.toString(nums2));
    removeDuplicates.removeDuplicates(nums2);
    logger.info("nums2 after duplicate removal:  " + Arrays.toString(nums2));
    logger.info("");
    logger.info("nums3 before duplicate removal: " + Arrays.toString(nums3));
    removeDuplicates.removeDuplicates(nums3);
    logger.info("nums3 after duplicate removal:  " + Arrays.toString(nums3));
  }
}