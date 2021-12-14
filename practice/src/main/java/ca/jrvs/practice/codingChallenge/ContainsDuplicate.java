package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ticket: https://www.notion.so/jarvisdev/5-Contains-Duplicate-8056d70eb4864c9da0ce7321d8e5aecf
 */
public class ContainsDuplicate {

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through all values in the array, resulting in O(n)
   */
  public boolean containsDuplicateSolution1(int[] nums) {
    Map map = new HashMap();
    for (int i = 0; i < nums.length; i++) {
      if (!map.containsValue(nums[i])) {
        map.put(i, nums[i]);
      }
      else {
        return true;
      }
    }
    return false;
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through all values in the array, resulting in O(n)
   */
  public boolean containsDuplicateSolution2(int[] nums) {
    Set set = new HashSet();
    for (int num : nums) {
      if (!set.contains(num)) {
        set.add(num);
      }
      else {
        return true;
      }
    }
    return false;
  }
}
