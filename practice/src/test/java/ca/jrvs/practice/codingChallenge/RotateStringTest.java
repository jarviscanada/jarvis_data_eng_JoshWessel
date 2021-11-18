package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RotateStringTest {

  RotateString rotateString;

  @Before
  public void setUp() throws Exception {
    rotateString = new RotateString();
  }

  @Test
  public void rotateString() {
    System.out.println(rotateString.rotateString("algo", "goal"));
    System.out.println(rotateString.rotateString("loag", "goal"));
    System.out.println(rotateString.rotateString("ogal", "goal"));
    System.out.println(rotateString.rotateString("oalg", "goal"));
    System.out.println(rotateString.rotateString("goal", "goal"));
    System.out.println(rotateString.rotateString("lago", "goal"));
  }
}