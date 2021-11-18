package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/Two-Sum-115fe3dd777945f89da7a808c663c642
 */
public class TwoSum {

  /**
   * Big-O: O(n^2)
   * Justification: Solution loops through array (O(n)), then for each element, loops through entire array (O(n)), resulting in O(n^2)
   */
  public int[] twoSumSolution1(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          return new int[] { i, j };
        }
      }
    }
    return null;
  }

  /**
   * Big-O: O(n)
   * Justification: Solution uses a for loop (2 actually, but not nested), resulting in O(n)
   */
  public int[] twoSumSolution2(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], i);
    }
    for (int i = 0; i < nums.length; i++) {
      int difference = target - nums[i];
      if (map.containsValue(difference) && map.get(difference) != i) {
        return new int[] { map.get(difference), i };
      }
    }
    return null;
  }
}