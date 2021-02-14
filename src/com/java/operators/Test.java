package com.java.operators;

public class Test {

  public static void main(String[] args) {

    /*
    post-unary ex++, ex--
    pre-unary ++ex, --ex
    other unary - ! ~ + (type)
    multi/div/mod * / %
    add/sub + -
    shift << >> >>>
    relational < > <= >= instanceof
    equal/not == !=
    logical & ^ |
    short circuit && ||
    ternary ? :
    assignment = += -= *= /= %= &= ^= |= <<= >>= >>>=

    for same precendence - left to right
     */

    // int i = !0; does not compile
    // boolean b = -true does not compile

    int i = 2;
    int j = ++i * 5 / 3;
    int k = 3 * 5 / 3;

    System.out.println(j);
    System.out.println(k);

    /*
    Numeric Promotion Rules
    If two values have different data types, Java will automatically promote one of the values to the larger of the two data types.
    If one of the values is integral and the other is floating-point, Java will automatically promote the integral value to the floating-point value’s data type.
    Smaller data types, namely, byte, short, and char, are first promoted to int any time they’re used with a Java binary arithmetic operator, even if neither of the operands is int.
    After all promotion has occurred and the operands have the same data type, the resulting value will have the same data type as its promoted operands.
     */

    /*
    Casting is a unary operation where one data type is explicitly interpreted as another data type. Casting is optional and unnecessary when converting to a larger or widening data type, but it is required when converting to a smaller or narrowing data type
     */

    /*
    Overflow is when a number is so large that it will no longer fit within the data type, so the system “wraps around” to the lowest negative value and counts up from there, similar to how modulus arithmetic works. There’s also an analogous underflow, when the number is too low to fit in the data type, such as storing -200 in a byte field.
     */

    System.out.print(2147483647+1);  // -2147483648

    System.out.print(null == null);  // true

    System.out.print(null instanceof Object); // false

    Object noObjectHere = null;
    System.out.print(noObjectHere instanceof String); // false

    boolean eyesClosed = true;
    boolean breathingSlowly = true;
    boolean resting = eyesClosed | breathingSlowly;
    boolean asleep = eyesClosed & breathingSlowly;
    boolean awake = eyesClosed ^ breathingSlowly;
    System.out.println(resting);  // true
    System.out.println(asleep);   // true
    System.out.println(awake);    // false

    // Short circuit operator. As first condition true, second never evaluated
    // un-performed side-effect
    int rabbit = 6;
    boolean bunny = (rabbit >= 6) || (++rabbit <= 7);
    System.out.println(rabbit); // 6

    int sheep = 1;
    int zzz = 1;
    int sleep = zzz<10 ? sheep++ : zzz++;
    System.out.print(sheep+","+zzz);  // 2,1

  }

}
