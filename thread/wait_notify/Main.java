package thread.wait_notify;

public class Main {

  public static void main(String[] args) {
    WorkObject workObject = new WorkObject(); // 동기화를 위한 lock 객체 생성

    ThreadA threadA = new ThreadA(workObject);
    ThreadB threadB = new ThreadB(workObject);

    threadA.start();
    threadB.start();
  }

}
