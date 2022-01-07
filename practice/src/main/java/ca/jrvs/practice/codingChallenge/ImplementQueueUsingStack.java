package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

/**
 * ticket: https://www.notion.so/jarvisdev/6-Implement-Queue-using-Stacks-e5d492ed0e1e4a829b1ca42cd205d25e
 */
public class ImplementQueueUsingStack {

  Stack<Integer> stack1 = new Stack<>();
  Stack<Integer> stack2 = new Stack<>();

  Stack<Integer> stack3 = new Stack<>();
  Stack<Integer> stack4 = new Stack<>();

  public ImplementQueueUsingStack() {

  }

  // Approach 1

  /**
   * Big-O: O(n)
   * Justification: The solution uses a while loop to iterate through elements in the stack, resulting in O(n)
   */
  public void push1(int x) {
    if (!stack1.isEmpty()) {
      while(!stack1.isEmpty()) {
        stack2.push(stack1.pop());
      }
    }
    stack2.push(x);
    while(!stack2.isEmpty()) {
      stack1.push(stack2.pop());
    }
  }

  /**
   * Big-O: O(1)
   * Justification: The solution uses stack's pop method, resulting in O(1)
   */
  public int pop1() {
    return stack1.pop();
  }

  /**
   * Big-O: O(1)
   * Justification: The solution uses stack's peek method, resulting in O(1)
   */
  public int peek1() {
    return stack1.peek();
  }

  /**
   * Big-O: O(1)
   * Justification: The solution uses stack's isEmpty method, resulting in O(1)
   */
  public boolean empty1() {
    return stack1.isEmpty();
  }

  // Approach 2

  /**
   * Big-O: O(1)
   * Justification: The solution uses stack's push method, resulting in O(1)
   */
  public void push2(int x) {
    stack3.push(x);
  }

  /**
   * Big-O: Amortized O(1)
   * Justification: The solution uses a while loop which depending on the size of the stack, is either O(1) or O(n), resulting in amortized O(1)
   * Source: https://leetcode.com/problems/implement-queue-using-stacks/solution/
   */
  public int pop2() {
    if (stack4.isEmpty()) {
      while(!stack3.isEmpty()) {
        stack4.push(stack3.pop());
      }
    }
    return stack4.pop();
  }

  /**
   * Big-O: Amortized O(1)
   * Justification: The solution uses a while loop which depending on the size of the stack, is either O(1) or O(n), resulting in amortized O(1)
   * Source: https://leetcode.com/problems/implement-queue-using-stacks/solution/
   */
  public int peek2() {
    if (stack4.isEmpty()) {
      while(!stack3.isEmpty()) {
        stack4.push(stack3.pop());
      }
    }
    return stack4.peek();
  }

  /**
   * Big-O: O(1)
   * Justification: The solution uses stack's isEmpty method, resulting in O(1)
   */
  public boolean empty2() {
    return stack3.isEmpty() && stack4.isEmpty();
  }
}