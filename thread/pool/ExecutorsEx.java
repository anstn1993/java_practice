package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorsEx {

  public static void main(String[] args) {

    /**
     * 생성자로 직접 생성
     */
     ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
         3, // 코어 스레드 개수(최소 유지 스레드 개수)
         100, // 최대 스레드 개수
         120L, // idle 허용 시간(120초동안 idle 상태면 풀에서 제거)
         TimeUnit.SECONDS, // idle 시간 단위
         new SynchronousQueue<>() // 작업 큐
     );

    /**
     * 초기 스레드 개수: 0
     * 코어 스레드 개수(최소 유지 스레드 개수): 0
     * 최대 스레드 개수: Integer.MAX_VALUE
     * 작업 요청이 풀의 스레드 개수를 초과하면 계속해서 새로운 스레드 생성
     * 스레드가 60초동안 idle상태면 풀에서 제거
     */
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    /**
     * 초기 스레드 개수: 0
     * 코어 스레드 개수(최소 유지 스레드 개수): 0
     * 최대 스레드 개수: 파라미터로 전달된 수
     * 작업 요청이 많아지면 최대 스레드 수까지 생성(아래의 예에서는 5개)
     * 생성된 스레드를 제거하지 않고 유지
     */
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);// 초기 스레드 0, 코어 스레드
  }
}
