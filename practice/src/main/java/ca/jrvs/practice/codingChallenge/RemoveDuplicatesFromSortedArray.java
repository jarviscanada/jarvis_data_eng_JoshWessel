package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/6-Duplicates-from-Sorted-Array-fd8116861f6c4220a4b0dd44434e26a1
 */
public class RemoveDuplicatesFromSortedArray {

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through the array, resulting in O(n)
   * Source: https://leetcode.com/problems/remove-duplicates-from-sorted-array/discuss/11757/My-Solution-:-Time-O(n)-Space-O(1)/12308
   */
  public int removeDuplicates(int[] nums) {
    int i, j = 0;
    for (i = 1; i < nums.length; i++) {
      if (nums[i] != nums[j]) {
        j++;
        nums[j] = nums[i];
      }
    }
    return j + 1;
  }
}