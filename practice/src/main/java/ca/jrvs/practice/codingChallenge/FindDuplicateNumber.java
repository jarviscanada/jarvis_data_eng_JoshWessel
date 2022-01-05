package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/jarvisdev/Find-the-Duplicate-Number-038d09e567b94861b4ab93cab83a0dde
 */
public class FindDuplicateNumber {

  /**
   * Big-O: O(n log n)
   * Justification: The solution uses Arrays.sort, which takes O(n log n) time, resulting in O(n log n)
   * Source: https://leetcode.com/problems/find-the-duplicate-number/solution/
   */
  public int findDuplicateNumberSolution1(int[] nums) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] == nums[i + 1]) {
        return nums[i];
      }
    }
    return -1;
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through all elements in the array, resulting in O(n)
   */
  public int findDuplicateNumberSolution2(int[] nums) {
    Set set = new HashSet();
    for (int i = 0; i < nums.length; i++) {
      if (set.contains(nums[i])) {
        return nums[i];
      }
      else {
        set.add(nums[i]);
      }
    }
    return -1;
  }
}