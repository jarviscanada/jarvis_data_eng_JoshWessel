package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SwapNumbersTest {

  private final Logger logger = LoggerFactory.getLogger(SwapNumbersTest.class);

  SwapNumbers swapNumbers;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    swapNumbers = new SwapNumbers();
  }

  @Test
  public void swapNumbersSolution1() {
    int[] numbersToSwap1 = { 0, 1 };
    int[] numbersToSwap2 = { -8, 15 };
    int[] numbersToSwap3 = { -8, 15, 0 };

    logger.info("[0, 1] swapped: " + Arrays.toString(swapNumbers.swapNumbersSolution1(numbersToSwap1)));
    logger.info("[-8, 15] swapped: " + Arrays.toString(swapNumbers.swapNumbersSolution1(numbersToSwap2)));
    logger.info("[-8, 15, 0] swapped: " + Arrays.toString(swapNumbers.swapNumbersSolution1(numbersToSwap3)));
  }

  @Test
  public void swapNumbersSolution2() {
    int[] numbersToSwap1 = { 0, 1 };
    int[] numbersToSwap2 = { -8, 15 };
    int[] numbersToSwap3 = { -8, 15, 0 };

    logger.info("[0, 1] swapped: " + Arrays.toString(swapNumbers.swapNumbersSolution2(numbersToSwap1)));
    logger.info("[-8, 15] swapped: " + Arrays.toString(swapNumbers.swapNumbersSolution2(numbersToSwap2)));
    logger.info("[-8, 15, 0] swapped: " + Arrays.toString(swapNumbers.swapNumbersSolution2(numbersToSwap3)));
  }
}