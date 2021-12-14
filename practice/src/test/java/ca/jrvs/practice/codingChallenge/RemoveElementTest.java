package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoveElementTest {

  private final Logger logger = LoggerFactory.getLogger(RemoveElementTest.class);

  RemoveElement removeElement;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    removeElement = new RemoveElement();
  }

  @Test
  public void removeElement() {
    int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    removeElement.removeElement(nums, 3);
    logger.info(Arrays.toString(nums));

    removeElement.removeElement(nums, 8);
    logger.info(Arrays.toString(nums));
  }
}