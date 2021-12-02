package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Palindrome-8193727101234f78b2f3f59f518a2203
 */
public class ValidPalindrome {

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through the characters in the string, resulting in O(n)
   * Source: https://leetcode.com/problems/valid-palindrome/discuss/40029/Accepted-pretty-Java-solution(271ms)
   */
  public boolean validPalindromeSolution1(String string) {
    string = string.toLowerCase();
    char[] characters = string.toCharArray();
    int i , j;
    for (i = 0, j = characters.length - 1; i < j;) {
      if (!Character.isLetterOrDigit(characters[i])) {
        i++;
      }
      else if (!Character.isLetterOrDigit(characters[j])) {
        j--;
      }
      else if (characters[i] != characters[j]) {
        return false;
      }
      else {
        i++;
        j--;
      }
    }
    return true;
  }

  /**
   * Big-O: O(n)
   * Justification: The solution uses recursion, which will call the method n times, where n is the number of characters in the string, resulting in O(n)
   * Source: https://leetcode.com/problems/valid-palindrome/discuss/391810/Java-Solutions-to-Valid-Palindrome-I-and-II-with-Explanation-(SubPalindrome-Iteration-and-Recursion)
   */
  public boolean validPalindromeSolution2(String string) {
    string = string.toLowerCase();
    if (!Character.isLetterOrDigit(string.charAt(0)) && string.length() > 1) {
      string = string.substring(1, string.length());
      return validPalindromeSolution2(string);
    }
    else if (!Character.isLetterOrDigit(string.charAt(string.length() - 1)) && string.length() > 1) {
      string = string.substring(0, string.length() - 1);
      return validPalindromeSolution2(string);
    }

    if (string.length() >= 2 && string.charAt(0) != string.charAt(string.length() - 1)) {
      return false;
    }
    if (string.length() == 1 || string.length() == 2 && string.charAt(0) != string.charAt(1)) {
      return true;
    }
    else {
      string = string.substring(1, string.length() - 1);
      return validPalindromeSolution2(string);
    }
  }
}
