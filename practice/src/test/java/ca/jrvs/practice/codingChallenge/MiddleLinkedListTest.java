package ca.jrvs.practice.codingChallenge;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MiddleLinkedListTest {

  private final Logger logger = LoggerFactory.getLogger(MiddleLinkedListTest.class);

  MiddleLinkedList middleLinkedList1;
  MiddleLinkedList middleLinkedList2;

  @Before
  public void setUp() throws Exception {

    BasicConfigurator.configure();

    middleLinkedList1 = new MiddleLinkedList();
    middleLinkedList1.addNode(5);
    middleLinkedList1.addNode(3);
    middleLinkedList1.addNode(7);
    middleLinkedList1.addNode(9);
    middleLinkedList1.addNode(1);

    middleLinkedList2 = new MiddleLinkedList();
    middleLinkedList2.addNode(5);
    middleLinkedList2.addNode(3);
    middleLinkedList2.addNode(7);
    middleLinkedList2.addNode(9);
    middleLinkedList2.addNode(1);
    middleLinkedList2.addNode(11);
  }

  @Test
  public void middleNode() {
    logger.info("Middle value of LL1: " + String.valueOf(middleLinkedList1.middleNode(middleLinkedList1.head).value));
    logger.info("Middle value of LL2: " + String.valueOf(middleLinkedList2.middleNode(middleLinkedList2.head).value));
  }
}