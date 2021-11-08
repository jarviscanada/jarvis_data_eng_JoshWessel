package ca.jrvs.apps.grep;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface JavaGrep {

  /**
   * Top level search workflow
   * @throws IOException
   */
  void process() throws IOException;

  /**
   * Traverse a given directory and return all files
   * @param rootDir
   * @return
   */
  Stream<File> listFiles(String rootDir);

  /**
   * Read a file and return all lines
   *
   * Explain FileReader, BufferedReader, and character encoding
   *
   * @param inputFile
   * @return
   */
  Stream<String> readLines(File inputFile) throws IOException;

  /**
   * Check if a line contains the regex pattern (passed by user)
   * @param line
   * @return
   */
  boolean containsPattern(String line);

  /**
   * Write lines to a file
   *
   * Explore: FileOutPutStream, OutputStreamWriter, and BufferedWriter
   *
   * @param lines
   * @throws IOException
   */
  void writeToFile(Stream<String> lines) throws IOException;

  String getRootPath();

  void setRootPath(String rootPath);

  String getRegex();

  void setRegex(String regex);

  String getOutFile();

  void setOutFile(String outFile);
}