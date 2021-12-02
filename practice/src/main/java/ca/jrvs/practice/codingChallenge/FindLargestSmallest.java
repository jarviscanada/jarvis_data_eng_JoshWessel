package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * ticket: https://www.notion.so/jarvisdev/3-Find-Largest-Smallest-bc7ae382503d4cdf881fdf4f5393203d
 */
public class FindLargestSmallest {

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through all values in the array, resulting in O(n)
   */
  public int findLargestApproach1(int[] arr) {
    int largest = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] > largest) {
        largest = arr[i];
      }
    }
    return largest;
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through all values in the array, resulting in O(n)
   */
  public int findSmallestApproach1(int[] arr) {
    int smallest = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] < smallest) {
        smallest = arr[i];
      }
    }
    return smallest;
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses Stream's max() method which (I assume) iterates through the stream of elements, resulting in O(n)
   */
  public int findLargestApproach2(int[] arr) {
    return Arrays.stream(arr).max().getAsInt();
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses Stream's min() method which (I assume) iterates through the stream of elements, resulting in O(n)
   */
  public int findSmallestApproach2(int[] arr) {
    return Arrays.stream(arr).min().getAsInt();
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses Collections.max(), which uses a while loop to iterate through all values in the collection, resulting in O(n)
   * Source: https://www.techiedelight.com/convert-int-array-list-integer/
   */
  public int findLargestApproach3(int[] arr) {
    return Collections.max(Arrays.stream(arr).boxed().collect(Collectors.toList()));
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses Collections.min(), which uses a while loop to iterate through all values in the collection, resulting in O(n)
   * Source: https://www.techiedelight.com/convert-int-array-list-integer/
   */
  public int findSmallestApproach3(int[] arr) {
    return Collections.min(Arrays.stream(arr).boxed().collect(Collectors.toList()));
  }
}
