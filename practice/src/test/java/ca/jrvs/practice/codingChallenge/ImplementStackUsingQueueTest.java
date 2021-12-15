package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImplementStackUsingQueueTest {

  private final Logger logger = LoggerFactory.getLogger(ImplementStackUsingQueueTest.class);

  ImplementStackUsingQueue stack;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    stack = new ImplementStackUsingQueue();
  }

  @Test
  public void test1Queue() {
    stack.push1Q(5);
    stack.push1Q(8);
    stack.push1Q(2);
    int param1 = stack.pop1Q();
    int param2 = stack.top1Q();
    boolean param3 = stack.empty1Q();

    logger.info("pop: " + param1 + ", top: " + param2 + ", empty: " + param3);
  }

  @Test
  public void test2Queues() {
    stack.push2Q(5);
    stack.push2Q(8);
    stack.push2Q(2);
    int param4 = stack.pop2Q();
    int param5 = stack.top2Q();
    boolean param6 = stack.empty2Q();

    logger.info("pop: " + param4 + ", top: " + param5 + ", empty: " + param6);
  }
}