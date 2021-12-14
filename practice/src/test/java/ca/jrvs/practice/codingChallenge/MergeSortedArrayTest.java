package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MergeSortedArrayTest {

  private final Logger logger = LoggerFactory.getLogger(RemoveElementTest.class);

  MergeSortedArray mergeSortedArray;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    mergeSortedArray = new MergeSortedArray();
  }

  @Test
  public void merge() {
    int[] nums1 = { 0, 1, 2 , 0, 0, 0 };
    int[] nums2 = { 3, 4, 5 };
    mergeSortedArray.merge(nums1, 3, nums2, 3);
    logger.info(Arrays.toString(nums1));
  }
}