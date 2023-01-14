package io.assistant.bytetochar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
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
  public static void main(String[] args) {
    // 문자 변환 스트림: OutputStreamWriter
    try (OutputStream os = new FileOutputStream(getPath("text.txt"));
      Writer w = new OutputStreamWriter(os)
    ) {
      w.write("문자 변환 스트림을 통해 문자열을 출력합니다.");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 문자 변환 스트림: InputStreamWriter
    try (InputStream is = new FileInputStream(getPath("text.txt"));
        Reader r = new InputStreamReader(is)
    ) {
      char[] chars = new char[10];
      int readCount;
      while ((readCount = r.read(chars)) != -1) {
        for (int i = 0; i < readCount; i++) {
          System.out.print(chars[i]);
        }
        System.out.println();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 성능 향상 스트림: BufferedInputStrea
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
