package ca.jrvs.apps.grep;

public interface RegexExc {

  /**
   * return true if filename extension is jpg or jpeg
   * @param filename
   * @return
   */
  public boolean matchJPeg(String filename);

  /**
   * return true if ip is valid (between 0.0.0.0 and 999.999.999.999)
   * @param ip
   * @return
   */
  public boolean matchIP(String ip);

  /**
   * return true if line is empty (white space, tabs)
   * @param line
   * @return
   */
  public boolean isEmptyLine(String line);
}
