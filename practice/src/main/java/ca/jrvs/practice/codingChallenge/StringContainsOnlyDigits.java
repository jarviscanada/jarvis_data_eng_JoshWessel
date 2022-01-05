package ca.jrvs.practice.codingChallenge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ticket: https://www.notion.so/jarvisdev/Check-if-a-String-contains-only-digits-0cafa3bbfbb14f679ce72adf34163a24
 */
public class StringContainsOnlyDigits {

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through all characters in the string, resulting in O(n)
   */
  public boolean stringContainsOnlyDigitsSolution1(String input) {
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) > 57 || input.charAt(i) < 48) {
        return false;
      }
    }
    return true;
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through all characters in the string, resulting in O(n)
   */
  public boolean stringContainsOnlyDigitsSolution2(String input) {
    for (int i = 0; i < input.length(); i++) {
      if (!Character.isDigit(input.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses Pattern and Matcher objects, which iterate through all characters in the string, resulting in O(n)
   * Source: https://stackoverflow.com/questions/40896475/time-complexity-of-regular-expression-in-java?noredirect=1&lq=1
   */
  public boolean stringContainsOnlyDigitsSolution3(String input) {
    Pattern pattern = Pattern.compile("^\\d{1,99999999}$");
    Matcher matcher = pattern.matcher(input);
    return matcher.find();
  }
}