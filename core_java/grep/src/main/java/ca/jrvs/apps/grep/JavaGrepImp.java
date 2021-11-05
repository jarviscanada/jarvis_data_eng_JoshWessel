package ca.jrvs.apps.grep;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  List<File> files = new ArrayList<File>();

  /**
   * Top level search workflow
   *
   * @throws IOException
   */
  @Override
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<String>();
    for (File file : listFiles(rootPath)) {
      for (String line : readLines(file)) {
        if (containsPattern(line)) {
          matchedLines.add(line);
        }
      }
    }
    writeToFile(matchedLines);
  }

  /**
   * Traverse a given directory and return all files
   *
   * @param rootDir
   * @return
   */
  @Override
  public List<File> listFiles(String rootDir) throws IOException {
    listFilesRecursively(rootDir);
    return files;
  }

  /**
   * Source: https://stackoverflow.com/a/24324367
   * @param rootDir
   * @throws IOException
   */
  private void listFilesRecursively(String rootDir) throws IOException {
    DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(rootDir));
    for (Path path : stream) {
      if (path.toFile().isDirectory()) {
        listFilesRecursively(path.toString());
      }
      else {
        files.add(path.toFile());
      }
    }
  }

  /**
   * Read a file and return all lines
   * <p>
   * Explain FileReader, BufferedReader, and character encoding
   *
   * Source: https://stackoverflow.com/a/16100219
   *
   * @param inputFile
   * @return
   */
  @Override
  public List<String> readLines(File inputFile) throws IOException {
    List<String> lines = new ArrayList<String>();
    BufferedReader in = new BufferedReader(new FileReader(inputFile));
    String str;
    while ((str = in.readLine()) != null) {
      lines.add(str);
    }
    return lines;
  }

  /**
   * Check if a line contains the regex pattern (passed by user)
   *
   * Source: Sources: https://www.w3schools.com/java/java_regex.asp, https://www.javatpoint.com/java-regex
   *
   * @param line
   * @return
   */
  @Override
  public boolean containsPattern(String line) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(line);
    return matcher.find();
  }

  /**
   * Write lines to a file
   * <p>
   * Explore: FileOutPutStream, OutputStreamWriter, and BufferedWriter
   *
   * Source: https://stackoverflow.com/questions/6548157/how-to-write-an-arraylist-of-strings-into-a-text-file/6548204#6548204
   *
   * @param lines
   * @throws IOException
   */
  @Override
  public void writeToFile(List<String> lines) throws IOException {
    FileWriter writer = new FileWriter(outFile);
    for (String str : lines)
      writer.write(str + System.lineSeparator());
    writer.close();
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("Usage: JavaGrep [regex] [rootPath] [outFile]");
    }

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      javaGrepImp.logger.error("Error: Unable to process", ex);
    }
  }
}
