package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/String-to-Integer-atoi-781ab315849c470786c438406e130299
 */
public class DuplicateCharacters {

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through the characters in the string, resulting in O(n)
   * Source(s): https://knpcode.com/java-programs/java-program-find-duplicate-characters-string-with-repetition-count/
   *            https://www.geeksforgeeks.org/print-all-the-duplicates-in-the-input-string/
   */
  public Character[] duplicateCharacters(String string) {
    char[] letters = string.toLowerCase().toCharArray();

    Map<Character, Integer> map = new HashMap<>();

    for (char c : letters) {
      if (c == ' ') {
        continue;
      }
      if (map.containsKey(c)) {
        map.put(c, map.get(c) + 1);
      }
      else {
        map.put(c, 1);
      }
    }

    Map<Character, Integer> dupMap = new HashMap<>();

    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      if ((int)entry.getValue() > 1) {
        dupMap.put(entry.getKey(), entry.getValue());
      }
    }

    return dupMap.keySet().toArray(new Character[0]);
  }
}
