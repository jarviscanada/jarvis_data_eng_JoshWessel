package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ticket: https://www.notion.so/jarvisdev/Nth-Node-From-End-of-LinkedList-1b6de1476ed6420280d499e84e687fc9
 */
public class NthNodeFromEndOfLinkedList {

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

  public ArrayList asArray(Node head) {
    ArrayList arrayList = new ArrayList();
    while (head != null) {
      arrayList.add(head.value);
      head = head.next;
    }
    return arrayList;
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses a while loop to traverse the linked list, resulting in O(n)
   * Sources: https://leetcode.com/problems/remove-nth-node-from-end-of-list/discuss/1608502/Simple-Java-Solution-Explained-or-O(n)-or-Two-pointer-or-Beats-100
   *          https://leetcode.com/problems/remove-nth-node-from-end-of-list/discuss/8804/Simple-Java-solution-in-one-pass
   */
  public Node removeNthFromEnd(Node head, int n) {
    Node start = new Node(0);
    start.next = head;
    Node fast = start;
    Node slow = start;

    for (int i = 0; i < n; i++) {
      if (fast.next != null) {
        fast = fast.next;
      }
    }

    while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;
    }

    slow.next = slow.next.next;
    this.head = start.next;

    return start.next;
  }
}
