package com.java.classes;

import java.util.List;

public class Test {

  public static void main(String[] args) {
    /*
     instantiation

     When Java sees the new keyword, it allocates memory for the new object.
     It then looks for a constructor with a matching signature and calls it
     */
    B b = new B(1);

    new C(); // 0-10-BestZoo-z-

    new D(); // 0 ready swimmy 1 Constructor
  }

}

class A {

  /*
  Java sees no constructor was coded and creates one - default constructor.
  This happens during the compile step.
  If you look at the file with the .java extension, the constructor will still be missing. It is only in the compiled file with the .class extension that it makes an appearance.
   */

  /*
  Having only private constructors in a class tells the compiler not to provide a default no-argument constructor.
  It also prevents other classes from instantiating the class.
  This is useful when a class has only static methods or the developer wants to have full control of all calls to create new instances of the class. Remember, static methods in the class, including a main() method, may access private members, including private constructors.
   */

  public A() {
    System.out.println("A");
  }

  public void get() {
    System.out.println("get()");
  }

  public void get(int a) {
    System.out.println("get(int)");
  }

}

/*
Inheritance is the process by which a subclass automatically includes any public or protected members of the class, including primitives, objects, or methods, defined in the parent class.
to prevent a class from being extended by marking the class with the final modifier.
 */
class B extends A {

  private int j;

  private final String name;
  private final String type;

  {
    /*
    Unlike local final variables, which are not required to have a value unless they are actually used,
    final instance variables must be assigned a value.
    Default values are not used for these variables.
    If they are not assigned a value in the line where they are declared or in an instance initializer,
    then they must be assigned a value in the constructor declaration.
    Failure to do so will result in a compiler error on the line that declares the constructor.
     */
    name = "TEST";
  }

  public B(String type) {
    //name = "TEST1";
    this.j = 1;
    this.type = type;
  }

  /*
  constructor overloading

  var not allowed in constructors
   */
  public B(int j) {
    // super(); won't compile as this() needs to be first
    /*
    Java calls another constructor on the same instance of the class
    must be the first statement in the constructor.
    The side effect of this is that there can be only one call to this() in any constructor
     */
    this("test");
    // super(); won't compile as this also needs to be first
    System.out.println("B");
    this.j = j;
  }

  public void get(float a) {
    System.out.println("get(float)");
    //return 0;
  }

}

/*
Despite using the same keyword, this and this() are very different. The first, this, refers to an instance of the class, while the second, this(), refers to a constructor call within the class.
super and super() are unrelated in Java. The first, super, is used to reference members of the parent class, while the second, super(), calls a parent constructor

Java compiler automatically inserts a call to the no-argument constructor super() if you do not explicitly call this() or super() as the first line of a constructor
 */

/*
a final class cannot be extended

What happens if you have a class that is not marked final but only contains private constructors—can you extend the class?
The answer is “yes,” but only an inner class defined in the class itself can extend it.
An inner class is the only one that would have access to a private constructor and be able to call super().
Other top-level classes cannot extend such a class.
 */

/*
Initialize class X:

If there is a superclass Y of X, then initialize class Y first.
Process all static variable declarations in the order they appear in the class.
Process all static initializers in the order they appear in the class.

Initialize Instance of X:
If there is a superclass Y of X, then initialize the instance of Y first.
Process all instance variable declarations in the order they appear in the class.
Process all instance initializers in the order they appear in the class.
Initialize the constructor including any overloaded constructors referenced with this().
 */

/*
constructors are executed from the bottom up, but since the first line of every constructor is a call to another constructor, the flow actually ends up with the parent constructor executed before the child constructor
 */

class C {

  private String name = "BestZoo";

  {
    System.out.print(name + "-");
  }

  private static int COUNT = 0;

  static {
    System.out.print(COUNT + "-");
  }

  static {
    COUNT += 10;
    System.out.print(COUNT + "-");
  }

  public C() {
    System.out.print("z-");
  }
}


class D {

  private String name = "swimmy";
  { System.out.println(name); }

  private static int COUNT = 0;
  static { System.out.println(COUNT); }
  { COUNT++; System.out.println(COUNT); }

  public D() {
    System.out.println("Constructor");
  }

  public static void main(String[] args) {
    System.out.println("Ready");
    new D();
  }
}

class E {
  static { System.out.print("A"); }
  { System.out.print("B"); }
  public E(String name) {
    this(1);
    System.out.print("C");
  }

  public E() {
    System.out.print("D");
  }

  public E(int stripes) {
    System.out.print("E");
  }
}

class F extends E {
  static { System.out.print("F"); }

  public F(int stripes) {
    super("sugar");
    System.out.print("G");
  }

  { System.out.print("H"); }

  public static void main(String[] grass) {
    new F(1);
    System.out.println(); // A F B E C H G
    new F(2); // B E C H G
    }
}

