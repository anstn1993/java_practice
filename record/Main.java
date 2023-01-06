package record;

public class Main {
  public static void main(String[] args) {
    // Required Args Constructor 생성
    Person mansoo = new Person("ManSoo", 31);
    // Getter 생성
    mansoo.name();
    mansoo.age();

    // equals, hashCode, toString 재정의
    Person mansooClone = new Person(mansoo.name(), mansoo.age());
    if (mansoo.equals(mansooClone)) {
      System.out.println("두 사람은 동등하다");
    }

    if (mansoo.hashCode() == mansooClone.hashCode()) {
      System.out.println("두 사람의 hash code는 동일하다");
    }

    System.out.println(mansoo.toString());
    System.out.println(mansooClone.toString());
  }
}
