package com.java.enums;

/*
An enumeration is like a fixed set of constants.
In Java, an enum, short for “enumerated type,” can be a top‐level type like a class or interface, as well as a nested type like an inner class.

Using an enum is much better than using a bunch of constants because it provides type‐safe checking.
With numeric or String constants, you can pass an invalid value and not find out until runtime. With enums, it is impossible to create an invalid enum value without introducing a compiler error.

Behind the scenes, an enum is a type of class that mainly contains static members

 */

/*
Cannot extend an enum
 */

enum Season {
  WINTER("Low"){
    public String getHours() {
      return "10am-3pm";
    }
    public String getColor() {
      return "Blue";
    }
  }, SPRING("Medium") {
    public String getHours() {
      return "9am-5pm";
    }
  }, SUMMER("High") {
    public String getHours() {
      return "9am-7pm";
    }
  }, FALL("Medium") {
    public String getHours() {
      return "9am-5pm";
    }
  };

  /*
  The immutable objects pattern is an object‐oriented design pattern in which an object cannot be modified after it is created.
  Instead of modifying an immutable object, you create a new object that contains any properties from the original object you want copied over.
   */
  //Since enum values are shared by all processes in the JVM, it would be problematic if one of them could change the value inside an enum. So final.
  private final String expectedVisitors;

  /*
   All enum constructors are implicitly private.
   This is reasonable since you can't extend an enum and the constructors can be called only within the enum itself.
   An enum constructor will not compile if it contains a public or protected modifier.
   */
  private Season(String expectedVisitors) {
    System.out.println("constructing"); //printed only once per enum value
    this.expectedVisitors = expectedVisitors;
  }
  public void printExpectedVisitors() {
    System.out.println(expectedVisitors);
  }

  //we created an abstract class and a bunch of tiny subclasses
  //This means that each and every enum value is required to implement this method.
  public abstract String getHours();

  public String getColor() {
    return "yellow";
  }
}

public class Test {

  public static void main(String[] args) {

    Season s = Season.SUMMER;
    System.out.println(Season.SUMMER); // SUMMER
    // can be compared using == because they are like static final constants
    System.out.println(s == Season.SUMMER); // true

    for(Season season: Season.values()) {
      System.out.println(season.name() + " " + season.ordinal());
    }

    Season t = Season.valueOf("SUMMER");

    switch (t) {
      case WINTER:
        System.out.println("Get out the sled!");
        break;
      case SUMMER:
        System.out.println("Time for the pool!"); // this
        break;
      default:
        System.out.println("Is it summer yet?");
    }

    System.out.println(
        switch (t) {
          case WINTER -> "Get out the sled";
          case SUMMER -> "Time for the pool";
          default -> "Is it summer yet";
        }
    );

    t.printExpectedVisitors();

  }

}
