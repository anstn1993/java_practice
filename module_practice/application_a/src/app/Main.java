package app;

import pack1.A;
import pack3.C;

public class Main {
  public static void main(String[] args) {
    A a = new A();

    a.method();
    a.method();

    C c = a.getC();
    c.method();
  }
}
