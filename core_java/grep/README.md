# Introduction

The grep app searches all files within a given directory for all lines that match a given pattern, writing all matches to a given output location.
The application requires the user to supply three parameters. These parameters include a pattern to search for, the search directory, and the output file to store all matched lines.
The application was written in Java, using IntelliJ as the IDE.
The testing process involved a series of trial and error tests.
IntelliJ's debugger was especially useful in identifying issues related to properly searching the given directory.
The application was cleaned and packaged using Maven and deployed using Docker.
A Docker image was also built and pushed to Docker Hub.

# Quick Start

Use the following command on the command line to execute the application:

```bash
java -cp [jar file] [Regex pattern] [Directory to search] [Output file]
```

Alternatively, the following command executes the application using Docker:

```bash
docker run --rm -v [Volume name]:[Path where file/directory is mounted in the container] [Image] [Regex pattern] [Directory to search] [Output file]
```

# Implementation

## Pseudocode

The application uses a `JavaGrepImp` class which implements a `JavaGrep` interface.
The `JavaGrepImp` class implements all methods declared in the interface.
Pseudocode for key methods can be found below.

**process**
```java
// This method groups all methods required for the application into a single method. It is the only method called in main.
// Params: None
// 1. Create a stream containing all files
// 2. For each file in the stream:
//    - Create a stream containing all lines in the file, filtered to match the given pattern
//    - Write the matched lines to the specified output file
// Returns void
```

**listFiles**
```java
// This method traverses the given directory and returns a stream containing all files
// Params: String rootDir
// 1. Return a stream of files from the given directory, while filtering out all files that meet the following criteria:
//    - All files ending with ".jar"
//    - All files ending with ".class"
//    - The specified output file
// Returns Stream<File>
```

**readLines**
```java
// This method reads the given file and returns a stream containing all lines within the file
// Params: File inputFile
// 1. Return a stream of Strings from the given input file
// Returns Stream<String>
```

**containsPattern**
```java
// This method reads the given line and returns a boolean that represents whether or not the given line contains the given pattern
// Params: String line
// 1. Create a Pattern object based on the given regex pattern
// 2. Create a Matcher object based on the given line
// 3. Return a boolean representing whether or not the given line contains the given pattern
// Returns boolean
```

**writeToFile**
```java
// This method writes all lines from the given Stream of Strings and writes each line to the given output file
// Params: Stream<String> lines
// 1. Create a FileWriter object to append the given output file
// 2. For each String in the Stream, write the String in the given output file, followed by a new line
// 3. Close the FileWriter object
// Returns void
```

**main**
```java
// The main method is used to accept parameters from the command line and run the application
// Params: String[] args
// 1. Ensure there are exactly 3 arguments
// 2. Create a JavaGrepImp object
// 3. Set JavaGrepImp object member variables to given parameters
// 4. Call JavaGrepImp objects process() method (see process method above)
// Returns void
```

## Performance Issue

Initially, the application used Java `List` objects to store the files and the lines within the files.
However, since performing operations on `List` objects is less memory efficient than some alternatives, the application was reimplemented using streams API, thus solving the performance issue.

# Test

Testing the application involved many trial and error tests.
Each test ensured certain code blocks were working as intended.
For example, at one point during development, the listFiles method was not accessing subdirectories.
IntelliJ's debugger proved instrumental in identifying the issue.
Another related issue involved the application crashing when it searched certain types of files (.jar and .class).
Again, the IDE's debugger identified the issue. Ultimately, researching and implementing alternative solutions resolved all bugs.

# Deployment

The application was packaged using Maven and deployed using Docker.
Using Maven, the application was cleaned, compiled, and packaged.
The deployment of the application through Docker involved the creation of a Docker image.
Then the container was run using the newly created image.
Docker Hub contains the image.

# Improvement

Below are a few improvements to consider.

1. Expand the number of file types included in the search (.jar, .class).
2. Output additional information to the output file, such as file name and line number of matched lines
3. Add additional parameters to allow the user to specify subdirectories or files to ignore in the search.