package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Anagram-99ca1da97abc4629bd9596babd3d20ba
 */
public class ValidAnagram {

  /**
   * Big-O: O(n log n)
   * Justification: The solution uses Arrays.sort, which uses quick sort (which has a time complexity of O(n log n)), resulting in O(n log n)
   * Source: https://leetcode.com/problems/valid-anagram/discuss/66651/Java-solution-using-sort
   */
  public boolean isAnagramSolution1(String string1, String string2) {
    if (string1.length() != string2.length()) {
      return false;
    }
    char[] charArray1 = string1.toCharArray();
    char[] charArray2 = string2.toCharArray();

    Arrays.sort(charArray1);
    Arrays.sort(charArray2);

    return Arrays.equals(charArray1, charArray2);
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through all characters in the String, resulting in O(n)
   * Source: https://leetcode.com/problems/valid-anagram/discuss/66484/Accepted-Java-O(n)-solution-in-5-lines
   */
  public boolean isAnagramSolution2(String string1, String string2) {
    Map<Character, Integer> map = new HashMap<>();
    if (string1.length() != string2.length()) {
      return false;
    }
    for (int i = 0; i < string1.length(); i++) {
      map.put(string1.charAt(i), map.getOrDefault(string1.charAt(i),0) + 1);
      map.put(string2.charAt(i), map.getOrDefault(string2.charAt(i),0) - 1);
    }
    for (char c : map.keySet()) {
      if (map.get(c) != 0) {
        return false;
      }
    }
    return true;
  }
}
