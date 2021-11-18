package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Rotate-String-13f28e2eb6ad47bf8a942af838afd847
 */
public class RotateString {

  /**
   * Big-O: O(n)
   * Justification: The solution uses the contains method, which iterates through the given string, resulting in O(n)
   */
  public boolean rotateString(String s, String goal) {

    return (s.length() == goal.length() && (s + s).contains(goal));
  }
}
