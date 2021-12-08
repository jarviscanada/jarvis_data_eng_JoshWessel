package ca.jrvs.practice.codingChallenge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ticket: https://www.notion.so/jarvisdev/4-Swap-two-numbers-6af8df5175ba4c7a927579445cbea458
 */
public class SwapNumbers {

  private final Logger logger = LoggerFactory.getLogger(SwapNumbers.class);

  /**
   * Big-O: O(1)
   * Justification: The solution does not perform any iteration, and the array is required to be of length 2, resulting in O(1)
   * Source: https://www.javatpoint.com/java-program-to-swap-two-numbers-using-bitwise-operator
   */
  public int[] swapNumbersSolution1(int[] arr) {

    if (arr.length != 2) {
      logger.error("Array has an invalid length. Array must have exactly 2 elements.");
      return null;
    }

    int num1 = arr[0];
    int num2 = arr[1];

    num1 = num1 ^ num2;
    num2 = num1 ^ num2;
    num1 = num1 ^ num2;

    return new int[] { num1, num2 };
  }

  /**
   * Big-O: O(1)
   * Justification: The solution does not perform any iteration, and the array is required to be of length 2, resulting in O(1)
   */
  public int[] swapNumbersSolution2(int[] arr) {

    if (arr.length != 2) {
      logger.error("Array has an invalid length. Array must have exactly 2 elements.");
      return null;
    }

    int num1 = arr[0];
    int num2 = arr[1];

    num1 = num1 + num2;
    num2 = num1 - num2;
    num1 = num1 - num2;

    return new int[] { num1, num2 };
  }
}
