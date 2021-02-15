package com.java.genericsCollections;

/*
The Java Collections Framework is a set of classes in java.util for storing collections. There are four main interfaces in the Java Collections Framework.

List: A list is an ordered collection of elements that allows duplicate entries. Elements in a list can be accessed by an int index.
Set: A set is a collection that does not allow duplicate entries.
Queue: A queue is a collection that orders its elements in a specific order for processing. A typical queue processes its elements in a first‐in, first‐out order, but other orderings are possible.
Map: A map is a collection that maps keys to values, with no duplicate keys allowed. The elements in a map are key/value pairs.

Map doesn't implement the Collection interface. It is considered part of the Java Collections Framework, even though it isn't technically a Collection
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Test {

  public static void main(String[] args) {
    Collection<String> list = new ArrayList<>();
    list.add("a");
    list.add("ask");
    list.add("b");
    list.add("c");
    list.add("d");
    list.add("e");
    list.add("e"); // true
    list.remove("e"); // removes a single matching value

    //Java does not allow removing elements from a list while using the enhanced for loop
    //for (String ch : list) // ConcurrentModificationException
    //  list.remove(ch);

    list.removeIf( a -> a.contains("a"));
    //String a = "a";
    //list.removeIf("a"::contains);
    // list.removeIf(s -> s.startsWith("A")); no equivalent method reference as Predicate takes only one value

    System.out.println(list);

    Collection<String> set = new HashSet<>();
    set.add("a");
    set.add("b");
    set.add("c");
    set.add("d");
    set.add("e");
    set.add("e"); // false A Set does not allow duplicates

    list.forEach(a -> System.out.println(a));
    list.forEach(System.out::println);

    /*
    An ArrayList is like a resizable array. When elements are added, the ArrayList automatically grows. When you aren't sure which collection to use, use an ArrayList.
    The main benefit of an ArrayList is that you can look up any element in constant time. Adding or removing an element is slower than accessing an element. This makes an ArrayList a good choice when you are reading more often than (or the same amount as) writing to the ArrayList.

    A LinkedList is special because it implements both List and Queue. It has all the methods of a List. It also has additional methods to facilitate adding or removing from the beginning and/or end of the list.
    The main benefits of a LinkedList are that you can access, add, and remove from the beginning and end of the list in constant time. The trade‐off is that dealing with an arbitrary index takes linear time. This makes a LinkedList a good choice when you'll be using it as Queue.

     */

    Arrays.asList(1,2,3); // returns fixed size list backed by an array. can't add elements. can replace elements. can't delete elements
    List.of(1,2,3); // returns immutable list. can't add,replace or delete elements
    List.copyOf(list); // returns immutable list with copy of original collection's value. can't add replace or delete elements.

    List<String> listCast = (List<String>)  list;
    listCast.add(1, "ghi");
    listCast.set(1, "hij");
    listCast.replaceAll(x -> x+"i");

    Queue<Integer> queue = new LinkedList<>();
    // Adds an element to the back of the queue and returns whether successful
    System.out.println(queue.offer(10)); // true
    System.out.println(queue.offer(4));  // true
    // Returns next element or returns null if empty queue
    System.out.println(queue.peek());    // 10
    // Removes and returns next element or returns null if empty queue
    System.out.println(queue.poll());    // 10
    // remove -> Removes and returns next element or throws an exception if empty queue
    // add -> Adds an element to the back of the queue and returns true or throws an exception
    // element -> Returns next element or throws an exception if empty queue


    /*
    A HashSet stores its elements in a hash table, which means the keys are a hash and the values are an Object. This means that it uses the hashCode() method of the objects to retrieve them more efficiently.
    The main benefit is that adding elements and checking whether an element is in the set both have constant time. The trade‐off is that you lose the order in which you inserted the elements. Most of the time, you aren't concerned with this in a set anyway, making HashSet the most common set.

    A TreeSet stores its elements in a sorted tree structure. The main benefit is that the set is always in sorted order. The trade‐off is that adding and checking whether an element exists take longer than with a HashSet, especially as the tree grows larger.
     */

    /*
    A HashMap stores the keys in a hash table. This means that it uses the hashCode() method of the keys to retrieve their values more efficiently.
    The main benefit is that adding elements and retrieving the element by key both have constant time. The trade‐off is that you lose the order in which you inserted the elements. Most of the time, you aren't concerned with this in a map anyway. If you were, you could use LinkedHashMap

    A TreeMap stores the keys in a sorted tree structure. The main benefit is that the keys are always in sorted order. Like a TreeSet, the trade‐off is that adding and checking whether a key is present takes longer as the tree grows larger.

    clear, containsKey, containsValue, entrySet Set<Map.Entry<K,V>>, forEach(BiConsumer(K key, V value)),
    get, getOrDefault, isEmpty, keySet Set<K>, put, putIfAbsent, remove, replace, replaceAll BiFunction, size, values

     */
    Map<String, String> map = Map.ofEntries(
        Map.entry("key1", "value1"),
        Map.entry("key1", "value1"));

    map.entrySet().forEach(e ->
        System.out.println(e.getKey() + e.getValue()));

    /*
    Java provides an interface called Comparable. If your class implements Comparable, it can be used in these data structures that require comparison. There is also a class called Comparator, which is used to specify that you want to use a different order than the object itself provides.

    public interface Comparable<T> {
      int compareTo(T o);
    }
    The number 0 is returned when the current object is equivalent to the argument to compareTo().
    A negative number (less than 0) is returned when the current object is smaller than the argument to compareTo().
    A positive number (greater than 0) is returned when the current object is larger than the argument to compareTo().

    Comparable in java.lang (compareTo) 1 param, Comparator in java.util (compare) 2 param

    We said that Comparator is a functional interface because it has a single abstract method. Comparable is also a functional interface since it also has a single abstract method. However, using a lambda for Comparable would be silly. The point of Comparable is to implement it inside the object being compared.


     */

    Comparator<Duck> byWeight = (d1, d2) -> d1.getWeight()-d2.getWeight();
    Comparator<Duck> byWeight1 = Comparator.comparing(Duck::getWeight);

    var byWeight3 = new Comparator<Duck>() { // DOES NOT COMPILE
      public int compare(Duck d1, Duck d2) {
        return d1.getWeight()-d2.getWeight();
      }
    };

    /*
    Comparator<Squirrel> c = Comparator.comparing(Squirrel::getSpecies)
   .thenComparingInt(Squirrel::getWeight);

   var c = Comparator.comparing(Squirrel::getSpecies).reversed();

   List<Rabbit> rabbits = new ArrayList<>();
   rabbits.add(new Rabbit());
   Comparator<Rabbit> c = (r1, r2) -> r1.id - r2.id;
   Collections.sort(rabbits, c);
   var index = Collections.binarySearch(names, "Hoppy", c);
     */

    List<Duck> ducks = new ArrayList<>();
    // Collections.sort(ducks); compiler error as Duck doesn't implement Comparable. So doesn't know how it has ot be sorted
    // So pass a comparator
    // Lambda
    Collections.sort(ducks, (d1, d2) -> d1.getWeight() - d2.getWeight());
    // Method reference
    Collections.sort(ducks, Comparator.comparing(Duck::getWeight));
    // Anonymous class
    Collections.sort(ducks, new Comparator<Duck>() {
      @Override
      public int compare(Duck o1, Duck o2) {
        return o1.getWeight() - o2.getWeight();
      }
    });

  }

}

