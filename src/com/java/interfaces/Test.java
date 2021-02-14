package com.java.interfaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Test {

}

interface IsWarmBlooded {

  boolean hasScales();

  /*
  A default method is a method defined in an interface with the default keyword and includes a method body.
  A default method may be overridden by a class implementing the interface.

  One motivation for adding default methods to the Java language was for backward compatibility. A default method allows you to add a new method to an existing interface, without the need to modify older code that implements the interface.

  A default method may be declared only within an interface.
  A default method must be marked with the default keyword and include a method body.
  A default method is assumed to be public.
  A default method cannot be marked abstract, final, or static.
  A default method may be overridden by a class that implements the interface.
  If a class inherits two or more default methods with the same method signature, then the class must override the method.

   */
  default double getTemperature() {
    return 10.0;
  }

  /*
  A static method must be marked with the static keyword and include a method body.
  A static method without an access modifier is assumed to be public.
  A static method cannot be marked abstract or final.
  A static method is not inherited and cannot be accessed in a class implementing the interface without a reference to the interface name.
   */
  static int getJumpHeight() {
    return 8;
  }

  /*
  A private interface method must be marked with the private modifier and include a method body.
  A private interface method may be called only by default and private (non‐ static) methods within the interface definition.
   */
  private void doNothing () {

  }

  /*
  A private static method must be marked with the private and static modifiers and include a method body.
  A private static interface method may be called only by other methods within the interface definition.
   */
  private static void againDoNothing() {

  }

}

interface IsColdBlooded {
  boolean hasFins();
  default double getTemperature() {
    return 1.0;
  }
}

class Cat implements IsColdBlooded, IsWarmBlooded {

  @Override
  public boolean hasScales() {
    return false;
  }

  @Override
  public boolean hasFins() {
    return false;
  }

  /*
  If a class implements two interfaces that have default methods with the same method signature, the compiler will report an error.
  This rule holds true even for abstract classes because the duplicate method could be called within a concrete method within the abstract class.
  Must override
   */
  @Override
  public double getTemperature() {
    IsColdBlooded.super.getTemperature();
    IsWarmBlooded.super.getTemperature();
    return 0;
  }
}

/*
By introducing six different interface member types, Java has certainly blurred the lines between an abstract class and an interface.
A key distinction, though, is that interfaces do not implement constructors and are not part of the class hierarchy.
While a class can implement multiple interfaces, it can only directly extend a single class.
 */

/*
A functional interface is an interface that contains a single abstract method
A lambda expression is a block of code that gets passed around, sort of like an anonymous class that defines one method
If a functional interface includes an abstract method with the same signature as a public method found in Object, then those methods do not count toward the single abstract method test.
 */
@FunctionalInterface
interface Sprint {
  public void sprint(int speed);
}

/*
Lambda expressions rely on the notion of deferred execution. Deferred execution means that code is specified now but runs later.
In this case, later is when the print() method calls it. Even though the execution is deferred, the compiler will still validate that the code syntax is correct.
 */

/*
Supplier<T>	T	get()	0
Consumer<T>	void	accept(T)	1 ( T)
BiConsumer<T, U>	void	accept(T,U)	2 ( T, U)
Predicate<T>	boolean	test(T)	1 (T)
BiPredicate<T, U>	boolean	test(T, U)	2 (T, U)
Function<T, R>	R	apply(T)	1 (T)
BiFunction<T, U, R>	R	apply(T,U)	2 (T, U)
UnaryOperator<T>	T	apply(T)	1 (T)
 */

@FunctionalInterface
interface LearnToSpeak {
  void speak(String sound);
}

class DuckHelper {
  public static void teacher(String name, LearnToSpeak trainer) {

    // exercise patience

    trainer.speak(name);
  }
}

class Duckling {
  public static void makeSound(String sound) {
    //LearnToSpeak learner = s -> System.out.println(s);
    /*
    A method reference and a lambda behave the same way at runtime. You can pretend the compiler turns your method references into lambdas for you.

    There are four formats for method references:

    Static methods
    Instance methods on a particular instance
    Instance methods on a parameter to be determined at runtime
    Constructors
     */
    LearnToSpeak learner = System.out::println;
    DuckHelper.teacher(sound, learner);

    Consumer<List<Integer>> methodRef = Collections::sort;
    Consumer<List<Integer>> lambda = x -> Collections.sort(x);

    var str = "abc";
    Predicate<String> methodRef1 = str::startsWith;
    Predicate<String> lambda1 = s -> str.startsWith(s);

    var random = new Random();
    Supplier<Integer> methodRef2 = random::nextInt;
    Supplier<Integer> lambda2 = () -> random.nextInt();

    Predicate<String> methodRef3 = String::isEmpty;
    Predicate<String> lambda3 = s -> s.isEmpty();

    BiPredicate<String, String> methodRef4 = String::startsWith;
    BiPredicate<String, String> lambda4 = (s, p) -> s.startsWith(p);

    //constructor reference
    Supplier<List<String>> methodRef5 = ArrayList::new;
    Supplier<List<String>> lambda5 = () -> new ArrayList();

    Function<Integer, List<String>> methodRef6 = ArrayList::new;
    Function<Integer, List<String>> lambda6 = x -> new ArrayList(x);

  }
}