package ca.jrvs.apps.grep;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
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

  //List<File> files = new ArrayList<>();

  /**
   * Top level search workflow
   *
   * @throws IOException
   */
  @Override
  public void process() throws IOException {

    Stream<File> allFiles = listFiles(rootPath);

    final Stream<String> allMatchedLines;

    allFiles.forEach(file -> {
      try {
        Stream<String> matchedLines = readLines(file).filter(this::containsPattern);
        matchedLines.forEach(System.out::println);
        allMatchedLines = Stream.concat(allMatchedLines, matchedLines);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    writeToFile(allMatchedLines);




//    List<String> matchedLines = new ArrayList<>();
//    for (File file : listFiles(rootPath)) {
//      for (String line : readLines(file)) {
//        if (containsPattern(line)) {
//          matchedLines.add(line);
//        }
//      }
//    }
//    writeToFile(matchedLines);
  }

  /**
   * Traverse a given directory and return all files
   *
   * Source: https://stackoverflow.com/questions/4917326/how-to-iterate-over-the-files-of-a-certain-directory-in-java/4917347
   *
   * New Sources: https://stackoverflow.com/questions/55555030/is-there-a-way-to-convert-the-results-of-stream-into-array-and-iterate-through-a
   *
   * @param rootDir
   * @return
   */
  @Override
  public Stream<File> listFiles(String rootDir) {

    File dir = new File(rootDir);
    return Arrays.stream(Objects.requireNonNull(dir.listFiles())).filter(File::isFile);

//    files.clear();
//
//    File dir = new File(rootDir);
//    File[] directoryListing = dir.listFiles();
//    if (directoryListing != null) {
//      for (File child : directoryListing) {
//        if (child.isFile()) {
//          files.add(child);
//        }
//      }
//    }
//    return files;
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
  public Stream<String> readLines(File inputFile) throws IllegalArgumentException, IOException {

    return Files.lines(inputFile.toPath());

//    List<String> lines = new ArrayList<String>();
//    BufferedReader in = new BufferedReader(new FileReader(inputFile));
//    String str;
//    while ((str = in.readLine()) != null) {
//      lines.add(str);
//    }
//    return lines;
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
  public void writeToFile(Stream<String> lines) throws IOException {
    FileWriter writer = new FileWriter(outFile);
    lines.forEach(str -> {
      try {
        writer.write(System.lineSeparator() + str + System.lineSeparator());
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
//    for (String str : lines)
//      writer.write(str + System.lineSeparator());
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

    BasicConfigurator.configure();

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
