package ca.jrvs.practice.codingChallenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ticket: https://www.notion.so/jarvisdev/2-Middle-of-the-Linked-List-968b2d5e361444049cfaf9ad74b2745b
 */
class Node {
  int value;
  Node next;
  Node(int value) { this.value = value; }
}

public class MiddleLinkedList {

  private final Logger logger = LoggerFactory.getLogger(MiddleLinkedList.class);

  Node head;

  private Node findLast(Node head) {
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

  /**
   * Big-O: O(n)
   * Justification: The solution uses a while loop to iterate through the nodes in the linked list, resulting in O(n), where n is the number of nodes in the linked list
   */
  public Node middleNode(Node head) {

    Node current = head;
    Node middle = current;

    while (current.next != null) {
      if (current.next.next != null) {
        current = current.next.next;
      } else {
        current = current.next;
      }
      middle = middle.next;
    }

    return middle;
  }
}