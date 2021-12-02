package ca.jrvs.practice.codingChallenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ticket: https://www.notion.so/jarvisdev/3-Find-Largest-Smallest-bc7ae382503d4cdf881fdf4f5393203d
 */
public class LinkedListCycle {

  private final Logger logger = LoggerFactory.getLogger(LinkedListCycle.class);

  class Node {
    int value;
    Node next;
    Node(int value) { this.value = value; }
    Node(int value, Node next) { this.value = value; this.next = next; }
  }
  Node head;

  public Node findLast(Node head) {
    Node current = head;
    while (current.next != null) {
      current = current.next;
    }
    return current;
  }

  public void addNode(int value) {
    if (head == null) {
      head = new Node(value);
      logger.info("Added a head node with value: " + value);
    }
    else {
      logger.info("Added a new node after " + findLast(head) + ", with value: " + value);
      findLast(head).next = new Node(value);
    }
  }

  public void addNode(int value, Node next) {
    if (head == null) {
      head = new Node(value);
      logger.info("Added a head node with value: " + value);
    }
    else {
      logger.info("Added a new node after " + findLast(head) + ", with value: " + value + " pointing to " + next);
      findLast(head).next = new Node(value, next);
    }
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses a while loop to traverse the linked list, resulting in O(n)
   * Source: https://leetcode.com/problems/linked-list-cycle/discuss/1605415/99.75-fastest-Two-Pointer-Solution
   */
  public boolean hasCycle(Node head) {

    if (head == null || head.next == null) {
      return false;
    }

    Node fast = head.next;
    Node slow = head;

    while (fast != null && slow != null) {
      fast = fast.next;
      if (fast != null) {
        fast = fast.next;
        slow = slow.next;
      }
      if (fast != null && fast.equals(slow)) {
        return true;
      }
    }
    return false;
  }
}
