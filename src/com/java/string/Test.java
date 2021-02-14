package com.java.string;

import java.util.Locale;

public class Test {

  public static void main(String[] args) {

    /*
    String is a special class and doesn't need to be instantiated with new
    Is a sequence of characters and implements interface CharSequence
    Once a String object is created, it is not allowed to change.
    It cannot be made larger or smaller, and you cannot change one of the characters inside it.
    Also, immutable classes in Java are final, which prevents subclasses creation.
    You wouldn’t want a subclass adding mutable behavior.

     */

    String a = "world";

    int three = 3;
    String four = "4";
    System.out.println(1 + 2 + three + four); // 64

    System.out.println(a.length()); // 5
    System.out.println(a.charAt(0)); // w
    System.out.println(a.indexOf('o')); // 1
    System.out.println(a.indexOf('0', 2)); // fromIndex; -1
    System.out.println(a.lastIndexOf("ld", 0)); // -1
    System.out.println(a.substring(0, 1)); // w
    System.out.println(a.substring(1)); // orld
    System.out.println(a.toLowerCase(Locale.CHINESE)); // world
    System.out.println(a.toUpperCase(Locale.FRANCE)); // WORLD
    System.out.println("abc".equals("ABC"));  // false
    System.out.println("ABC".equals("ABC"));  // true
    System.out.println("abc".equalsIgnoreCase("ABC"));  // true
    System.out.println("abc".startsWith("a")); // true
    System.out.println("abc".startsWith("A")); // false
    System.out.println("abc".endsWith("c")); // true
    System.out.println("abc".endsWith("a")); // false
    System.out.println("abcabc".replace('a', 'A')); // AbcAbc
    System.out.println("abcabc".replace("a", "A")); // AbcAbc
    System.out.println("abc".contains("b")); // true
    System.out.println("abc".contains("B")); // false
    System.out.println("abc".strip());                 // abc
    System.out.println("\t   a b c\n".strip());        // a b c

    String text = " abc\t ";
    System.out.println(text.trim().length());         // 3
    System.out.println(text.strip().length());        // 3
    System.out.println(text.stripLeading().length()); // 5
    System.out.println(text.stripTrailing().length());// 4
    // strip does all that trim does, but also supports Unicode

    // returns value from string pool if present; else adds value to string pool
    text.intern();

    // method chaining
    String result = "AniMaL   ".trim().toLowerCase().replace('a', 'A');
    System.out.println(result);

    StringBuilder alpha = new StringBuilder();
    for(char current = 'a'; current <= 'z'; current++)
      alpha.append(current);
    System.out.println(alpha);
    alpha.insert(1, "--");
    System.out.println(alpha);
    alpha.delete(1,2);
    System.out.println(alpha);
    alpha.deleteCharAt(1);
    System.out.println(alpha);
    alpha.replace(2,3, "vvvv");
    System.out.println(alpha);
    alpha.reverse();
    System.out.println(alpha);
    // StringBuffer supports threads. Performs slower than StringBuilder

    /*
    The string pool, also known as the intern pool, is a location in the Java virtual machine (JVM) that collects all these strings.
    The string pool contains literal values and constants that appear in your program.
    For example, "name" is a literal and therefore goes into the string pool.
    myObject.toString() is a string but not a literal, so it does not go into the string pool.
     */
    String first = "rat" + 1;
    String second = "r" + "a" + "t" + "1";
    String third = "r" + "a" + "t" + new String("1");
    System.out.println(first == second); // true as both are compile-time constants
    System.out.println(first == second.intern()); // true
    System.out.println(first == third); // false
    System.out.println(first == third.intern()); // true

    /*
    Text blocks
     */
    String email = """
        Hello, 
        there
        """;



  }

}
