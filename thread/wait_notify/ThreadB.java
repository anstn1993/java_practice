package thread.wait_notify;

public class ThreadB extends Thread {
  private WorkObject workObject;

  public ThreadB(WorkObject workObject) {
    setName("ThreadB");
    this.workObject = workObject;
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      workObject.methodB(); // 동기화 메서드 호출
    }
  }
}
