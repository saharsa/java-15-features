package com.java.annotations;

/*

Metadata is data that provides information about other data
The purpose of an annotation is to assign metadata attributes to classes, methods, variables, and other Java types.
annotations function a lot like interfaces
interfaces can be applied only to classes, annotations can be applied to any declaration including classes, methods, expressions, and even other annotations. Also, unlike interfaces, annotations allow us to pass a set of values where they are applied
annotations establish relationships that make it easier to manage data about our application
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Test {

}

enum Size {
  SMALL, MEDIUM, LARGE
}

class Bear {

}

/*
we use an annotation to create an annotation
marker annotation, when it does not contain any elements
the default value of an annotation must be a non‐ null constant expression
 */
@interface Exercise {
  int hoursPerDay(); // required
  int startHour() default 6; // not required
}

/*
annotation elements are implicitly abstract and public
 */
@interface Panda {
  // Integer height(); wrapper classes not supported
  // String[][] generateInfo() not supported
  Size size() default Size.LARGE;
  // Bear friendlyBear(); not supported
  Exercise exercise() default @Exercise(hoursPerDay = 2);

}


@Exercise(hoursPerDay = 3)
class Cheetah {

}

@Exercise(hoursPerDay = 1, startHour = 9)
class Sloth {

}

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface ElectricitySource {
  public int voltage();
  int MIN_VOLTAGE = 2;
  public static final int MAX_VOLTAGE = 18;
}

/*
Classes, interfaces, enums, and modules
Variables ( static, instance, local)
Methods and constructors
Method, constructor, and lambda parameters
Cast expressions
Other annotations
 */