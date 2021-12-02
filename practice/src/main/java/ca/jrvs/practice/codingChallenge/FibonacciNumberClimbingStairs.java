package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-28c27a2489ed43979a213a2044eb6546
 */
public class FibonacciNumberClimbingStairs {

  /**
   * Big-O: O(2^n)
   * Justification: The solution adds the result of 2 recursive functions, resulting in O(2^n) (exponential time)
   */
  public int fibNumRecursive(int n) {
    if (n <= 1) {
      return n;
    }
    else {
      return fibNumRecursive(n - 1) + fibNumRecursive(n - 2);
    }
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through all values less than n, resulting in O(n)
   */
  public int fibNumDP(int n) {
    int[] arr = new int[n + 1];

    arr[0] = 0;
    arr[1] = 1;

    for (int i = 2; i <= n; i++) {
      arr[i] = arr[i - 1] + arr[i - 2];
    }

    return arr[n];
  }

  /**
   * Big-O: O(2^n)
   * Justification: The solution adds the result of 2 recursive functions, resulting in O(2^n) (exponential time)
   */
  public int climbStairsRecursive(int n) {
    if (n == 1) {
      return 1;
    }
    else if (n == 2) {
      return 2;
    }
    else {
      return climbStairsRecursive(n - 1) + climbStairsRecursive(n - 2);
    }
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iteratively add new values to the array, resulting in O(n)
   */
  public int climbStairsDP(int n) {
    if (n <= 1) {
      return 1;
    }

    int[] arr = new int[n + 1];

    arr[0] = 0;
    arr[1] = 1;
    arr[2] = 2;

    for (int i = 3; i <= n; i++) {
      arr[i] = arr[i - 1] + arr[i - 2];
    }

    return arr[n];
  }
}
