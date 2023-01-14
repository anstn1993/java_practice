package io.object;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Member;
import java.net.URL;
import java.util.Arrays;

/**
 * 보조 스트림: 입출력 스트림에 보조적인 기능을 제공하는 스트림(자체적으로 입출력 기능을 가지진 않음)
 * <p>
 * InputStreamReader: 바이트 입력 스트림을 문자 스트림으로 변환 BufferedInputStream, BufferedOutputStream, BufferedReader, BufferdWriter:
 * 바이트/문자 입출력의 성능 향상 DataInputStream, DataOutputStream: 기본 타입 데이터 입출력 PrintStream, PrintWriter: 줄바꿈 처리나 형식화된 문자열을 출력
 * ObjectInputStream, ObjectOutputStream: 객체 입출력
 */
public class Main {

  public static void main(String[] args) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getPath("object.dat")));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getPath("object.dat")))) {
      Person person = new Person(31, "김문수");
      int[] array = {1, 2, 3};

      oos.writeObject(person);
      oos.writeObject(array);
      oos.flush();

      Person deserializedPerson = (Person) ois.readObject();
      int[] deserializedArray = (int[]) ois.readObject();

      System.out.println(deserializedPerson);
      System.out.println(Arrays.toString(deserializedArray));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
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
