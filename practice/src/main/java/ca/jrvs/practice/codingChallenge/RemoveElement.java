package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Remove-Element-d5c50c8b7e1c4c5ba315e01080bdc9c4
 */
public class RemoveElement {

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through all values in the array, resulting in O(n)
   * Source: https://leetcode.com/problems/remove-element/solution/
   */
  public int removeElement(int[] nums, int val) {
    int i, j = 0;
    for (i = 0; i < nums.length; i++) {
      if (nums[i] != val) {
        nums[j] = nums[i];
        j++;
      }
    }
    return j;
  }
}
