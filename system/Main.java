package system;

import static java.lang.System.*;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class Main {
  public static void main(String[] args) throws IOException {

    // in 필드: 키보드 입력
    int read = in.read();

    // out 필드: 모니터에 출력
    out.println("standard output");
    // err 모니터에 에러 출려
    err.println("error");

    // 현재 시간을 밀리초 단위로 반환
    currentTimeMillis();

    // 현재 시간을 나노초 단위로 반환
    nanoTime();

    // 운영체제 및 사용자 정보 조회
    Properties properties = getProperties();
    for (Object key : properties.keySet()) {
      out.println(String.format("key: %s, value: %s", key, properties.get(key)));
    }

    // 운영체제 환경 변수 조회
    Map<String, String> envInfo = getenv();
    for (String key : envInfo.keySet()) {
      out.println(String.format("key: %s, value: %s", key, envInfo.get(key)));
    }

    // 프로세스 종료
    exit(0);
  }
}
