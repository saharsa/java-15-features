package com.java.lambdas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Test {

  public static void main(String[] args) {

    /*
    Functional programming uses lambda expressions to write code.
    A lambda expression is a block of code that gets passed around.
    You can think of a lambda expression as an unnamed method.
    It has parameters and a body just like full-fledged methods do, but it doesn’t have a name like a real method.
    Lambda expressions are often referred to as lambdas for short.

    Lambdas are passed to a method expecting an instance of a functional interface.

    Lambdas work with interfaces that have only one abstract method.
    These are called functional interfaces.
    @FunctionalInterface
    Predicate -> boolean test(T t), Consumer -> void accept(T t), Supplier -> get(), and Comparator
    Comparator 2 params, one return value of type int
    Consumer 1 param, void return type
    Predicate 1 param, boolean return type
    Supplier no param, one return type

    lambda can access an instance variable, method parameter, or local variable under certain conditions.
    Instance variables (and class variables) are always allowed.
    Method parameters and local variables are allowed to be referenced if they are effectively final.
    This means that the value of a variable doesn’t change after it is set, regardless of whether it is explicitly marked as final.
     */

    Predicate<String> predicate = a -> a.startsWith("a");
    System.out.println(predicate.test("and"));
    System.out.println(predicate.test("nd"));

    Consumer<String> consumer = x -> System.out.println(x);
    consumer.accept("Hello world!");

    Supplier<Integer> random = () ->  new Random().nextInt();
    System.out.println(random.get());

    Comparator<Integer> ints = (i1, i2) -> i1 - i2;
    Comparator<String> strings = (s1, s2) -> s2.compareTo(s1);
    System.out.println(ints.compare(1,2));
    System.out.println(strings.compare("a", "b"));

    List<String> bunnies = new ArrayList<>();
    bunnies.add("long ear");
    bunnies.add("floppy");
    bunnies.add("hoppy");
    // Comparator
    bunnies.sort((b1, b2) -> b1.compareTo(b2));
    // Consumer
    bunnies.forEach(b -> System.out.println(b));
    System.out.println(bunnies);// [long ear, floppy, hoppy]
    // Predicate
    bunnies.removeIf(s -> s.charAt(0) != 'h');
    System.out.println(bunnies);     // [hoppy]

    Map<String, Integer> bunnies1 = new HashMap<>();
    bunnies1.put("long ear", 3);
    bunnies1.put("floppy", 8);
    bunnies1.put("hoppy", 1);
    //BiConsumer
    bunnies1.forEach((k, v) -> System.out.println(k + " " + v));

  }

}
