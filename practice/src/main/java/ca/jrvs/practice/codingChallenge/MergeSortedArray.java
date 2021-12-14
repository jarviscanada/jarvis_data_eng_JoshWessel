package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Merge-Sorted-Array-4395fa95300b4313886be667d834cd6b
 */
public class MergeSortedArray {

  /**
   * Big-O: O(n log n)
   * Justification: The solution follows a merge sort algorithm, which as a tree of merges, has a height of logn. This is then multiplied by O(n) for the size of the arrays, resulting in O(n log n)
   * Sources: https://leetcode.com/problems/merge-sorted-array/discuss/29578/Share-my-accepted-Java-solution!, https://stackoverflow.com/questions/7801861/why-is-merge-sort-worst-case-run-time-o-n-log-n
   */
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int i1 = m - 1;
    int i2 = n - 1;
    int lastIndex = m + n - 1;
    while (i1 >= 0 && i2 >= 0) {
      if (nums1[i1] > nums2[i2]) {
        nums1[lastIndex] = nums1[i1];
        i1--;
      }
      else {
        nums1[lastIndex] = nums2[i2];
        i2--;
      }
      lastIndex--;
    }
    while (i2 >= 0) {
      nums1[lastIndex] = nums2[i2];
      lastIndex--;
      i2--;
    }
  }
}
