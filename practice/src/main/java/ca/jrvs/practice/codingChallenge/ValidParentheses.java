package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * ticket: https://www.notion.so/jarvisdev/4-Valid-Parentheses-7476acfd027e4204a90c487c32ad9c14
 */
public class ValidParentheses {

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through all characters in the String, resulting in O(n)
   * Sources: https://leetcode.com/problems/valid-parentheses/discuss/9178/Short-java-solution, https://leetcode.com/problems/valid-parentheses/discuss/747621/Java-stack-and-hash-map-solution
   */
  public boolean isValid(String string) {
    if ((string.length() & 1) != 0) {
      return false;
    }
    Map<Character, Character> brackets = new HashMap<>();
    brackets.put('(',')');
    brackets.put('[',']');
    brackets.put('{','}');
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < string.length(); i++) {
      if (brackets.containsKey(string.charAt(i))) {
        stack.push(string.charAt(i));
      }
      else if (stack.isEmpty() || brackets.get(stack.pop()) != string.charAt(i)) {
        return false;
      }
    }
    return stack.isEmpty();
  }
}