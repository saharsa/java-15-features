package com.java.functionalProgramming;

/*
A Supplier is used when you want to generate or supply values without taking any input.
Supplier<T>	T	get()	0

You use a Consumer when you want to do something with a parameter but not return anything.
BiConsumer does the same thing except that it takes two parameters.
Consumer<T>	void	accept(T)	1 (T)
BiConsumer<T, U>	void	accept(T,U)	2 (T, U)

Predicate is often used when filtering or matching.
Note that a Predicate returns a boolean primitive and not a Boolean object.
Both are common operations.
A BiPredicate is just like a Predicate except that it takes two parameters instead of one.
Predicate<T>	boolean	test(T)	1 (T)
BiPredicate<T, U>	boolean	test(T,U)	2 (T, U)

A Function is responsible for turning one parameter into a value of a potentially different type and returning it.
Similarly, a BiFunction is responsible for turning two parameters into a value and returning it
Function<T, R>	R	apply(T)	1 (T)
BiFunction<T, U, R>	R	apply(T,U)	2 (T, U)

UnaryOperator and BinaryOperator are a special case of a Function.
They require all type parameters to be the same type.
A UnaryOperator transforms its value into one of the same type.
For example, incrementing by one is a unary operation.
In fact, UnaryOperator extends Function.
A BinaryOperator merges two values into one of the same type.
Adding two numbers is a binary operation.
Similarly, BinaryOperator extends BiFunction.
UnaryOperator<T>	T	apply(T)	1 (T)
BinaryOperator<T>	T	apply(T,T)	2 (T, T)

Interface instance	Method return type	Method name	Method parameters
Consumer	Consumer	andThen()	Consumer
Function	Function	andThen()	Function
Function	Function	compose()	Function
Predicate	Predicate	and()	Predicate
Predicate	Predicate	negate()	—
Predicate	Predicate	or()	Predicate

 */

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

  public static void main(String[] args) {

    Supplier<LocalDate> s1 = LocalDate::now;
    Supplier<LocalDate> s2 = () -> LocalDate.now();
    Supplier<StringBuilder> s3 = StringBuilder::new;
    Supplier<StringBuilder> s4 = () -> new StringBuilder();
    Supplier<ArrayList<String>> s5 = ArrayList<String>::new;

    System.out
        .println(s1); // com.java.functionalProgramming.Test$$Lambda$14/0x0000000800bb1240@15aeb7ab
    System.out.println(s2.get()); // 2021-02-15
    System.out.println(s5.get());

    Consumer<String> c1 = System.out::println;
    Consumer<String> c2 = x -> System.out.println(x);
    var map = new HashMap<String, Integer>();
    BiConsumer<String, Integer> b1 = map::put;
    BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);
    b1.accept("chicken", 7);
    b2.accept("chick", 1);

    System.out.println(map); // {chicken=7, chick=1}
    c1.accept("Annie"); // Annie
    c2.accept("Annie"); // Annie

    Predicate<String> p1 = String::isEmpty;
    Predicate<String> p2 = x -> x.isEmpty();
    BiPredicate<String, String> p3 = String::startsWith;
    BiPredicate<String, String> p4 =
        (string, prefix) -> string.startsWith(prefix);

    System.out.println(p1.test(""));  // true
    System.out.println(p2.test(""));  // true
    System.out.println(p3.test("chicken", "chick"));  // true
    System.out.println(p4.test("chicken", "chick"));  // true

    Function<String, Integer> f1 = String::length;
    Function<String, Integer> f2 = x -> x.length();
    BiFunction<String, String, String> f3 = String::concat;
    BiFunction<String, String, String> f4 =
        (string, toAdd) -> string.concat(toAdd);

    System.out.println(f1.apply("cluck")); // 5
    System.out.println(f2.apply("cluck")); // 5
    System.out.println(f3.apply("baby ", "chick")); // baby chick
    System.out.println(f4.apply("baby ", "chick")); // baby chick

    UnaryOperator<String> u1 = String::toUpperCase;
    UnaryOperator<String> u2 = x -> x.toUpperCase();
    BinaryOperator<String> u3 = String::concat;
    BinaryOperator<String> u4 = (string, toAdd) -> string.concat(toAdd);

    System.out.println(u1.apply("chirp"));  // CHIRP
    System.out.println(u2.apply("chirp"));  // CHIRP
    System.out.println(u3.apply("baby ", "chick")); // baby chick
    System.out.println(u4.apply("baby ", "chick")); // baby chick

    Predicate<String> brownEggs =
        s -> s.contains("egg") && s.contains("brown");
    Predicate<String> otherEggs =
        s -> s.contains("egg") && !s.contains("brown");

    Predicate<String> brown = s -> s.contains("brown");
    //BiPredicate<String,String> brown1 = String::contains;
    Predicate<String> egg = s -> s.contains("egg");
    Predicate<String> brownEggs1 = egg.and(brown);
    Predicate<String> otherEggs2 = egg.and(brown.negate());

    // Consumer instances are run in sequence and are independent of each other.
    Consumer<String> co1 = x -> System.out.print("1: " + x);
    Consumer<String> co2 = x -> System.out.print(",2: " + x);
    Consumer<String> combined = co1.andThen(co2);
    combined.accept("Annie"); // // 1: Annie,2: Annie

    // compose() method on Function chains functional interfaces. However, it passes along the output of one to the input of another.
    Function<Integer, Integer> before = x -> x + 1;
    Function<Integer, Integer> after = x -> x * 2;

    Function<Integer, Integer> combinedFunction = after.compose(before);
    System.out.println(combinedFunction.apply(3));   // 8

    /*
    We express “we don't know” or “not applicable” answer in Java using Optional type.
    An Optional is created using a factory.
    You can either request an empty Optional or pass a value for the Optional to wrap.
    Think of an Optional as a box that might have something in it or might instead be empty.

    get()	Throws an exception	Returns value
    ifPresent(Consumer c)	Does nothing	Calls Consumer with value
    isPresent()	Returns false	Returns true
    orElse(T other)	Returns other parameter	Returns value
    orElseGet(Supplier s)	Returns result of calling Supplier	Returns value
    orElseThrow()	Throws NoSuchElementException	Returns value
    orElseThrow(Supplier s)	Throws exception created by calling Supplier	Returns value
     */
    /*
    Before Java 8, programmers would return null instead of Optional.
    There were a few shortcomings with this approach.
    One was that there wasn't a clear way to express that null might be a special value.
    By contrast, returning an Optional is a clear statement in the API that there might not be a value in there.
    Another advantage of Optional is that you can use a functional programming style with ifPresent() and the other methods rather than needing an if statement.
    You can chain Optional calls.
     */
    System.out.println(average(90, 100)); // Optional[95.0]
    System.out.println(average());        // Optional.empty

    Optional<Double> opt = average();
    if (opt.isPresent()) {
      System.out.println(opt.get()); // 95.0
    }

    opt.ifPresent(System.out::println);
    System.out.println(opt.orElseGet(Math::random));

    /*

    Stream.empty()	Finite	Creates Stream with zero elements
    Stream.of(varargs)	Finite	Creates Stream with elements listed
    coll.stream()	Finite	Creates Stream from a Collection
    coll.parallelStream()	Finite	Creates Stream from a Collection where the stream can run in parallel
    Stream.generate(supplier)	Infinite	Creates Stream by calling the Supplier for each element upon request
    Stream.iterate(seed,unaryOperator)	Infinite	Creates Stream by using the seed for the first element and then calling the UnaryOperator for each subsequent element upon request
    Stream.iterate(seed,predicate, unaryOperator)	Finite or infinite	Creates Stream by using the seed for the first element and then calling the UnaryOperator for each subsequent element upon request. Stops if the Predicate returns false

    You can perform a terminal operation without any intermediate operations but not the other way around. This is why we will talk about terminal operations first. Reductions are a special type of terminal operation where all of the contents of the stream are combined into a single primitive or Object.

    Method	What happens for infinite streams	Return value	Reduction
    count()	Does not terminate	long	Yes
    min()max()	Does not terminate	Optional<T>	Yes
    findAny()findFirst()	Terminates	Optional<T>	No
    allMatch()anyMatch()noneMatch()	Sometimes terminates	boolean	No
    forEach()	Does not terminate	void	No
    reduce()	Does not terminate	Varies	Yes
    collect()	Does not terminate	Varies	Yes
     */
    Stream<String> empty = Stream.empty();          // count = 0
    Stream<Integer> singleElement = Stream.of(1);   // count = 1
    Stream<Integer> fromArray = Stream.of(1, 2, 3); // count = 3
    var list = List.of("av", "bnk", "ci");
    Stream<String> fromList = list.stream();
    Stream<String> fromListParallel = list.parallelStream();
    Stream<Double> randoms = Stream.generate(Math::random); // infinite stream
    Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2); // infinite stream
    Stream<Integer> oddNumbers1 = Stream.iterate(1, n -> n < 100,
        n -> n + 2); // seed, predicate to specify when done, unaryoperator to get next value

    // determines the number of elements in a finite stream. For an infinite stream, it never terminates
    System.out.println(fromArray.count());
    /*
    A stream can have only one terminal operation. Once a terminal operation has been run, the stream cannot be used again.
     */
    System.out.println(fromList.max((i, j) -> i.length() - j.length())); // Optional[bnk]
    //System.out.println(fromArray.min((i,j)-> i-j));

    Stream<String> infinite = Stream.generate(() -> "chimp");
    infinite.findAny().ifPresent(System.out::println); // chimp

    Stream<String> infinite1 = Stream.generate(() -> "chimp");
    var list1 = List.of("monkey", "2", "chimp");
    Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
    System.out.println(list.stream().anyMatch(pred));  // true
    System.out.println(list.stream().allMatch(pred));  // false
    System.out.println(list.stream().noneMatch(pred)); // false
    System.out.println(infinite1.anyMatch(pred)); // true

    var list2 = List.of("monkey", "2", "chimp");
    list2.forEach(System.out::print);

    Stream<String> stream = Stream.of("w", "o", "l", "f");
    String word = stream.reduce("", (s, c) -> s + c);
    System.out.println(word); // wolf

    /*
    If the stream is empty, an empty Optional is returned.
    If the stream has one element, it is returned.
    If the stream has multiple elements, the accumulator is applied to combine them.
     */

    Stream<String> stream1 = Stream.of("w", "o", "l", "f!");
    // initializer accumulator combiner (combines any intermediate totals)
    // The three‐argument reduce() operation is useful when working with parallel streams because it allows the stream to be decomposed and reassembled by separate threads
    int length = stream1.reduce(0, (i, s) -> i + s.length(), (a, b) -> a + b);
    System.out.println(length); // 5

    // The collect() method is a special type of reduction called a mutable reduction
    Stream<String> stream2 = Stream.of("w", "o", "l", "f");
    // supplier accumulator(biconsumer) combiner(biconsumer)
    StringBuilder builtWord = stream2
        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
    System.out.println(builtWord); // wolf

    Stream<String> stream3 = Stream.of("w", "o", "l", "f");
    TreeSet<String> set = stream3.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
    System.out.println(set); // [f, l, o, w]

    Stream<String> stream4 = Stream.of("w", "o", "l", "f");
    TreeSet<String> streamedSet = stream4.collect(Collectors.toCollection(TreeSet::new));
    System.out.println(streamedSet); // [f, l, o, w]

    Stream<String> stream5 = Stream.of("w", "o", "l", "f");
    Set<String> streamedSet1 = stream5.collect(Collectors.toSet());
    System.out.println(streamedSet1); // [f, w, l, o]

    Stream<String> st = Stream.of("monkey", "gorilla", "bonobo");
    st.filter(x -> x.startsWith("m")).forEach(System.out::print); // monkey

    // The distinct() method returns a stream with duplicate values removed. The duplicates do not need to be adjacent to be removed
    Stream<String> st1 = Stream.of("duck", "duck", "duck", "goose");
    st1.distinct().forEach(System.out::print); // duckgoose

    // make a finite stream out of an infinite stream
    Stream<Integer> st2 = Stream.iterate(1, n -> n + 1);
    st2.skip(5).limit(2).forEach(System.out::print); // 67

    // The map() method creates a one‐to‐one mapping from the elements in the stream to the elements of the next step in the stream
    Stream<String> st3 = Stream.of("monkey", "gorilla", "bonobo");
    st3.map(String::length).forEach(System.out::print); // 676

    // The flatMap() method takes each element in the stream and makes any elements it contains top‐level elements in a single stream. This is helpful when you want to remove empty elements from a stream or you want to combine a stream of lists.
    List<String> zero = List.of();
    var one = List.of("Bonobo");
    var two = List.of("Mama Gorilla", "Baby Gorilla");
    Stream<List<String>> animals = Stream.of(zero, one, two);
    animals.flatMap(m -> m.stream()).forEach(System.out::println);

    Stream<String> st4 = Stream.of("brown-", "bear-");
    st4.sorted(Comparator.reverseOrder()).forEach(System.out::print); // bear-brown-

    // common use for peek() is to output the contents of the stream as it goes by
    var st5 = Stream.of("black bear", "brown bear", "grizzly");
    long count = st5.filter(s -> s.startsWith("g")).peek(System.out::println)
        .count();              // grizzly
    System.out.println(count);                          // 1

    var list3 = List.of("Toby", "Anna", "Leroy", "Alex");
    list3.stream().filter(n -> n.length() == 4).sorted()
        .limit(2).forEach(System.out::println);

    // pipelines
    long count1 = Stream.of("goldfish", "finch")
        .filter(s -> s.length() > 5)
        .collect(Collectors.toList())
        .stream()
        .count();
    System.out.println(count1);   // 1

    /*
    IntStream: Used for the primitive types int, short, byte, and char
    LongStream: Used for the primitive type long
    DoubleStream: Used for the primitive types double and float
     */
    IntStream intStream = IntStream.of(1, 2, 3);
    OptionalDouble avg = intStream.average();
    System.out.println(avg.getAsDouble());  // 2.0
    IntStream range = IntStream.range(1, 6);
    IntStream rangeClosed = IntStream.rangeClosed(1, 5);


    LocalDate date = LocalDate.of(2020, Month.OCTOBER, 20);
    LocalTime time = LocalTime.of(11, 12, 34);
    LocalDateTime dt = LocalDateTime.of(date, time);

    System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
    System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME));
    System.out.println(dt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

    DateFormat s = new SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm");
    System.out.println(s.format(new Date()));  // October 20, 2020 at 06:15

    Locale l1 = new Locale.Builder()
        .setLanguage("en")
        .setRegion("US")
        .build();
    Locale locale = new Locale("fr");
    Locale.setDefault(locale);

    int attendeesPerYear = 3_200_000;
    int attendeesPerMonth = attendeesPerYear / 12;

    var us = NumberFormat.getInstance(Locale.US); // 266,666
    System.out.println(us.format(attendeesPerMonth));

    var gr = NumberFormat.getInstance(Locale.GERMANY); // 266.666
    System.out.println(gr.format(attendeesPerMonth));

    var ca = NumberFormat.getInstance(Locale.CANADA_FRENCH);
    System.out.println(ca.format(attendeesPerMonth)); // 266 666

  }

  /*
  ResourceBundle
  Zoo_en.properties
  Zoo_fr.properties

   */
  public static void printWelcomeMessage(Locale locale) {
    var rb = ResourceBundle.getBundle("Zoo", locale);
    System.out.println(rb.getString("hello") + ", " + rb.getString("open"));
  }

  public static Optional<Double> average(int... scores) {
    if (scores.length == 0) {
      return Optional.empty();
    }
    int sum = 0;
    for (int score : scores) {
      sum += score;
    }
    return Optional.of((double) sum / scores.length);
  }

}
