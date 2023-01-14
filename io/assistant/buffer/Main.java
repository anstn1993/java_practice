package io.assistant.buffer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * 보조 스트림: 입출력 스트림에 보조적인 기능을 제공하는 스트림(자체적으로 입출력 기능을 가지진 않음)
 *
 * InputStreamReader: 바이트 입력 스트림을 문자 스트림으로 변환
 * BufferedInputStream, BufferedOutputStream, BufferedReader, BufferdWriter: 바이트/문자 입출력의 성능 향상
 * DataInputStream, DataOutputStream: 기본 타입 데이터 입출력
 * PrintStream, PrintWriter: 줄바꿈 처리나 형식화된 문자열을 출력
 * ObjectInputStream, ObjectOutputStream: 객체 입출력
 *
 */
public class Main {
  public static void main(String[] args) throws IOException {

    // 일반 스트림
    InputStream is = new FileInputStream(getPath("image.jpg"));
    OutputStream os = new FileOutputStream(getPath("image_copy.jpg"));
    long nonBufferTime = copy(is, os);
    System.out.println("일반 스트림 처리 시간: " + nonBufferTime);

    // buffered 스트림
    InputStream bis = new BufferedInputStream(new FileInputStream(getPath("image.jpg")));
    OutputStream bos = new BufferedOutputStream(new FileOutputStream(getPath("image_copy.jpg")));
    long bufferTime = copy(bis, bos);
    System.out.println("Buffered 스트림 처리 시간: " + bufferTime);

    // buffered 문자 스트림
    try(BufferedReader br = new BufferedReader(new FileReader(getPath("text.txt")))) {
      String str;
      while((str = br.readLine()) != null) {
        System.out.println(str);
      }
    }
  }

  private static long copy(InputStream is, OutputStream os) throws IOException {
    long start = System.nanoTime();

    int data;
    while ((data = is.read()) != -1) {
      os.write(data);
    }
    is.close();
    os.close();
    long end = System.nanoTime();
    return end - start;
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
