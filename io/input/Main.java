package io.input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {
    // 1바이트씩 읽기
    try (InputStream is = new FileInputStream(getPath("test.txt"))) {
      int data;
      // 1바이트를 읽어서 int 변수로 반환(4바이트 중에 1바이트만 사용)
      while ((data = is.read()) != -1) {
        System.out.println(String.format("read data: %d", data));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 여러 바이트 한번에 읽기
    try (InputStream is = new FileInputStream(getPath("test2.txt"))) {
      byte[] bytes = new byte[4];
      int readByteCount;
      // 바이트 배열의 크기만큼 읽고 읽은 바이트 수를 반환
      while ((readByteCount = is.read(bytes)) != -1) {
        System.out.println(String.format("read data: %s", new String(bytes)));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Reader: 문자열 전용 스트림
    // 문자 하나씩 읽기
    try (Reader r = new FileReader(getPath("text.txt"))) {
      int data;
      while ((data = r.read()) != -1) {
        System.out.print((char) data);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    try (Reader r = new FileReader(getPath("text.txt"))) {
      // 여러 문자 한번에 읽기
      char[] chars = new char[3];
      int readCount;
      while ((readCount = r.read(chars)) != -1) {
        for (int i = 0; i < readCount; i++) {
          System.out.print(chars[i]);
        }
        System.out.println();
      }
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
