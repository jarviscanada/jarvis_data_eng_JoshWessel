package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ticket: https://www.notion.so/jarvisdev/5-Reverse-Linked-List-8d4087c3ba6d428282a0de66f2e6c00d
 */
public class ReverseLinkedList {

  private final Logger logger = LoggerFactory.getLogger(ReverseLinkedList.class);

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
   * Justification: The solution uses a while loop to iterate through all nodes in the linked list, resulting in O(n)
   * Sources: https://leetcode.com/problems/reverse-linked-list/discuss/1630053/Java-Solution
   */
  public Node reverseListIteration(Node head) {
    Node curr = head;
    Node next = null;
    Node prev = null;
    while (curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    return this.head = prev;
  }

  /**
   * Big-O: O(n)
   * Justification: The solution recursively visits each node in the linked list, resulting in O(n)
   * Source: https://leetcode.com/problems/reverse-linked-list/discuss/58125/In-place-iterative-and-recursive-Java-solution
   */
  public Node reverseListRecursion(Node head) {
    return reverseListRecursionImplementation(head, null);
  }

  public Node reverseListRecursionImplementation(Node head, Node newHead) {
    if (head == null) {
      return this.head = newHead;
    }
    Node next = head.next;
    head.next = newHead;
    return reverseListRecursionImplementation(next, head);
  }
}