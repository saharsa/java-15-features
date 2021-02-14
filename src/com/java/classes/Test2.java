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