package ca.jrvs.practice.codingChallenge;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RotateStringTest {

  private final Logger logger = LoggerFactory.getLogger(RotateStringTest.class);

  RotateString rotateString;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    rotateString = new RotateString();
  }

  @Test
  public void rotateString() {
    logger.info(String.valueOf(rotateString.rotateString("algo", "goal")));
    logger.info(String.valueOf(rotateString.rotateString("loag", "goal")));
    logger.info(String.valueOf(rotateString.rotateString("ogal", "goal")));
    logger.info(String.valueOf(rotateString.rotateString("oalg", "goal")));
    logger.info(String.valueOf(rotateString.rotateString("goal", "goal")));
    logger.info(String.valueOf(rotateString.rotateString("lago", "goal")));
  }
}