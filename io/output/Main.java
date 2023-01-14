package io.output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URL;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {
    // 바이트 단위 출력
    try (OutputStream os = new FileOutputStream(getPath("test.txt"))) {
      byte a = 10;
      os.write(a);
      os.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 바이트 배열 단위 출력
    try (OutputStream os = new FileOutputStream(getPath("test2.txt"))) {
      byte[] bytes = {10, 20, 30};
      os.write(bytes);
      os.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 바이트 배열의 원하는 영역만 출력
    try (OutputStream os = new FileOutputStream(getPath("test3.txt"))) {
      byte[] bytes = {10, 20, 30, 40, 50};
      os.write(bytes, 1, 3); // 1번 인덱스부터 3개의 바이트만 출력(20, 30, 40)
      os.flush();
    } catch (IOException e) {
     e.printStackTrace();
    }

    // Writer: 문자열 전용 스트림
    try (Writer w = new FileWriter(getPath("text.txt"))) {
      // 문자 하나씩 쓰기
      char a = 'a';
      char b = 'b';
      char c = 'c';
      w.write(a);
      w.write(b);
      w.write(c);

      // 여러 문자 쓰기
      char[] chars = { 'd', 'e', 'f'};
      w.write(chars);

      // 문자열 쓰기
      w.write("ghijklmn");

    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  private static String getPath(String fileName) throws FileNotFoundException {
    URL resource = Main.class.getResource(fileName);

    if (resource == null) {
      throw new FileNotFoundException();
    }
    System.out.println(resource.getPath());
    return resource.getPath();
  }
}
