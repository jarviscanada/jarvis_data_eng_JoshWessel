package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Print-letter-with-number-24e7feb87816466589c06a7bcbe3729b
 */
public class PrintLetterWithNumber {

  /**
   * Big-O: O(n)
   * Justification: The solution uses a for loop to iterate through the string, resulting in O(n)
   * Sources: https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html
   *          https://www.delftstack.com/howto/java/add-char-to-string-java/
   */
  String printLetterWithNumber(String string) {
    for (int i = 0; i < string.length(); i++) {
      StringBuilder stringBuilder = new StringBuilder();
      char letter = string.charAt(i);
      int asciiNum = 0;
      if (Character.isLetter(letter)) {
        if (Character.isLowerCase(letter)) {
          asciiNum = letter - 96;
        }
        else if (Character.isUpperCase(letter)) {
          asciiNum = letter - 38;
        }
        string = stringBuilder.append(string.substring(0,i) + letter + asciiNum + string.substring(i+1)).toString();
      }
    }
    return string;
  }

}
