package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-b28529c4fab146f5bc819c89a39af9d0
 */
public class CheckIfNumberIsEvenOrOdd {

  /**
   * Big-O: O(1)
   * Justification: The solution uses only an if statement containing a modulo operation comparison, resulting in O(1)
   */
  public String checkIfNumberIsEvenOrOddSolution1(int num) {
    if (num % 2 == 0) {
      return "even";
    }
    return "odd";
  }

  /**
   * Big-O: O(1)
   * Justification: The solution uses only an if statement containing a bitwise operation comparison, resulting in O(1)
   * Source: https://www.geeksforgeeks.org/check-if-a-number-is-odd-or-even-using-bitwise-operators/
   */
  public String checkIfNumberIsEvenOrOddSolution2(int num) {
    if ((num | 1) > num) {
      return "even";
    }
    return "odd";
  }
}