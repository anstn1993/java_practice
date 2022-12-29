package sealedclass;

/*
 * non-sealed를 사용하면 부모 클래스가 sealed 클래스여도
 * 다시 상속이 가능해짐(즉, Manager가 부모 클래스가 될 수 있음)
 */
public non-sealed class Manager extends Person {
    @Override
    public void work() {
        System.out.println("생산 관리를 합니다.");
    }
}