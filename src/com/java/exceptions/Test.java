package com.java.exceptions;

/*
A method can handle the exception case itself or make it the caller’s responsibility.

java.lang.Object <-- java.lang.Throwable <-- java.lang.Exception <-- java.lang.RuntimeException
java.lang.Throwable <-- java.lang.Error

A checked exception is an exception that must be declared or handled by the application code where it is thrown.
In Java, checked exceptions all inherit Exception but not RuntimeException.
Checked exceptions tend to be more anticipated—for example, trying to read a file that doesn’t exist.

Checked exceptions also include any class that inherits Throwable, but not Error or RuntimeException.
For example, a class that directly extends Throwable would be a checked exception.

Handle or declare rule means that all checked exceptions that could be thrown within a method are either wrapped in compatible try and catch blocks or declared in the method signature.

The distinction is that checked exceptions must be handled or declared, while unchecked exceptions can be optionally handled or declared.

An unchecked exception is any exception that does not need to be declared or handled by the application code where it is thrown.
Unchecked exceptions are often referred to as runtime exceptions, although in Java, unchecked exceptions include any class that inherits RuntimeException or Error

A runtime exception is defined as the RuntimeException class and its subclasses. Runtime exceptions tend to be unexpected but not necessarily fatal. For example, accessing an invalid array index is unexpected.
Even though they do inherit the Exception class, they are not checked exceptions.

An unchecked exception can often occur on nearly any line of code, as it is not required to be handled or declared. For example, a NullPointerException can be thrown in the body of the following method if the input reference is null

RuntimeException:  ArithmeticException ArrayIndexOutOfBoundsException ClassCastException NullPointerException IllegalArgumentException NumberFormatException

Checked Exceptions:  IOException FileNotFoundException

Errors are unchecked exceptions that extend the Error class. They are thrown by the JVM and should not be handled or declared.
ExceptionInInitializerError StackOverflowError NoClassDefFoundError
Java runs static initializers the first time a class is used. If one of the static initializers throws an exception, Java can’t start using the class. It declares defeat by throwing an ExceptionInInitializerError.
A NoClassDefFoundError occurs when Java can’t find the class at runtime. Generally, this means a library available when the code was compiled is not available when the code is executed.

ClassNotFoundException is an exception that occurs when you try to load a class at run time using Class.forName() or loadClass() methods and mentioned classes are not found in the classpath.
NoClassDefFoundError is an error that occurs when a particular class is present at compile time, but was missing at run time.

 */

import java.io.FileInputStream;
import java.io.IOException;

public class Test {

  static void fall(int distance) throws IOException {
    if(distance > 10) {
      throw new IOException();
      // throw new ArrayIndexOutOfBoundsException();  // DOES NOT COMPILE unreachable code
    }

    /*
    try-with-resources

    Behind the scenes, the compiler replaces a try-with-resources block with a try and finally block. We refer to this “hidden” finally block as an implicit finally block since it is created and used by the compiler automatically
    When there are multiple resources opened, they are closed in the reverse order from which they were created
    You can’t just put any random class in a try-with-resources statement. Java requires classes used in a try-with-resources implement the AutoCloseable interface, which includes a void close() method

    The resources created in the try clause are in scope only within the try block.

    Implicit finally is executed before the programmer-defined finally.

    The try-with-resources statement guarantees only the close() method will be called. If the close() method encounters an exception of its own or the method is implemented poorly, a resource leak can still occur.

     */
    try (FileInputStream is = new FileInputStream("myfile.txt")) {
      // Read file data
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      // is.close(); does not compile as out of scope
    }
  }

  public static void main(String[] args) {

    /*
    at most one catch block will run, and it will be the first catch block that can handle it
    an exception defined by the catch statement is only in scope for that catch block

    The one difference between multi-catch blocks and chaining catch blocks is that order does not matter for a multi-catch block within a single catch expression.

     */
    try {
      fall(11);
    } catch (IOException | ClassCastException e) {
      e.printStackTrace();
    } finally {
      System.out.println("Do nothing");
      /*
      try statement also lets you run code at the end with a finally clause regardless of whether an exception is thrown
      The catch block is not required if finally is present.
      A finally block is typically used to close resources such as files or databases
      If a try statement with a finally block is entered, then the finally block will always be executed, regardless of whether the code completes successfully.
      while a finally block will always be executed, it may not finish
      When System.exit() is called in the try or catch block, the finally block does not run.
       */
    }
    // FileNotFoundException | IOException p // does not compile as file not found is subclass of io

    String type = "moose";
    Object obj = type;
    Integer number = (Integer) obj; // ClassCastException
  }

}