/*
The first statement of every constructor is a call to an overloaded constructor via this(), or a direct parent constructor via super().
If the first statement of a constructor is not a call to this() or super(), then the compiler will insert a no-argument super() as the first statement of the constructor.
Calling this() and super() after the first statement of a constructor results in a compiler error.
If the parent class doesn’t have a no-argument constructor, then every constructor in the child class must start with an explicit this() or super() constructor call.
If the parent class doesn’t have a no-argument constructor and the child doesn’t define any constructors, then the child class will not compile.
If a class only defines private constructors, then it cannot be extended by a top-level class.
All final instance variables must be assigned a value exactly once by the end of the constructor. Any final instance variables not assigned a value will be reported as a compiler error on the line the constructor is declared.
 */

/*
In Java, overriding a method occurs when a subclass declares a new implementation for an inherited method with the same signature and compatible return type.
Remember that a method signature includes the name of the method and method parameters.
When you override a method, you may reference the parent version of the method using the super keyword.
In this manner, the keywords this and super allow you to select between the current and parent versions of a method, respectively.
 */

/*
The method in the child class must have the same signature as the method in the parent class.
The method in the child class must be at least as accessible as the method in the parent class.
The method in the child class may not declare a checked exception that is new or broader than the class of any exception declared in the parent class method.
If the method returns a value, it must be the same or a subtype of the method in the parent class, known as covariant return types.
 */

class Rhino {

  protected void chew(List<String> input) {}

  protected CharSequence getName() {
    return "rhino";
  }
  protected String getColor() {
    return "grey, black, or white";
  }
}

class JavanRhino extends Rhino {

  /*
  you can override a method with generic parameters, but you must match the signature including the generic type exactly
   */
  protected void chew(List<String> input) {}

  /*
  they are considered overloaded methods, not overridden methods, because the signature is not the same.
  Type erasure does not change the fact that one of the method arguments is a List and the other is an ArrayList
   */
  //protected void chew(ArrayList<Double> input) {}

  public String getName() {
    return "javan rhino";
  }
  /*public CharSequence getColor() {  // DOES NOT COMPILE
    return "grey";
  }*/
}

/*
void sing1(List<?> v) {}                 // unbounded wildcard
void sing2(List<? super String> v) {}    // lower bounded wildcard
void sing3(List<? extends String> v) {}  // upper bounded wildcard
 */

/*
A hidden method occurs when a child class defines a static method with the same name and signature as an inherited static method defined in a parent class.
Put simply, it is method hiding if the two methods are marked static, and method overriding if they are not marked static.
If one is marked static and the other is not, the class will not compile.

By marking a method final, you forbid a child class from replacing this method.
This rule is in place both when you override a method and when you hide a method.
 */
class Bear {

  public final boolean hasFeathers() {
    return false;
  }
  public final static void flyAway() {}

  public static void sneeze() {
    System.out.println("Bear is sneezing");
  }
  public void hibernate() {
    System.out.println("Bear is hibernating");
  }
  public static void eat() {
    System.out.println("Bear is eating");
  }
}

class Panda extends Bear {
  /*
  public final boolean hasFeathers() {  // DOES NOT COMPILE
      return false;
   }
   public final static void flyAway() {}  // DOES NOT COMPILE
  public void sneeze() {           // DOES NOT COMPILE
      System.out.println("Panda sneezes quietly");
   }
  public static void hibernate() { // DOES NOT COMPILE
      System.out.println("Panda is going to sleep");
   }
   */
  public static void eat() {
    System.out.println("Panda is chewing");
  }
  public static void main(String[] args) {
    eat();
  }
}

/*
A hidden variable occurs when a child class defines a variable with the same name as an inherited variable defined in the parent class.
This creates two distinct copies of the variable within an instance of the child class: one instance defined in the parent class and one defined in the child class.
 */
class Carnivore {
  protected boolean hasFur = false;
}

class Meerkat extends Carnivore {
  protected boolean hasFur = true;

  public static void main(String[] args) {
    Meerkat m = new Meerkat();
    Carnivore c = m;
    System.out.println(m.hasFur);
    System.out.println(c.hasFur);
  }
}

/*
 Polymorphism enables an instance of A to be reassigned or passed to a method using one of its supertypes, such as B class or C interface.
Once the object has been assigned to a new reference type, only the methods and variables available to that reference type are callable on the object without an explicit cast.

 all objects are accessed by reference, so as a developer you never have direct access to the object itself.
 The type of the object determines which properties exist within the object in memory.
The type of the reference to the object determines which methods and variables are accessible to the Java program.

 Casting a reference from a subtype to a supertype doesn’t require an explicit cast.
Casting a reference from a supertype to a subtype requires an explicit cast.
The compiler disallows casts to an unrelated class.
At runtime, an invalid cast of a reference to an unrelated type results in a ClassCastException being thrown.

instanceof operator, which can be used to check whether an object belongs to a particular class or interface and to prevent ClassCastExceptions at runtime
 */