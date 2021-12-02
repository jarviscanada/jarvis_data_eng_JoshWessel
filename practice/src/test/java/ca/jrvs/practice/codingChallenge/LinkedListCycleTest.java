package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkedListCycleTest {

  private final Logger logger = LoggerFactory.getLogger(LinkedListCycleTest.class);

  LinkedListCycle linkedListCycle;
  LinkedListCycle linkedListNotCycle;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();

    linkedListCycle = new LinkedListCycle();

    linkedListCycle.addNode(1);
    linkedListCycle.addNode(2);
    linkedListCycle.addNode(3);
    LinkedListCycle.Node node3 = linkedListCycle.findLast(linkedListCycle.head);
    linkedListCycle.addNode(4);
    linkedListCycle.addNode(5, node3);

    linkedListNotCycle = new LinkedListCycle();

    linkedListNotCycle.addNode(1);
    linkedListNotCycle.addNode(2);
    linkedListNotCycle.addNode(3);
    linkedListNotCycle.addNode(4);
    linkedListNotCycle.addNode(5);
  }

  @Test
  public void hasCycle() {
    logger.info("Linked List (cycle):    " + String.valueOf(linkedListCycle.hasCycle(linkedListCycle.head)));
    logger.info("Linked List (no cycle): " + String.valueOf(linkedListNotCycle.hasCycle(linkedListNotCycle.head)));
  }
}