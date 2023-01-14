package thread.yeild;

public class Main {
  public static void main(String[] args) {
    WorkThread workThreadA = new WorkThread("workThreadA");
    WorkThread workThreadB = new WorkThread("workThreadB");
    workThreadA.start();
    workThreadB.start();

    // 5초 후에 workThreadA는 yield를 호출하게 함으로써 계속 실행 대기 상태로 빠지게 유도 -> workThreadB가 더 많은 코어 자원 획득
    try {
      Thread.sleep(5000);
      System.out.println("5초 경과!");
    } catch (InterruptedException e) {}
    workThreadA.setWork(false);

    // 10초 후에는 다시 workThreadA도 실행되게끔 유도
    try {
      Thread.sleep(10000);
      System.out.println("10초 경과!");
    } catch (InterruptedException e) {}
    workThreadA.setWork(true);


  }
}
