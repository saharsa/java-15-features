package com.java.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {

  public static void main(String[] args) {
    int[] a = {3,2,1,4};
    System.out.println(Arrays.toString(a));
    Arrays.sort(a);
    System.out.println(Arrays.toString(a));
    System.out.println(Arrays.binarySearch(a, 3));

    /*
    A negative number means the first array is smaller than the second.
    A zero means the arrays are equal.
    A positive number means the first array is larger than the second.

    If both arrays are the same length and have the same values in each spot in the same order, return zero.
    If all the elements are the same but the second array has extra elements at the end, return a negative number.
    If all the elements are the same but the first array has extra elements at the end, return a positive number.
    If the first element that differs is smaller in the first array, return a negative number.
    If the first element that differs is larger in the first array, return a positive number.

    null is smaller than any other value.
    For numbers, normal numeric order applies.
    For strings, one is smaller if it is a prefix of another.
    For strings/characters, numbers are smaller than letters.
    For strings/characters, uppercase is smaller than lowercase.
     */
    System.out.println(Arrays.compare(new int[] {1}, new int[] {2}));

    /*
    If the arrays are equal, mismatch() returns -1. Otherwise, it returns the first index where they differ.
     */
    System.out.println(Arrays.mismatch(new int[] {1}, new int[] {1}));
    System.out.println(Arrays.mismatch(new String[] {"a"},
        new String[] {"A"}));
    System.out.println(Arrays.mismatch(new int[] {1, 2}, new int[] {1}));

    /*
    An array has one glaring shortcoming: You have to know how many elements will be in the array when you create it, and then you are stuck with that choice.
    Just like a StringBuilder, an ArrayList can change capacity at runtime as needed.
    Like an array, an ArrayList is an ordered sequence that allows duplicates.
     */

    /*
    Wrapper classes
    Can store 'null' value
    autoboxing, unboxing

    valueOf preferred over new as it allows caching
     */

    Boolean t = Boolean.valueOf(true);
    String u = String.valueOf(123);


    List<String> list = new ArrayList<>();
    list.add("hawk");
    list.add("robin");
    String[] stringArray = list.toArray(new String[0]);
    List<String> list1 = Arrays.asList(stringArray);

    List<String> list2 = Arrays.asList("one", "two");
    List<String> list3 = List.of("one", "two");

    Collections.sort(list2);

  }

}
