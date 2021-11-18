package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringToIntegerTest {

  StringToInteger stringToInteger;

  @Before
  public void setUp() throws Exception {
    stringToInteger = new StringToInteger();
  }

  @Test
  public void myAtoiSolution1() {
    System.out.println(stringToInteger.myAtoiSolution1("158"));
    System.out.println(stringToInteger.myAtoiSolution1("2"));
    System.out.println(stringToInteger.myAtoiSolution1("-28"));
    System.out.println(stringToInteger.myAtoiSolution1("+56"));
    System.out.println(stringToInteger.myAtoiSolution1("notanint"));
    System.out.println(stringToInteger.myAtoiSolution1("000578"));
    System.out.println(stringToInteger.myAtoiSolution1("34kindofanint"));
    System.out.println(stringToInteger.myAtoiSolution1("some48digits"));
    System.out.println(stringToInteger.myAtoiSolution1("05morestufftotest"));
  }

  @Test
  public void myAtoiSolution2() {
    System.out.println(stringToInteger.myAtoiSolution2("158"));
    System.out.println(stringToInteger.myAtoiSolution2("2"));
    System.out.println(stringToInteger.myAtoiSolution2("-28"));
    System.out.println(stringToInteger.myAtoiSolution2("+56"));
    System.out.println(stringToInteger.myAtoiSolution2("notanint"));
    System.out.println(stringToInteger.myAtoiSolution2("000578"));
    System.out.println(stringToInteger.myAtoiSolution2("34kindofanint"));
    System.out.println(stringToInteger.myAtoiSolution2("some48digits"));
    System.out.println(stringToInteger.myAtoiSolution2("05morestufftotest"));
  }
}