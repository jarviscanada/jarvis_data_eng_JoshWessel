package ca.jrvs.apps.grep;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc {

  /**
   * return true if filename extension is jpg or jpeg
   *
   * Sources: https://www.w3schools.com/java/java_regex.asp, https://www.javatpoint.com/java-regex
   *
   * @param filename
   * @return
   */
  @Override
  public boolean matchJPeg(String filename) {
    Pattern pattern = Pattern.compile("(jpg$|jpeg$)", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(filename);
    return matcher.find();
  }

  /**
   * return true if ip is valid (between 0.0.0.0 and 999.999.999.999)
   *
   * Sources: https://www.w3schools.com/java/java_regex.asp, https://www.javatpoint.com/java-regex
   *
   * @param ip
   * @return
   */
  @Override
  public boolean matchIP(String ip) {
    Pattern pattern = Pattern.compile("^(\\d{1,3}\\.){3}\\d{1,3}$");
    Matcher matcher = pattern.matcher(ip);
    return matcher.find();
  }

  /**
   * return true if line is empty (white space, tabs)
   *
   * Source: https://www.w3schools.com/java/java_regex.asp, https://www.javatpoint.com/java-regex
   *
   * @param line
   * @return
   */
  @Override
  public boolean isEmptyLine(String line) {
    Pattern pattern = Pattern.compile("\\s");
    Matcher matcher = pattern.matcher(line);
    return matcher.find();
  }

  /**
   * Source: https://medium.com/factory-mind/regex-tutorial-a-simple-cheatsheet-by-examples-649dc1c3f285
   * @param args
   */
  public static void main(String[] args) {
    String validJPG = "image.jpeg";
    String validIP = "0.1.2.3";
    String emptyLine = "  ";
    String invalidJPG = "image.jepeg";
    String invalidIP = "0.12345.2.3.4.5";
    String notEmptyLine = "abc";

    RegexExcImp reg = new RegexExcImp();

    boolean isValidJPG = reg.matchJPeg(validJPG);
    boolean isValidIP = reg.matchIP(validIP);
    boolean isValidLine = reg.isEmptyLine(emptyLine);
    boolean isinValidJPG = reg.matchJPeg(invalidJPG);
    boolean isinValidIP = reg.matchIP(invalidIP);
    boolean isinValidLine = reg.isEmptyLine(notEmptyLine);

    System.out.println(isValidJPG);
    System.out.println(isValidIP);
    System.out.println(isValidLine);
    System.out.println(isinValidJPG);
    System.out.println(isinValidIP);
    System.out.println(isinValidLine);
  }
}