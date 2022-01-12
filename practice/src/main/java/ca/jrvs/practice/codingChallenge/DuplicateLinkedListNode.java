package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.resources.LinkedJList;

/**
 * ticket: https://www.notion.so/jarvisdev/7-Duplicate-LinkedList-Node-92d78367119647099cefc0d54c2fe2f5
 */
public class DuplicateLinkedListNode {

  /**
   * Big-O: O(n)
   * Justification: The solution uses a while loop to traverse the linked list, resulting in O(n)
   * Source: https://leetcode.com/problems/remove-duplicates-from-sorted-list/discuss/28743/remove-duplicates-from-sortedunsorted-list-in-java
   */
  public void removeDuplicateLinkedListNode(LinkedJList<Integer> linkedJList) {
    linkedJList.removeDuplicateNodes();
  }
}