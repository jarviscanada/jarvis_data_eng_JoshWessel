package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReverseLinkedListTest {

  private final Logger logger = LoggerFactory.getLogger(ReverseLinkedListTest.class);

  ReverseLinkedList reverseLinkedList;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    reverseLinkedList = new ReverseLinkedList();
    reverseLinkedList.addNode(1);
    reverseLinkedList.addNode(2);
    reverseLinkedList.addNode(3);
    reverseLinkedList.addNode(4);
    reverseLinkedList.addNode(5);
  }

  @Test
  public void reverseListIteration() {
    logger.info("Before: " + reverseLinkedList.asArray(reverseLinkedList.head).toString());
    reverseLinkedList.reverseListIteration(reverseLinkedList.head);
    logger.info("After:  " + reverseLinkedList.asArray(reverseLinkedList.head).toString());
  }

  @Test
  public void reverseListRecursion() {
    logger.info("Before: " + reverseLinkedList.asArray(reverseLinkedList.head).toString());
    reverseLinkedList.reverseListRecursion(reverseLinkedList.head);
    logger.info("After:  " + reverseLinkedList.asArray(reverseLinkedList.head).toString());
  }
}