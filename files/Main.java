package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Files는 운영체제의 파일시스템에게 작업을 위임하여 파일에 대한 제어를 할 수 있는 다양한 정적 메서드를 제공하는 편리한 클래스다.
 */
public class Main {

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path directoryPath = getDirectoryPath();
    Path filePath = Paths.get(directoryPath.toString(), "test.txt");
    if (Files.notExists(filePath)) { // 파일이 존재하지 않으면
      Files.createFile(filePath); // 파일 생성
    }

    Files.writeString(filePath, "I'm mansoo!"); // 파일에 문자열 쓰기

    System.out.println("파일 사이즈: " + Files.size(filePath) + " bytes");
    System.out.println("파일 유형: " + Files.probeContentType(filePath));
    System.out.println(Files.readString(filePath));

    Files.deleteIfExists(filePath); // 삭제처리

  }

  private static Path getDirectoryPath() throws FileNotFoundException, URISyntaxException {
    URL resource = Main.class.getResource("");

    if (resource == null) {
      throw new FileNotFoundException();
    }
    System.out.println(resource.getPath());
    return Paths.get(resource.toURI());
  }

  private static void printFileInfo(File file) {
    System.out.printf("file name: %s\nfile length: %s\nread: %s, write: %s, execute: %s\nlast modified time: %s\n", file.getName(), file.length(), file.canRead(), file.canWrite(), file.canExecute(), new Date(file.lastModified()));
    System.out.println();
  }
}
