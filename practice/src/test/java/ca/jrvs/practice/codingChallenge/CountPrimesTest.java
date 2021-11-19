package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CountPrimesTest {

  CountPrimes countPrimes;

  @Before
  public void setUp() throws Exception {
    countPrimes = new CountPrimes();
  }

  @Test
  public void countPrimes() {
    System.out.println(countPrimes.countPrimes(10));
    System.out.println(countPrimes.countPrimes(50));
    System.out.println(countPrimes.countPrimes(100));
  }
}