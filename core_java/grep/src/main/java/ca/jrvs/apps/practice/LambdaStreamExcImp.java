package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp implements LambdaStreamExc {

  /**
   * Create a String stream from array
   * <p>
   * note: arbitrary number of value will be stored in an array
   *
   * Source: https://www.baeldung.com/java-8-streams
   *
   * @param strings
   * @return
   */
  @Override
  public Stream<String> createStrStream(String... strings) {
    return Stream.of(strings);
  }

  /**
   * Convert all strings to uppercase please use createStrStream
   *
   * Source: https://github.com/vfarcic/java-8-exercises/blob/master/src/main/java/com/technologyconversations/java8exercises/streams/ToUpperCase.java
   *
   * @param strings
   * @return
   */
  @Override
  public Stream<String> toUpperCase(String... strings) {
    return createStrStream(strings).map(String::toUpperCase);
  }

  /**
   * filter strings that contains the pattern e.g. filter(stringStream, "a") will return another
   * stream which no element contains a
   *
   * Source: https://www.baeldung.com/java-8-functional-interfaces
   *
   * @param stringStream
   * @param pattern
   * @return
   */
  @Override
  public Stream<String> filter(Stream<String> stringStream, String pattern) {
    return stringStream.filter(i -> i.contains(pattern));
  }

  /**
   * Create a intStream from a arr[]
   *
   * @param arr
   * @return
   */
  @Override
  public IntStream createIntStream(int[] arr) {
    return IntStream.of(arr);
  }

  /**
   * Convert a stream to list
   *
   * Source: https://www.baeldung.com/java-stream-to-list-collecting
   *
   * @param stream
   * @return
   */
  @Override
  public <E> List<E> toList(Stream<E> stream) {
    return stream.collect(Collectors.toList());
  }

  /**
   * Convert a intStream to list
   *
   * Sources: https://www.baeldung.com/java-intstream-convert, https://stackoverflow.com/questions/14830313/retrieving-a-list-from-a-java-util-stream-stream-in-java-8
   *
   * @param intStream
   * @return
   */
  @Override
  public List<Integer> toList(IntStream intStream) {
    return intStream.boxed().collect(Collectors.toList());
  }

  /**
   * Create a IntStream range from start to end inclusive
   *
   * Source: https://howtodoinjava.com/java8/intstream-examples/
   *
   * @param start
   * @param end
   * @return
   */
  @Override
  public IntStream createIntStream(int start, int end) {
    return IntStream.range(start, end + 1);
  }

  /**
   * Convert a intStream to a doubleStream and compute square root of each element
   *
   * Source: https://www.codegrepper.com/code-examples/java/java+streams+add+1+to+all+elements
   *
   * @param intStream
   * @return
   */
  @Override
  public DoubleStream squareRootIntStream(IntStream intStream) {
    return intStream.asDoubleStream().map(Math::sqrt);
  }

  /**
   * filter all even number and return odd numbers from a intStream
   *
   * Source: https://techndeck.com/check-even-odd-numbers-within-a-range-in-java-8-streams/
   *
   * @param intStream
   * @return
   */
  @Override
  public IntStream getOdd(IntStream intStream) {
    return intStream.filter(i -> i % 2 != 0);
  }

  /**
   * Return a lambda function that print a message with a prefix and suffix This lambda can be
   * useful to format logs
   * <p>
   * You will learn: - functional interface http://bit.ly/2pTXRwM & http://bit.ly/33onFig - lambda
   * syntax
   * <p>
   * e.g. LambdaStreamExc lse = new LambdaStreamImp(); Consumer<String> printer =
   * lse.getLambdaPrinter("start>", "<end"); printer.accept("Message body");
   * <p>
   * sout: start>Message body<end
   *
   * @param prefix prefix str
   * @param suffix suffix str
   * @return
   */
  @Override
  public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
    return message -> System.out.println(prefix + message + suffix);
  }

  /**
   * Print each message with a given printer Please use `getLambdaPrinter` method
   * <p>
   * e.g. String[] messages = {"a","b", "c"}; lse.printMessages(messages,
   * lse.getLambdaPrinter("msg:", "!") );
   * <p>
   * sout: msg:a! msg:b! msg:c!
   *
   * @param messages
   * @param printer
   */
  @Override
  public void printMessages(String[] messages, Consumer<String> printer) {
    Stream<String> msgs = Stream.of(messages);
    msgs.forEach(printer);
  }

  /**
   * Print all odd number from a intStream. Please use `createIntStream` and `getLambdaPrinter`
   * methods
   * <p>
   * e.g. lse.printOdd(lse.createIntStream(0, 5), lse.getLambdaPrinter("odd number:", "!"));
   * <p>
   * sout: odd number:1! odd number:3! odd number:5!
   *
   * Sources: https://www.baeldung.com/java-collection-stream-foreach, https://www.baeldung.com/java-intstream-convert
   *
   * @param intStream
   * @param printer
   */
  @Override
  public void printOdd(IntStream intStream, Consumer<String> printer) {
    Stream<String> stream = getOdd(intStream).mapToObj(String::valueOf);
    stream.forEach(printer);
  }

  /**
   * Square each number from the input.
   * Please write two solutions and compare difference
   * - using flatMap
   *
   * Source: https://stackoverflow.com/questions/24633913/how-do-i-get-an-intstream-from-a-listinteger, https://howtodoinjava.com/java8/stream-flatmap-example/
   *
   * @param ints
   * @return
   */
  @Override
  public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
    List<Integer> allLists = ints.flatMap(Collection::stream).collect(Collectors.toList());
    return allLists.stream().map(i -> i * i);
  }

  public Stream<Integer> flatNestedInt2(Stream<List<Integer>> ints) {
    List<Integer> allLists = ints.flatMap(Collection::stream).collect(Collectors.toList());
    return allLists.stream().flatMapToInt(IntStream::of).map(i -> i * i).boxed();
  }

  public static void main(String[] args) {

    LambdaStreamExc lse = new LambdaStreamExcImp();

    // Test toUpperCase & createStrStream
    Stream<String> stream = lse.toUpperCase("lower","case","strings","with","some","Upper","Case");
    stream.forEach(System.out::println);

    // Test filter
    Stream<String> stream2 = lse.createStrStream("lower","case","strings","with","some","Upper","Case");
    stream2 = lse.filter(stream2, "ase");
    stream2.forEach(System.out::println);

    // Test createIntStream (1)
    int[] arrayOfInts = { 0, 2, 5, 8, 10 };
    IntStream intStream = lse.createIntStream(arrayOfInts);
    intStream.forEach(System.out::println);

    // Test createIntStream (2)
    IntStream intStream2 = lse.createIntStream(18, 28);
    intStream2.forEach(System.out::println);

    // Test toList (1)
    Stream<String> stream3 = lse.createStrStream("these","are","strings");
    List<String> stringList = lse.toList(stream3);
    stringList.forEach(System.out::println);

    // Test toList (2)
    IntStream intStream3 = lse.createIntStream(55, 58);
    List<Integer> intList = lse.toList(intStream3);
    intList.forEach(System.out::println);

    // Test squareRootIntStream
    IntStream intStream4 = lse.createIntStream(9, 16);
    DoubleStream dblStream = lse.squareRootIntStream(intStream4);
    dblStream.forEach(System.out::println);

    // Test getLambdaPrinter
    Consumer<String> printer = lse.getLambdaPrinter("prefix>", "<suffix");
    printer.accept("Message");

    // Test printMessages
    String[] messages = {"a","b","c"};
    lse.printMessages(messages, lse.getLambdaPrinter("msg:","!"));

    // Test printOdd & getOdd
    lse.printOdd(lse.createIntStream(1,10),lse.getLambdaPrinter("Odd Number: ", "!"));

    // Test flatNestedInt
    List<Integer> intList1 = Arrays.asList(1, 2, 3);
    List<Integer> intList2 = Arrays.asList(4, 5, 6);
    List<Integer> intList3 = Arrays.asList(7, 8, 9);
    Stream<List<Integer>> strListInt = Stream.of(intList1, intList2, intList3); // https://howtodoinjava.com/java8/java-streams-by-examples/
    Stream<Integer> finalIntStream = lse.flatNestedInt(strListInt);
    finalIntStream.forEach(System.out::println);

    // Test flatNestedInt2
    List<Integer> intList4 = Arrays.asList(10, 11, 12);
    List<Integer> intList5 = Arrays.asList(13, 14, 15);
    List<Integer> intList6 = Arrays.asList(16, 17, 18);
    Stream<List<Integer>> strListInt2 = Stream.of(intList4, intList5, intList6); // https://howtodoinjava.com/java8/java-streams-by-examples/
    Stream<Integer> finalIntStream2 = lse.flatNestedInt2(strListInt2);
    finalIntStream2.forEach(System.out::println);
  }
}
