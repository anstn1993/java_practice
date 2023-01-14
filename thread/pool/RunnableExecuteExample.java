package thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableExecuteExample {
  public static void main(String[] args) {
    // 스레드 풀을 이용하여 관리자 계정으로 100명의 유저에게 이메일 전송 처리
    List<Email> emails = new ArrayList<>();
    for (int i = 1; i < 101; i++) {
      emails.add(new Email("admin@me.com", "member" + i + "@me.com", "이벤트가 런칭되었습니다."));
    }

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    for (Email email : emails) {
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          email.send();
        }
      });
    }

    executorService.shutdown(); // 작업이 다 완료된 후에 종료
  }
}
