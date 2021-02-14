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
