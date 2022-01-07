package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImplementQueueUsingStackTest {

  private final Logger logger = LoggerFactory.getLogger(ImplementQueueUsingStackTest.class);

  ImplementQueueUsingStack queue;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    queue = new ImplementQueueUsingStack();
  }

  @Test
  public void testQueue1() {
    queue.push1(5);
    queue.push1(8);
    queue.push1(2);
    int param1 = queue.pop1();
    int param2 = queue.peek1();
    boolean param3 = queue.empty1();

    logger.info("pop: " + param1 + ", peek: " + param2 + ", empty: " + param3);
  }

  @Test
  public void testQueue2() {
    queue.push2(5);
    queue.push2(8);
    queue.push2(2);
    int param1 = queue.pop2();
    int param2 = queue.peek2();
    boolean param3 = queue.empty2();

    logger.info("pop: " + param1 + ", peek: " + param2 + ", empty: " + param3);
  }
}