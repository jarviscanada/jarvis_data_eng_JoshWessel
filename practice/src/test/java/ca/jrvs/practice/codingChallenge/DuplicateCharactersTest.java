package ca.jrvs.practice.codingChallenge;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class DuplicateCharactersTest {

  private final Logger logger = LoggerFactory.getLogger(DuplicateCharactersTest.class);

  DuplicateCharacters duplicateCharacters;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    duplicateCharacters = new DuplicateCharacters();
  }

  @Test
  public void duplicateCharacters() {
    logger.info(String.valueOf(Arrays.toString(duplicateCharacters.duplicateCharacters("no Duplicates"))));
    logger.info(String.valueOf(Arrays.toString(duplicateCharacters.duplicateCharacters("Duplicatessss"))));
    logger.info(String.valueOf(Arrays.toString(duplicateCharacters.duplicateCharacters("qqqwwwwwerrrrtyyyy"))));
    logger.info(String.valueOf(Arrays.toString(duplicateCharacters.duplicateCharacters("many many duplicates"))));
    logger.info(String.valueOf(Arrays.toString(duplicateCharacters.duplicateCharacters("aab"))));
  }
}