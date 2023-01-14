package io.assistant.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

  public static void main(String[] args) throws FileNotFoundException {
    // 데이터 타입의 크기가 다르기 때문에 출력 순서와 읽기 순서를 동일하게 가져가야 한다.
    try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(getPath("test.txt")));
        DataInputStream dis = new DataInputStream(new FileInputStream(getPath("test.txt")))) {
      // 기본 타입 출력
      dos.writeUTF("김문수");
      dos.writeDouble(100.0);
      dos.writeInt(1);
      dos.writeBoolean(true);

      dos.flush();

      // 기본타입 읽기
      System.out.println(dis.readUTF());
      System.out.println(dis.readDouble());
      System.out.println(dis.readInt());
      System.out.println(dis.readBoolean());
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
