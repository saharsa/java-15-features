package com.java.innerClasses;

import com.java.innerClasses.Zoo.Ticket;

/*
A member inner class is a class defined at the member level of a class (the same level as the methods, instance variables, and constructors).
It is the opposite of a top-level class, in that it cannot be declared unless it is inside another class.

Four types of nested classes: member inner classes, local classes, anonymous classes, and static nested classes.

Top-level classes and interfaces can only be set with public or package-private access, member inner classes do not have the same restriction.
A member inner class can be declared with all of the same access modifiers as a class member, such as public, protected, default (package-private), or private.
A member inner class can contain many of the same methods and variables as a top-level class. Some members are disallowed in member inner classes, such as static members.

The scope of a nested class is bounded by the scope of its enclosing class. Thus in above example, class NestedClass does not exist independently of class OuterClass.
A nested class has access to the members, including private members, of the class in which it is nested. However, the reverse is not true i.e., the enclosing class does not have access to the members of the nested class.
A nested class is also a member of its enclosing class.
As a member of its enclosing class, a nested class can be declared private, public, protected, or package private(default).
Nested classes are divided into two categories:
static nested class : Nested classes that are declared static are called static nested classes.
inner class : An inner class is a non-static nested class.

 */

/*

Benefits:
There are many benefits of using nested classes.
They can encapsulate helper classes by restricting them to the containing class. They can make it easy to create a class that will be used in only one place.
They can make the code cleaner and easier to read.

 */

/*

Inner classes have the following properties:
Can be declared public, protected, package‐private (default), or private
Can extend any class and implement interfaces
Can be marked abstract or final
Cannot declare static fields or methods, except for static final fields
Can access members of the outer class including private members

 */

/*

Compiling the Outer.java class with which we have been working creates two class files. Outer.class you should be expecting.
For the inner class, the compiler creates Outer$Inner.class.

 */
class Zoo {

  public class Ticket implements Paper {

    private String serialNumber;

    public String getId() {
      return serialNumber;
    }
    /*

    can’t have static method in a nested inner class because an inner class is implicitly associated with an object of its outer class so it cannot define any static method for itself

    public static void display(){
      System.out.println("A");
    }

     */
  }

  private interface Paper {

    public String getId();
  }

  public Ticket sellTicket(String serialNumber) {
    var t = new Ticket();
    t.serialNumber = serialNumber;
    return t;
  }

}

/*
Method Local inner class

Like local variables, a local class declaration does not exist until the method is invoked, and it goes out of scope when the method returns.
This means you can create instances only from within the method.
Those instances can still be returned from the method.
Local classes are not limited to being declared only inside methods.
They can be declared inside constructors and initializers too.

Local classes have the following properties:

They do not have an access modifier.
They cannot be declared static and cannot declare static fields or methods, except for static final fields.
They have access to all fields and methods of the enclosing class (when defined in an instance method).
They can access local variables if the variables are final or effectively final.

 */
class Outer {

  int k = 3;

  void outerMethod(int j) {
    // j = 2; won't work as j is not effectively final
    int i = 1;
    k = 4;
    System.out.println("inside outer method");
    class Inner {

      void innerMethod() {
        System.out.println("inside inner method" + j + " " + k);
      }
    }
    Inner y = new Inner();
    y.innerMethod();
  }
}

/*
Static nested class

They are like a static member of outer class.
static nested class can be instantiated without an instance of the enclosing class
The trade‐off, though, is it can't access instance variables or methods in the outer class directly. It can be done but requires an explicit reference to an outer class variable.
The nesting creates a namespace because the enclosing class name must be used to refer to it.
It can be made private or use one of the other access modifiers to encapsulate it.
The enclosing class can refer to the fields and methods of the static nested class

 */

class Outer1 {

  private static void outerMethod() {
    System.out.println("inside outer method");
  }

  static class Inner {

    public void innerMethod() {
      outerMethod();
      System.out.println("inside inner method");
    }
  }
}

