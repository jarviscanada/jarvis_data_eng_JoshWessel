package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

public class FibonacciNumberClimbingStairsTest {

  FibonacciNumberClimbingStairs fibonacciNumberClimbingStairs;

  @Before
  public void setUp() throws Exception {
    fibonacciNumberClimbingStairs = new FibonacciNumberClimbingStairs();
  }

  @Test
  public void fibNumRecursive() {
    System.out.println(fibonacciNumberClimbingStairs.fibNumRecursive(20));
  }

  @Test
  public void fibNumDP() {
    System.out.println(fibonacciNumberClimbingStairs.fibNumDP(20));
  }

  @Test
  public void climbStairsRecursive() {
    System.out.println(fibonacciNumberClimbingStairs.climbStairsRecursive(20));
  }

  @Test
  public void climbStairsDP() {
    System.out.println(fibonacciNumberClimbingStairs.climbStairsDP(20));
  }
}