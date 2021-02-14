package com.java.classes;

public class Test2 {

  public static void main(String[] args) {
    new Stork("crow").printName(); // Stork
  }

}

/*
An abstract class is a class that cannot be instantiated and may contain abstract methods.
An abstract method is a method that does not define an implementation when it is declared.
Both abstract classes and abstract methods are denoted with the abstract modifier.
An abstract class is one cannot be instantiated
 */

/*
Abstract Class Definition Rules

Abstract classes cannot be instantiated.
All top-level types, including abstract classes, cannot be marked protected or private.
Abstract classes cannot be marked final.
Abstract classes may include zero or more abstract and nonabstract methods.
An abstract class that extends another abstract class inherits all of its abstract methods.
The first concrete class that extends an abstract class must provide an implementation for all of the inherited abstract methods.
Abstract class constructors follow the same rules for initialization as regular constructors, except they can be called only as part of the initialization of a subclass.
 */

/*
Abstract Method Definition Rules

Abstract methods can be defined only in abstract classes or interfaces.
Abstract methods cannot be declared private or final.
Abstract methods must not provide a method body/implementation in the abstract class in which they are declared.
Implementing an abstract method in a subclass follows the same rules for overriding a method, including covariant return types, exception declarations, etc.
 */

abstract class Bird {

  protected String name;

  /*
   a constructor in an abstract class and a nonabstract class is that a constructor in abstract class can be called only when it is being initialized by a nonabstract subclass
   */
  public Bird(String name){
    this.name = name;
  }

  public abstract String getName();

  /*
    As long as you do not mark the method as final, the subclass has the option to override an inherited method.
    public final abstract void getNothing(); // does not compile
    A method cannot be marked as both abstract and private
    it is possible (albeit redundant) to declare a method final and private.
   */

  public void printName() {
    System.out.print(getName());
  }

  /*
  If a static method cannot be overridden, then it follows that it also cannot be marked abstract since it can never be implemented.
  abstract static void swim();  // DOES NOT COMPILE
   */
}

class Stork extends Bird {

  public Stork(String name) {
    super(name);
  }

  public String getName() { return "Stork!"; }

}

/*
An interface is an abstract data type are that declares a list of abstract methods that any class implementing the interface must provide. An interface can also include constant variables. Both abstract methods and constant variables included with an interface are implicitly assumed to be public.
With Java 8, interfaces were updated to include static and default methods.
A default method is one in which the interface method has a body and is not marked abstract.
It was added for backward compatibility, allowing an older class to use a new version of an interface that contains a new method, without having to modify the existing class.
In Java 9, interfaces were updated to support private and private static methods.
Both of these types were added for code reusability within an interface declaration and cannot be called outside the interface definition.

public abstract implicit modifiers for method
public static final implicit modifiers for variable
abstract implicit modifier for interface

static blocks not allowed so variable has to be initialized immediately
 */
interface SomeInterface {
  int a = 1;
}

interface Nocturnal {}

interface CanFly {
  public void flap();
}

interface HasBigEyes extends Nocturnal, CanFly {}

class Owl implements Nocturnal, CanFly {
  public int hunt() { return 5; }
  public void flap() { System.out.println("Flap!"); }
}

/*
Differences between abstract classes and interfaces

Even though abstract classes and interfaces are both considered abstract types, only interfaces make use of implicit modifiers.
This means that an abstract class and interface with similar declarations may have very different properties.
An interface can extend multiple interface
The primary differences between the two are that interfaces include implicit modifiers, do not contain constructors, do not participate in the instance initialization process, and support multiple inheritance.
 */

interface Herbivore {
  void eatPlants();
  //int legs(); // does not compile as not covariant return types as per overriding principle
}

interface Omnivore {
  void eatPlants();
  void eatMeat();
  void legs();
}

/*
you just need to be able to create a single method that overrides both inherited abstract methods at the same time
 */
class Animal implements Herbivore, Omnivore {
  public void eatMeat() {
    System.out.println("Eating meat");
  }
  public void eatPlants() {
    System.out.println("Eating plants");
  }

  public void legs() {

  }

}

/*
Interface Definition Rules

Interfaces cannot be instantiated.
All top-level types, including interfaces, cannot be marked protected or private.
Interfaces are assumed to be abstract and cannot be marked final.
Interfaces may include zero or more abstract methods.
An interface can extend any number of interfaces.
An interface reference may be cast to any reference that inherits the interface, although this may produce an exception at runtime if the classes aren’t related.
The compiler will only report an unrelated type error for an instanceof operation with an interface on the right side if the reference on the left side is a final class that does not inherit the interface.
An interface method with a body must be marked default, private, static, or private static (covered when studying for the 1Z0-816 exam).
The following are the five rules for abstract methods defined in interfaces.

Abstract Interface Method Rules

Abstract methods can be defined only in abstract classes or interfaces.
Abstract methods cannot be declared private or final.
Abstract methods must not provide a method body/implementation in the abstract class in which is it declared.
Implementing an abstract method in a subclass follows the same rules for overriding a method, including covariant return types, exception declarations, etc.
Interface methods without a body are assumed to be abstract and public.
The first four rules for abstract methods, whether they be defined in abstract classes or interfaces, are exactly the same.

Interface Variables Rules

Interface variables are assumed to be public, static, and final.
Because interface variables are marked final, they must be initialized with a value when they are declared.

 */