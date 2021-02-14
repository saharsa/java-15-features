package com.java.methodsEncapsulation;

import static java.lang.System.out;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/*
Regular imports are for importing classes
Static imports are for importing static members of classes
importing two classes with the same name gives a compiler error.
This is true of static imports as well.
 */

public class Test {

  static int k = 1;
  int l = 2;

  // constant
  private static final int CONSTANT_VAL;

  /*
   static initialization
   Runs when the class is first loaded
   */
  static {
    CONSTANT_VAL = 10;
  }

  /*
  The private modifier means the method can be called only from within the same class.
   */
  private void a() {
    System.out.println("a");
  }

  /*
  With default access, the method can be called only from classes in the same package.
  This one is tricky because there is no keyword for default access.
  You simply omit the access modifier.

  default keyword from switch is different
   */
  void b() {
    System.out.println("b");
  }

  /*
  The protected modifier means the method can be called only from classes in the same package or subclasses.
   */
  protected void c() {
    System.out.println("c");
  }

  /*
  The public modifier means the method can be called from any class
   */
  public void d() {
    System.out.println("d");
  }

  /*

  access-specifier optional-specifier return-type function-name(parameters) exception-list

  static The static modifier is used for class methods
  abstract The abstract modifier is used when a method body is not provided
  final The final modifier is used when a method is not allowed to be overridden by a subclass
  synchronized The synchronized modifier is used with multithreaded code
  native The native modifier is used when interacting with code written in another language such as C++
  strictfp The strictfp modifier is used for making floating-point calculations portable
   */

  //varargs
  public void e(int... nums){

  }

  /*
  static method cannot call an instance method
  doesn’t compile because a static variable is trying to use an instance variable

   */

  /*
  Just remember that only data gets its “own copy.” There is no need to duplicate copies of the code itself.
   */

  public static void main(String[] args) {

    Test test = new Test();
    out.println(test.k);
    test = null;
    // Even if test is pointing to no object, since invoking static variable it will work
    out.println(test.k);

    print("abc"); // C
    print(new ArrayList()); // I
    print(LocalDate.of(2019, Month.JULY, 4)); // O

  }

  public static void test(){
    // System.out.println(l); non static field cannot be referenced from a static context
  }

  /*
  Method overloading occurs when methods have the same name but different method signatures, which means they differ by method parameters
   */
  public void sum(int a){

  }

  public void sum(float b){

  }

  public void fly(int[] lengths) {}
  // public void fly(int... lengths) {}     // DOES NOT COMPILE Java treats varargs as if they were an array

  public static void print(Iterable i) {
    System.out.print("I");
  }
  public static void print(CharSequence c) {
    System.out.print("C");
  }
  public static void print(Object o) {
    System.out.print("O");
  }

  /*
  Java has a concept called type erasure where generics are used only at compile time.
  That means the compiled code looks like this:
  public void walk(List strings) {}
  public void walk(List integers) {}    // DOES NOT COMPILE
   */
  public void walk(List<String> strings) {}
  // public void walk(List<Integer> integers) {}    // DOES NOT COMPILE

  public static void walk(int[] ints) {}
  public static void walk(Integer[] integers) {}

  /*
  Encapsulation refers to preventing callers from changing the instance variables directly.
  This is done by making instance variables private and getters/setters public.
   */

}
