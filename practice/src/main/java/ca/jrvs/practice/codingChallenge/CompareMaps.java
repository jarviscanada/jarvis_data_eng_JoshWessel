package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.resources.JMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/4-How-to-compare-two-maps-b21cd5c6563742238f3d9de0c44cef1a
 */
public class CompareMaps {

  /**
   * Big-O: O(n)
   * Justification: The solution iterates over all entries in the maps, resulting in O(n)
   */
  public <K,V> boolean compareMapsSolution1(Map<K,V> m1, Map<K,V> m2) {
    return m1.entrySet().equals(m2.entrySet());
  }

  /**
   * Big-O: O(n)
   * Justification: The solution iterates over all entries in the maps, resulting in O(n)
   */
  public <K,V> boolean compareMapsSolution2(JMap<K,V> m1, JMap<K,V> m2) {
    return m1.equals(m2);
  }
}
