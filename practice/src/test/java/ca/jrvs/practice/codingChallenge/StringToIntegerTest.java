package ca.jrvs.practice.codingChallenge;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringToIntegerTest {

  private final Logger logger = LoggerFactory.getLogger(StringToIntegerTest.class);

  StringToInteger stringToInteger;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    stringToInteger = new StringToInteger();
  }

  @Test
  public void myAtoiSolution1() {
    logger.info(String.valueOf(stringToInteger.myAtoiSolution1("158")));
    logger.info(String.valueOf(stringToInteger.myAtoiSolution1("2")));
    logger.info(String.valueOf(stringToInteger.myAtoiSolution1("-28")));
    logger.info(String.valueOf(stringToInteger.myAtoiSolution1("+56")));
    logger.info(String.valueOf(stringToInteger.myAtoiSolution1("notanint")));
    logger.info(String.valueOf(stringToInteger.myAtoiSolution1("000578")));
    logger.info(String.valueOf(stringToInteger.myAtoiSolution1("34kindofanint")));
    logger.info(String.valueOf(stringToInteger.myAtoiSolution1("some48digits")));
    logger.info(String.valueOf(stringToInteger.myAtoiSolution1("05morestufftotest")));
  }

  @Test
  public void myAtoiSolution2() {
    logger.info(String.valueOf(stringToInteger.myAtoiSolution2("158")));
    logger.info(String.valueOf(stringToInteger.myAtoiSolution2("2")));
    logger.info(String.valueOf(stringToInteger.myAtoiSolution2("-28")));
    logger.info(String.valueOf(stringToInteger.myAtoiSolution2("+56")));
    logger.info(String.valueOf(stringToInteger.myAtoiSolution2("notanint")));
    logger.info(String.valueOf(stringToInteger.myAtoiSolution2("000578")));
    logger.info(String.valueOf(stringToInteger.myAtoiSolution2("34kindofanint")));
    logger.info(String.valueOf(stringToInteger.myAtoiSolution2("some48digits")));
    logger.info(String.valueOf(stringToInteger.myAtoiSolution2("05morestufftotest")));
  }
}