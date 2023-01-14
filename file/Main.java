package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class Main {

  public static void main(String[] args) throws IOException {
    File file1 = createFileIfNotExist("test1.txt");
    File file2 = createFileIfNotExist("test2.txt");
    File file3 = createFileIfNotExist("test3.txt");
    File file4 = createFileIfNotExist("test4.txt");

    printFileInfo(file1);
    printFileInfo(file2);
    printFileInfo(file3);
    printFileInfo(file4);

    File dir = new File(getDirectoryPath());
    File[] files = dir.listFiles(); // 디렉토리와 그 하위 디렉토리의 모든 파일들을 배열로 리턴
    if (files != null) {
      for (File file : files) {
        printFileInfo(file);
      }
    }
  }

  private static String getDirectoryPath() throws FileNotFoundException {
    URL resource = Main.class.getResource("");

    if (resource == null) {
      throw new FileNotFoundException();
    }
    System.out.println(resource.getPath());
    return resource.getPath();
  }

  private static File createFileIfNotExist(String fileName) throws IOException {
    String path = getDirectoryPath() + fileName;

    File file = new File(path);
    if(!file.exists()) {
      file.createNewFile();
    }
    return file;
  }

  private static void printFileInfo(File file) {
    System.out.printf("file name: %s\nfile length: %s\nread: %s, write: %s, execute: %s\nlast modified time: %s\n", file.getName(), file.length(), file.canRead(), file.canWrite(), file.canExecute(), new Date(file.lastModified()));
    System.out.println();
  }
}