/*
Anonymous inner class

declared without any name at all. They are created in two ways.
As subclass of specified type
As implementer of the specified interface

In above code we create an object of anonymous inner class but this anonymous inner class is an implementer of the interface Hello. Any anonymous inner class can implement only one interface at one time.
It can either extend a class or implement interface at a time.
It is an inner class without a name and for which only a single object is created.
An anonymous inner class can be useful when making an instance of an object with certain “extras” such as overloading methods of a class or interface, without having to actually subclass a class.

A normal class can implement any number of interfaces but anonymous inner class can implement only one interface at a time.
A regular class can extend a class and implement any number of interface simultaneously. But anonymous Inner class can extend a class or can implement an interface but not both at a time.
For regular/normal class, we can write any number of constructors but we cant write any constructor for anonymous Inner class because anonymous class does not have any name and while defining constructor class name and constructor name must be same

An anonymous class has access to the members of its enclosing class.
An anonymous class cannot access local variables in its enclosing scope that are not declared as final or effectively final.
Like a nested class, a declaration of a type (such as a variable) in an anonymous class shadows any other declarations in the enclosing scope that have the same name.

We cannot declare static initializers or member interfaces in an anonymous class.
An anonymous class can have static members provided that they are constant variables.

Note that you can declare the following in anonymous classes:
Fields
Extra methods (even if they do not implement any methods of the supertype)
Instance initializers
Local classes

However, constructors can not be declared in an anonymous class.

 */
class Demo {

  void show() {
    System.out.println("i am in show method of super class");
  }
}

class Flavor1Demo {

  //  An anonymous class with Demo as base class
  static Demo d = new Demo() {
    void show() {
      super.show();
      System.out.println("i am in Flavor1Demo class");
    }
  };

  public static void main(String[] args) {
    d.show();
  }
}

class Flavor2Demo {

  // An anonymous class that implements Hello interface
  static Hello h = new Hello() {
    public void show() {
      System.out.println("i am in anonymous class");
    }
  };

  public static void main(String[] args) {
    h.show();
  }
}

interface Hello {

  void show();
}

public class Test {

  public static void main(String[] args) {
    Zoo zoo = new Zoo();
    Ticket t = zoo.sellTicket("ABC");
    Ticket t1 = new Zoo().new Ticket();

    Outer x = new Outer();
    x.outerMethod(2);

    Outer1.Inner inner1 = new Outer1.Inner();
    inner1.innerMethod();
  }

}

class A {

  private int x = 10;

  class B {

    private int x = 20;

    class C {

      private int x = 30;

      public void allTheX() {
        System.out.println(x);        // 30
        System.out.println(this.x);   // 30
        System.out.println(B.this.x); // 20
        System.out.println(A.this.x); // 10
      }
    }
  }

  public static void main(String[] args) {
    A a = new A();
    A.B b = a.new B();
    A.B.C c = b.new C();
    c.allTheX();
  }
}

/*

Permitted Modifiers Inner class static nested class Local class Anonymous class
Access modifiers	  All         All                 None        None
abstract	          Yes         Yes                 Yes         No
Final	              Yes         Yes                 Yes         No

Permitted Members Inner class static nested class Local class Anonymous class
Instance methods	Yes         Yes                 Yes         Yes
Instance variables Yes        Yes                 Yes         Yes
static methods	   No         Yes                 No          No
static variables	 Yes(if final)  Yes             Yes(if final) Yes(if final)

                                                                          Inner class   static nested class   Local class                                 Anonymous class
Can extend any class or implement any number of interfaces	              Yes           Yes                   Yes                                         No—must have exactly one superclass or one interface
Can access instance members of enclosing class without a reference	      Yes           No                    Yes (if declared in an instance method)     Yes (if declared in an instance method)
Can access local variables of enclosing method                            N/A           N/A                   Yes (if final or effectively final)         Yes (if final or effectively final)

 */