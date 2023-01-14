package io.network.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import org.json.JSONObject;

public class Client {

  private static Socket socket;
  private static final Scanner sc = new Scanner(System.in);

  // 메세지 타입
  private static final int PARTICIPATION = 1;
  private static final int PERMIT = 2;
  private static final int MESSAGE = 3;
  private static final int NOTIFICATION = 4;
  private static final int QUIT = 5;

  public static void main(String[] args) {
    try {
      socket = new Socket("localhost", 50000);// 접속할 서버의 호스트, 포트정보로 소켓 생성 및 연결 시도

      // 메세지 수신 스레드 실행
      MessageReceiver messageReceiver = new MessageReceiver(socket);
      messageReceiver.setDaemon(true);
      messageReceiver.start();
      try {
        messageReceiver.join();
      } catch (InterruptedException e) {
      }
    } catch (UnknownHostException e) {
      // 호스트를 찾을 수 없을 때
      e.printStackTrace();
    } catch (IOException e) {
      // 서버 접속 실패시
      e.printStackTrace();
    }
  }

  public static class MessageSender extends Thread {
    private Socket socket;
    private DataOutputStream os;

    public MessageSender(Socket socket) {
      this.socket = socket;
      try {
        this.os = new DataOutputStream(socket.getOutputStream());
      } catch (IOException e) {
      }
    }

    @Override
    public void run() {
      try {
        while (true) {
          String message = sc.nextLine();
          JSONObject jsonObject = new JSONObject();
          jsonObject.put("operation", MESSAGE);
          jsonObject.put("message", message);
          os.writeUTF(jsonObject.toString());
          os.flush();
        }
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public static class MessageReceiver extends Thread {
    private Socket socket;
    private DataInputStream is;
    private DataOutputStream os;

    public MessageReceiver (Socket socket) {
      this.socket = socket;
      try {
        this.is = new DataInputStream(socket.getInputStream());
        this.os = new DataOutputStream(socket.getOutputStream());
      } catch (IOException e) {
      }
    }

    private void receive(String payload) {
      JSONObject jsonObject = new JSONObject(payload);
      int operation = jsonObject.getInt("operation");
      switch (operation) {
        case PERMIT:
          printAlert(jsonObject.getString("message"));
          participateChatRoom(operation);
          break;
        case MESSAGE:
          processForMessage(jsonObject.getString("from"), jsonObject.getString("message"));
          break;
        case NOTIFICATION:
          printAlert(jsonObject.getString("message"));
          break;
        default:
          break;
      }
    }

    private void processForMessage(String from, String message) {
      System.out.printf("<%s>: %s", from, message);
      System.out.println();
    }

    private void participateChatRoom(int operation) {
      System.out.print("이름 입력: ");
      String name = sc.nextLine();

      JSONObject jsonObject = new JSONObject();
      jsonObject.put("operation", PARTICIPATION);
      jsonObject.put("name", name);
      try {
        this.os.writeUTF(jsonObject.toString());
      } catch (IOException e) {
        closeSocket();
        System.out.println("채팅 서버에 정상적으로 접근할 수 없습니다. 다음에 다시 이용해주세요.");
        return;
      }

      // 메세지 전송 스레드 시작
      if (operation == PERMIT) {
        MessageSender messageSender = new MessageSender(socket);
        messageSender.setDaemon(true);
        messageSender.start();
      }
    }

    @Override
    public void run() {
      try {
        DataInputStream is = new DataInputStream(socket.getInputStream());
        while (true) {
          String payload = is.readUTF();
          receive(payload);
        }
      } catch (EOFException e) {
        System.out.println("서버와의 연결이 종료되었습니다.");
      } catch (SocketException e) {
        System.out.println("서버에서 소켓을 끊었습니다. 연결을 종료합니다.");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static void closeSocket() {
    try {
      socket.close();
    } catch (IOException e) {
    }
  }

  private static void printAlert(String message) {
    System.out.println("=====================================");
    System.out.println(message);
    System.out.println("=====================================");
  }
}
