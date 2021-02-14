package com.java.instanceInitializer;

public class Test {

  /*
    Does not compile as order matters for fields and blocks of code.
    {System.out.println(name);}
   */

  // fist initialized
  private String name = "Fluffy";

  // next instance initializer called
  {
    System.out.println(name);
    System.out.println("setting field");
  }

  public Test() {
    // returns back to constructor
    name = "Tiny";
    System.out.println(name);
    System.out.println("setting constructor");
  }

  public static void main(String[] args) {
    Test test = new Test();
    System.out.println(test.name);
  }

  // Deprecated as of Java 9. Can run 0 or 1 time only. Called during garbage collection of object.
  @Override
  protected void finalize() throws Throwable {
    super.finalize();
  }
}
