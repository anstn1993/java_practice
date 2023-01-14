package thread.join;

public class Main {

  public static void main(String[] args) {
    SumThread sumThread = new SumThread();
    sumThread.start();
    try {
      sumThread.join(); // sumThread의 실행이 종료될 때까지 메인 스레드는 일시정지(suspension) 상태로
    } catch (InterruptedException e) {
    }

    System.out.println(String.format("1~100까지의 합: %d",sumThread.getSum()));
  }
}
