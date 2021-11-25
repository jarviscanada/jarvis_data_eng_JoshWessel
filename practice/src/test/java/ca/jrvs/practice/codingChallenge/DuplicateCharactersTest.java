package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class DuplicateCharactersTest {

  DuplicateCharacters duplicateCharacters;

  @Before
  public void setUp() throws Exception {
    duplicateCharacters = new DuplicateCharacters();
  }

  @Test
  public void duplicateCharacters() {
    System.out.println(Arrays.toString(duplicateCharacters.duplicateCharacters("no Duplicates")));
    System.out.println(Arrays.toString(duplicateCharacters.duplicateCharacters("Duplicatessss")));
    System.out.println(Arrays.toString(duplicateCharacters.duplicateCharacters("qqqwwwwwerrrrtyyyy")));
    System.out.println(Arrays.toString(duplicateCharacters.duplicateCharacters("many many duplicates")));
    System.out.println(Arrays.toString(duplicateCharacters.duplicateCharacters("aab")));
  }
}