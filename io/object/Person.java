package io.object;

import java.io.Serial;
import java.io.Serializable;

public class Person implements Serializable {

  // 직렬화된 객체의 필드와 역직렬화의 대상이 되는 클래스의 필드 정보가 달라도 역직렬화가 가능하게 버전 정보로 동등성 여부 판단
  @Serial
  private static final long serialVersionUID = -622284561026719240L;

  public Person(int age, String name) {
    this.age = age;
    this.name = name;
  }

  private int age;
  private String name;

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Person{" +
        "age=" + age +
        ", name='" + name + '\'' +
        '}';
  }
}