class Duck {
  int weight;
  public Duck(int weight) {
    this.weight = weight;
  }
  public int getWeight(){
    return this.weight;
  }
}

/*
 The syntax for introducing a generic is to declare a formal type parameter in angle brackets

A type parameter can be named anything you want. The convention is to use single uppercase letters to make it obvious that they aren't real class names. The following are common letters to use:

E for an element
K for a map key
V for a map value
N for a number
T for a generic data type
S, U, V, and so forth for multiple generic types
 */
class Crate<T> {
  private T contents;
  public T emptyCrate() {
    return contents;
  }
  public void packCrate(T contents) {
    this.contents = contents;
  }

  public static void main(String[] args) {
    Duck duck = new Duck(10);
    Crate<Duck> crateForDuck = new Crate<>();
    crateForDuck.packCrate(duck);
    Duck unpacked = crateForDuck.emptyCrate();
  }
}

class SizeLimitedCrate<T, U> {
  private T contents;
  private U sizeLimit;
  public SizeLimitedCrate(T contents, U sizeLimit) {
    this.contents = contents;
    this.sizeLimit = sizeLimit;
  }

  public static void main(String[] args) {
    Duck duck = new Duck(20);
    Integer numPounds = 15_000;
    SizeLimitedCrate<Duck, Integer> c1
        = new SizeLimitedCrate<>(duck, numPounds);
  }
}

/*
TYPE ERASURE
Behind the scenes, the compiler replaces all references to T in Crate with Object. In other words, after the code compiles, your generics are actually just Object types. The Crate class looks like the following at runtime:
public class Crate {
       private Object contents;
       public Object emptyCrate() {
          return contents;
       }
       public void packCrate(Object contents) {
          this.contents = contents;
       }
    }
This means there is only one class file. There aren't different copies for different parameterized types.
This process of removing the generics syntax from your code is referred to as type erasure. Type erasure allows your code to be compatible with older versions of Java that do not contain generics.
compiler adds the relevant casts for your code to work with this type of erased class
Duck unpacked = (Duck) crate.emptyCrate();
 */

