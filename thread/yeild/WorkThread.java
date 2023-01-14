package thread.yeild;

public class WorkThread extends Thread {

  private boolean work = true;

  public void setWork(boolean work) {
    this.work = work;
  }

  public WorkThread(String name) {
    setName(name);
  }

  @Override
  public void run() {
    while (true) {
      if (work) {
        System.out.println(getName() + ": 작업처리");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else {
        Thread.yield(); // 자신은 실행 대기 상태로 빠지고 실행 대기 큐에 있는 다른 스레드에 실행 양보
      }
    }
  }
}
