package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ticket: https://www.notion.so/jarvisdev/5-Implement-Stack-using-Queue-1a2bfca195124d54a98a02bfa3da62b5
 */
public class ImplementStackUsingQueue {

  // 2 queue approach
  Queue<Integer> queue1 = new LinkedList<>();
  Queue<Integer> queue2 = new LinkedList<>();
  int top;

  // 1 queue approach
  Queue<Integer> queue3 = new LinkedList<>();

  public ImplementStackUsingQueue() {

  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses a while loop to iterate through all elements in the queue, resulting in O(n)
   * Source: https://leetcode.com/problems/implement-stack-using-queues/solution/
   */
  public void push2Q(int x) {
    queue1.add(x);
    top = x;
  }

  public int pop2Q() {
    while (queue1.size() > 1) {
      top = queue1.remove();
      queue2.add(top);
    }
    int popped = queue1.remove();
    Queue<Integer> queueTemp = queue1;
    queue1 = queue2;
    queue2 = queueTemp;
    return popped;
  }

  public int top2Q() {
    return top;
  }

  public boolean empty2Q() {
    return queue1.isEmpty();
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through all elements in the queue, resulting in O(n)
   * Source: https://leetcode.com/problems/implement-stack-using-queues/solution/
   */
  public void push1Q(int x) {
    queue3.add(x);
    for (int i = 0; i < queue3.size() - 1; i++) {
      queue3.add(pop1Q());
    }
  }

  public int pop1Q() {
    return queue3.remove();
  }

  public int top1Q() {
    return queue3.peek();
  }

  public boolean empty1Q() {
    return queue3.isEmpty();
  }
}
