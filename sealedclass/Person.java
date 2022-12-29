package sealedclass;

public sealed class Person permits Employee, Manager {
    public void work() {
        System.out.println("사람이 일을 합니다.");
    }
}