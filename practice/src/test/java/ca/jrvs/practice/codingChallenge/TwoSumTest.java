package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TwoSumTest {

  private TwoSum twoSum;

  @Before
  public void setUp() throws Exception {
    twoSum = new TwoSum();
  }

  @Test
  public void twoSumSolution1() {
    int[] test = { 0, 1, 2, 3, 4, 5 };
    int[] results = twoSum.twoSumSolution1(test, 8);
    for (int result : results) {
      System.out.println(result);
    }
  }

  @Test
  public void twoSumSolution2() {
    int[] test = { 0, 1, 2, 3, 4, 5 };
    int[] results = twoSum.twoSumSolution2(test, 8);
    for (int result : results) {
      System.out.println(result);
    }
  }
}