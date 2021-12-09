package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidAnagramTest {

  private final Logger logger = LoggerFactory.getLogger(ValidAnagramTest.class);

  ValidAnagram validAnagram;

  String anagram1 = "cat";
  String anagram2 = "act";

  String anagram3 = "abcdefg";
  String anagram4 = "bdfaceg";

  String anagram5 = "abc";
  String anagram6 = "abd";

  String anagram7 = "abc";
  String anagram8 = "abcd";

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    validAnagram = new ValidAnagram();
  }

  @Test
  public void isAnagramSolution1() {
    logger.info(anagram1 + " and " + anagram2 + " are anagrams of each other: " + validAnagram.isAnagramSolution1(anagram1, anagram2));
    logger.info(anagram3 + " and " + anagram4 + " are anagrams of each other: " + validAnagram.isAnagramSolution1(anagram3, anagram4));
    logger.info(anagram5 + " and " + anagram6 + " are anagrams of each other: " + validAnagram.isAnagramSolution1(anagram5, anagram6));
    logger.info(anagram7 + " and " + anagram8 + " are anagrams of each other: " + validAnagram.isAnagramSolution1(anagram7, anagram8));
  }

  @Test
  public void isAnagramSolution2() {
    logger.info(anagram1 + " and " + anagram2 + " are anagrams of each other: " + validAnagram.isAnagramSolution2(anagram1, anagram2));
    logger.info(anagram3 + " and " + anagram4 + " are anagrams of each other: " + validAnagram.isAnagramSolution2(anagram3, anagram4));
    logger.info(anagram5 + " and " + anagram6 + " are anagrams of each other: " + validAnagram.isAnagramSolution2(anagram5, anagram6));
    logger.info(anagram7 + " and " + anagram8 + " are anagrams of each other: " + validAnagram.isAnagramSolution2(anagram7, anagram8));
  }
}