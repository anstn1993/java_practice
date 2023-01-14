package thread.wait_notify;

public class WorkObject {
  public synchronized void methodA() {
    Thread thread = Thread.currentThread();
    System.out.println(thread.getName() + ": methodA 작업 실행");
    notify(); // 일시 정지 상태에 있는 스레드들 중 하나를 실행 대기 상태로 깨움
    try {
      wait(); // 자기 자신은 일시 정지 상태로
    } catch (InterruptedException e) {
    }
  }

  public synchronized void methodB() {
    Thread thread = Thread.currentThread();
    System.out.println(thread.getName() + ": methodB 작업 실행");
    notify(); // 일시 정지 상태에 있는 스레드들 중 하나를 실행 대기 상태로 깨움
    try {
      wait(); // 자기 자신은 일시 정지 상태로
    } catch (InterruptedException e) {
    }
  }
}
