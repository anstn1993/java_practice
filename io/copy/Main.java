package io.copy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Main {

  public static void main(String[] args) {
    try(InputStream is = new FileInputStream(getPath("image.jpg"));
        OutputStream os = new FileOutputStream(getPath("image_copy.jpg"))) {

      byte[] bytes = new byte[1024];
      int readByteCount;
      // file read
      while ((readByteCount = is.read(bytes)) != -1) {
        // file write
        os.write(bytes);
      }
      os.flush();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }


    try(InputStream is = new FileInputStream(getPath("image.jpg"));
        OutputStream os = new FileOutputStream(getPath("image_copy.jpg"))
    ) {
      is.transferTo(os);// java 9
    } catch (FileNotFoundException e) {
      e.printStackTrace();
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
