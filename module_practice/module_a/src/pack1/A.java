package pack1;

import pack2.B;
import pack3.C;

public class A {
  public void method() {
    System.out.println("method A 호출");
    B b = new B();
    b.method();
  }

  public C getC() {
    return new C();
  }
}
