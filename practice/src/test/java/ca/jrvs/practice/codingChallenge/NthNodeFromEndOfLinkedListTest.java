package ca.jrvs.practice.codingChallenge;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NthNodeFromEndOfLinkedListTest {

  private final Logger logger = LoggerFactory.getLogger(NthNodeFromEndOfLinkedListTest.class);

  NthNodeFromEndOfLinkedList nthNodeFromEndOfLinkedList;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();

    nthNodeFromEndOfLinkedList = new NthNodeFromEndOfLinkedList();
    nthNodeFromEndOfLinkedList.addNode(1);
    nthNodeFromEndOfLinkedList.addNode(2);
    nthNodeFromEndOfLinkedList.addNode(3);
    nthNodeFromEndOfLinkedList.addNode(4);
    nthNodeFromEndOfLinkedList.addNode(5);
  }

  @Test
  public void removeNthFromEnd() {
    logger.info(String.valueOf(nthNodeFromEndOfLinkedList.asArray(nthNodeFromEndOfLinkedList.head)));
    nthNodeFromEndOfLinkedList.removeNthFromEnd(nthNodeFromEndOfLinkedList.head, 1);
    logger.info(String.valueOf(nthNodeFromEndOfLinkedList.asArray(nthNodeFromEndOfLinkedList.head)));
  }
}