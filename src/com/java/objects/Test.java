package com.java.objects;

import java.util.Objects;

public class Test {

}

class Obj {
  int i;

  public Obj(int i){
    this.i = i;
  }

  /*
  The toString() method is called when you try to print an object or concatenate the object with a String.
  It is commonly overridden with a version that prints a unique description of the instance using its instance fields.
   */
  @Override
  public String toString(){
    return ""+i;
  }

  /*
  used to compare objects, with the default implementation just using the == operator.
  You should override the equals(Object) method anytime you want to conveniently compare elements for equality, especially if this requires checking numerous fields.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Obj obj = (Obj) o;
    return i == obj.i;
  }

  /*
  Any time you override equals(Object), you must override hashCode() to be consistent. This means that for any two objects, if a.equals(b) is true, then a.hashCode()==b.hashCode() must also be true.
  If they are not consistent, then this could lead to invalid data and side effects in hash‐based collections such as HashMap and HashSet
   */
  @Override
  public int hashCode() {
    return Objects.hash(i);
  }
}
