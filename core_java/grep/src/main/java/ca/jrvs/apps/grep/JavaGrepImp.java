package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  /**
   * Top level search workflow
   *
   * @throws IOException
   */
  @Override
  public void process() throws IOException {

    Stream<File> allFiles = listFiles(rootPath);

    allFiles.forEach(file -> {
      try {
        Stream<String> matchedLines = readLines(file).filter(this::containsPattern);
        writeToFile(matchedLines);
      } catch (IOException e) {
        throw new RuntimeException("Failed to find matched lines or write to file", e);
      }
    });
  }

  /**
   * Traverse a given directory and return all files
   *
   * Source: https://howtodoinjava.com/java8/java-8-list-all-files-example/, https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
   *
   * @param rootDir
   * @return
   */
  @Override
  public Stream<File> listFiles(String rootDir) throws IOException {
    return Files.walk(Paths.get(rootDir))
        .filter(path -> !path.toString().endsWith(".jar"))
        .filter(path -> !path.toString().endsWith(".class"))
        .filter(path -> !path.toString().endsWith(outFile))
        .filter(Files::isRegularFile)
        .map(Path::toFile);
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
   * Source: https://stackoverflow.com/questions/6548157/how-to-write-an-arraylist-of-strings-into-a-text-file/6548204#6548204, https://newbedev.com/write-to-text-file-without-overwriting-in-java
   *
   * @param lines
   * @throws IOException
   */
  @Override
  public void writeToFile(Stream<String> lines) throws IOException {
    FileWriter writer = new FileWriter(outFile, true);
    lines.forEach(str -> {
      try {
        writer.write(str + System.lineSeparator());
      } catch (IOException e) {
        throw new RuntimeException("Failed to write to file", e);
      }
    });
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


