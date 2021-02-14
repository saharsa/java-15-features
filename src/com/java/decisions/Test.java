package com.java.decisions;

public class Test {

  public enum Day { SUNDAY, MONDAY, TUESDAY,
    WEDNESDAY, THURSDAY, FRIDAY, SATURDAY; }

  public static void main(String[] args) {

    /*
    switch statement - enums, String, char, int, byte, short and their respective wrappers
    var also if it resolves to one of the previous types
    boolean, long, float and double excluded

    values in each case statement must be compile-time constant values of same data type as switch value
    you can use only literals, enum constants, or final constant variables of the same data type
    Even final values in method parameter won't work as that value is passed.
     */

    /*
    Java 12 onwards, Switch Expressions
    combines switch statement with lambda expression and allows switch to return a value
     */

    /*
    Current JVM has two kinds of switch byte codes: LookupSwitch and TableSwitch.
    Each case in a switch statement has an integer offset, if these offsets are contiguous (or mostly contiguous with no large gaps) (case 0: case 1: case 2, etc.), then TableSwitch is used.
    If the offsets are spread out with large gaps (case 0: case 400: case 93748:, etc.), then LookupSwitch is used.
    The difference, in short, is that TableSwitch is done in constant time because each value within the range of possible values is given a specific byte-code offset. Thus, when you give the statement an offset of 3, it knows to jump ahead 3 to find the correct branch.
    Lookup switch uses a binary search to find the correct code branch. This runs in O(log n) time, which is still good, but not the best.

    JVM will run JIT optimizations on if statements that will try to place the hottest branch first in the code. This is called "Branch Prediction"
     */

    Day day = Day.WEDNESDAY;
    System.out.println(
        switch (day) {
          case MONDAY, FRIDAY, SUNDAY -> 6;
          case TUESDAY                -> 7;
          case THURSDAY, SATURDAY     -> 8;
          case WEDNESDAY              -> 9;
          default -> throw new IllegalStateException("Invalid day: " + day);
        }
    );

    int numLetters = switch (day) {
      case MONDAY, FRIDAY, SUNDAY -> {
        System.out.println(6);
        yield 6;
      }
      case TUESDAY -> {
        System.out.println(7);
        yield 7;
      }
      case THURSDAY, SATURDAY -> {
        System.out.println(8);
        yield 8;
      }
      case WEDNESDAY -> {
        System.out.println(9);
        yield 9;
      }
      default -> {
        throw new IllegalStateException("Invalid day: " + day);
      }
    };

    /*
    for-each loop works for
    i) A built-in java array
    ii) an object whose type implements java.lang.Iterable

    A compile time enhancement. Actually converts into a standard for loop during compilation
     */

    String[] a = {"a", "b"};
    for(String i : a){
      System.out.println(i);
    }

    // continue cannot be used with switch

  }

}
