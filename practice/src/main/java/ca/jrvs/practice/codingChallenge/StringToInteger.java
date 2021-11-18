package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/String-to-Integer-atoi-781ab315849c470786c438406e130299
 */
public class StringToInteger {

  /**
   * Big-O:
   * Justification:
   */
  public int myAtoiSolution1(String s) {
    int sign = 1;
    int i = 0;
    while (s.charAt(i) == ' ') {
      i++;
    }
    if (s.charAt(i) == '-') {
      sign = -1;
      i++;
    }
    else if (s.charAt(i) == '+') {
      i++;
    }
    int value = 0;
    int startIndex = i;
    while (i < s.length()) {
      if (!Character.isDigit(s.charAt(i)))
        break;
      int nextIndex = i + 1;
      if (nextIndex >= s.length())
        nextIndex = s.length() - 1;
      if (!Character.isDigit(s.charAt(nextIndex)) || i + 1 == s.length()) {
        value = Integer.parseInt(s.substring(startIndex, i + 1));
        break;
      }
      i++;
    }
    return value * sign;
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses a while loop to iterate through the characters in the string, resulting in O(n)
   */
  public int myAtoiSolution2(String s) {
    int sign = 1;
    int i = 0;
    while (s.charAt(i) == ' ') {
      i++;
    }
    if (s.charAt(i) == '-') {
      sign = -1;
      i++;
    }
    else if (s.charAt(i) == '+') {
      i++;
    }
    int value = 0;
    while (i < s.length()) {
      if (!Character.isDigit(s.charAt(i)))
        break;
      value = 10 * value + s.charAt(i++) - '0';
    }
    return value * sign;
  }
}