interface Shippable<T> {
  void ship(T t);
}

class ShippableDuckCrate implements Shippable<Duck> {
  public void ship(Duck t) { }
}

class ShippableAbstractCrate<U> implements Shippable<U> {
  public void ship(U t) { }
}
/*
There are some limitations on what you can do with a generic type due to type erasure.
Oracle refers to types whose information is fully available at runtime as reifiable. Reifiable types can do anything that Java allows. Nonreifiable types have some limitations.
Calling a constructor: Writing new T() is not allowed because at runtime it would be new Object().
Creating an array of that generic type: This one is the most annoying, but it makes sense because you'd be creating an array of Object values.
Calling instanceof: This is not allowed because at runtime List<Integer> and List<String> look the same to Java thanks to type erasure.
Using a primitive type as a generic type parameter: This isn't a big deal because you can use the wrapper class instead. If you want a type of int, just use Integer.
Creating a static variable as a generic type parameter: This is not allowed because the type is linked to the instance of the class.
 */

class Handler {
  public static <T> void prepare(T t) {
    System.out.println("Preparing " + t);
  }
  public static <T> Crate<T> ship(T t) {
    System.out.println("Shipping " + t);
    return new Crate<T>();
  }
}
/*
Box.<String>ship("package");
Box.<String[]>ship(args);
 */

/*
generics don't seem particularly useful since they are treated as an Object and therefore don't have many methods available. Bounded wildcards solve this by restricting what types can be used in a wildcard. A bounded parameter type is a generic type that specifies a bound for the generic
A wildcard generic type is an unknown generic type represented with a question mark ( ?)

Unbounded wildcard	?	List<?> a = new ArrayList<String>();
Wildcard with an upper bound	? extends type	List<? extends Exception> a = new ArrayList<RuntimeException>();
Wildcard with a lower bound	? super type	List<? super Exception> a = new ArrayList<Object>();
 */

/*
A method reference is a compact syntax for writing lambdas that refer to methods. There are four types: static methods, instance methods on a particular object, instance methods on a parameter, and constructor references.
 */
/*
Each primitive class has a corresponding wrapper class. For example, long's wrapper class is Long. Java can automatically convert between primitive and wrapper classes when needed. This is called autoboxing and unboxing. Java will use autoboxing only if it doesn't find a matching method signature with the primitive. For example, remove(int n) will be called rather than remove(Object o) when called with an int.
 */
/*
The diamond operator ( <>) is used to tell Java that the generic type matches the declaration without specifying it again. The diamond operator can be used for local variables or instance variables as well as one‐line declarations.
 */

/*
The Java Collections Framework includes four main types of data structures: lists, sets, queues, and maps. The Collection interface is the parent interface of List, Set, and Queue. The Map interface does not extend Collection. You need to recognize the following:

List: An ordered collection of elements that allows duplicate entries
ArrayList: Standard resizable list
LinkedList: Can easily add/remove from beginning or end
Set: Does not allow duplicates
HashSet: Uses hashCode() to find unordered elements
TreeSet: Sorted. Does not allow null values
Queue: Orders elements for processing
LinkedList: Can easily add/remove from beginning or end
Map: Maps unique keys to values
HashMap: Uses hashCode() to find keys
TreeMap: Sorted map. Does not allow null keys
The Comparable interface declares the compareTo() method. This method returns a negative number if the object is smaller than its argument, 0 if the two objects are equal, and a positive number otherwise. The compareTo() method is declared on the object that is being compared, and it takes one parameter. The Comparator interface defines the compare() method. A negative number is returned if the first argument is smaller, zero if they are equal, and a positive number otherwise. The compare() method can be declared in any code, and it takes two parameters. Comparator is often implemented using a lambda.

The Arrays and Collections classes have methods for sort() and binarySearch(). Both take an optional Comparator parameter. It is necessary to use the same sort order for both sorting and searching, so the result is not undefined.
 */

/*
Generics are type parameters for code. To create a class with a generic parameter, add <T> after the class name. You can use any name you want for the type parameter. Single uppercase letters are common choices.

Generics allow you to specify wildcards. <?> is an unbounded wildcard that means any type. <? extends Object> is an upper bound that means any type that is Object or extends it. <? extends MyInterface> means any type that implements MyInterface. <? super Number> is a lower bound that means any type that is Number or a superclass. A compiler error results from code that attempts to add an item in a list with an unbounded or upper‐bounded wildcard.
 */
