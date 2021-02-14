package com.java.primitives;

public class Test {

  /*
  Local variables: In scope from declaration to end of block
  Instance variables: In scope from declaration until object eligible for garbage collection
  Class variables: In scope from declaration until program ends
   */

  /*
  Do not require initialization. As soon as they are declared, given default value.
  boolean -> false
  byte,short,int,long -> 0
  float,double -> 0.0
  char -> '\u0000' (NUL)
  All object references -> null
   */
  // instance variable
  int z = 1;
  // class variable
  static int y = 1;

  // var u = "Hello"; Does not compile

  public static void main(String[] args) {
    // Primitives are just a single value in memory. Not an object nor represents an object.

    boolean a = true;
    byte b = 123; // 8-bit integral value; -128 to 127
    short c = 123; // 16-bit integral value; signed;
    int d = 123; // 32-bit integral value
    long e = 123L; // 64-bit integral value
    float f = 12.3f; // 32-bit floating point value
    double g = 12.3; // 64-bit floating point value
    char h = '1'; // 16-bit unicode value; unsigned; closely related to short;

    int i = 017; // Octal (0-7 digits)
    int j = 0XF; // Hexadecimal 0-9,A-F
    int k = 0b10; // Binary

    int l = 1_000_000; // Numeric literals
    /*
    double notAtStart = _1000.00;          // DOES NOT COMPILE
    double notAtEnd = 1000.00_;            // DOES NOT COMPILE
    double notByDecimal = 1000_.00;        // DOES NOT COMPILE
    double annoyingButLegal = 1_00_0.0_0;  // Ugly, but compiles
    double reallyUgly = 1__________2;      // Also compiles
     */

    // Primitives can't be assigned null
    // Do not have any methods

    /*
    Identifiers must begin with a letter, a $ symbol, or a _ symbol.
    Identifiers can include numbers but not start with them.
    Since Java 9, a single underscore _ is not allowed as an identifier.
    Can't use reserved word.
     */

    /*
    true/false/null are literal values, not reserved words
     */

    /*
    camelCase
    PascalCase
    snake_case
     */

    /*
     local variable type inference
     instructing compiler to determine type for you

     var cannot be used with constructors, method parameters, or instance variables.
     Remember that var is only used for local variable type inference.

     var not reserved word, allowed to be used as an identifier.
     A reserved type name, means cannot be used to define a type such as class interface or enum

     A var is used as a local variable in a constructor, method, or initializer block.
     A var is always initialized on the same line (or statement) where it is declared.
     The value of a var can change, but the type cannot.
     A var cannot be initialized with a null value without a type.
     A var is not permitted in a multiple-variable declaration.
     A var is a reserved type name but not a reserved word, meaning it can be used as an identifier except as a class, interface, or enum name.

     */
    var w = "Hello";
    // w = 1; does not compile
    // var x; does not compile
    // var a = 2, b = 3;  does not compile
  }

  // method parameter 'a'
  public void doNothing(int a) {

  }

}
