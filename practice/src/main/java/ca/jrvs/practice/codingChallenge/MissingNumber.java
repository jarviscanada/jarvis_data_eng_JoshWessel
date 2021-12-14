package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/jarvisdev/Missing-Number-160d2011f72d41ff824c806dbf1558e0
 */
public class MissingNumber {

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through all values in the array, resulting in O(n)
   * Source: https://leetcode.com/problems/missing-number/discuss/1626882/Java-0ms-Solution
   */
  public int missingNumberSolution1(int[] nums) {
    int sum = nums.length * (nums.length + 1) / 2;
    for (int num : nums) {
      sum -= num;
    }
    return sum;
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses a pair of for loops to iterate through all values in the array, resulting in O(n)
   * Source: https://leetcode.com/problems/missing-number/discuss/1109000/java-solution-using-set
   */
  public int missingNumberSolution2(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      set.add(nums[i]);
    }
    for (int i = 0; i < nums.length + 1; i++) {
      if (set.add(i)) {
        return i;
      }
    }
    return 0;
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through all values in the array, resulting in O(n)
   * Source: https://leetcode.com/problems/missing-number/discuss/946021/Using-Gauss'-formula
   */
  public int missingNumberSolution3(int[] nums) {
    int sum = 0;
    int gaussSum = (nums.length * (nums.length + 1)) / 2;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
    }
    return gaussSum - sum;
  }
}
