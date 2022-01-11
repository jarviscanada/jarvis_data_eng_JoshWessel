package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.resources.LinkedJList;
import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuplicateLinkedListNodeTest {

  private final Logger logger = LoggerFactory.getLogger(DuplicateLinkedListNodeTest.class);

  DuplicateLinkedListNode duplicateLinkedListNode;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    duplicateLinkedListNode = new DuplicateLinkedListNode();
  }

  @Test
  public void removeDuplicateLinkedListNode() {
    LinkedJList<Integer> linkedJList = new LinkedJList<>();
    linkedJList.add(1);
    linkedJList.add(8);
    linkedJList.add(4);
    linkedJList.add(7);
    linkedJList.add(4);
    linkedJList.add(1);
    linkedJList.add(5);
    linkedJList.add(4);
    logger.info("Before:");
    Object[] arrayBefore = linkedJList.toArray();
    for (int i = 0; i < arrayBefore.length; i++) {
      logger.info("[" + i + "]: " + arrayBefore[i]);
    }
    duplicateLinkedListNode.removeDuplicateLinkedListNode(linkedJList);
    logger.info("After:");
    Object[] arrayAfter = linkedJList.toArray();
    for (int i = 0; i < arrayAfter.length; i++) {
      logger.info("[" + i + "]: " + arrayAfter[i]);
    }
  }
}