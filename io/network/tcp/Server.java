package io.network.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class Server {

  private static ServerSocket serverSocket;
  private static final int ROOM_CAPACITY = 10;
  private static final ExecutorService executorService = Executors.newFixedThreadPool(ROOM_CAPACITY);
  private static final Map<String, SocketCommunicator> room = Collections.synchronizedMap(new HashMap<>());

  // 메세지 타입
  private static final int PARTICIPATION = 1;
  private static final int PERMIT = 2;
  private static final int MESSAGE = 3;
  private static final int NOTIFICATION = 4;
  private static final int QUIT = 5;

  public static void main(String[] args) throws IOException {

    startSocketServer(); // 서버 시작

    Scanner sc = new Scanner(System.in);

    // q 입력하면 프로세스 종료
    while (true) {
      String key = sc.nextLine();
      if (key.equals("q")) {
        break;
      }
    }

    sc.close();
    closeSocketServer();
  }

  private static void startSocketServer() throws IOException {
    serverSocket = new ServerSocket(50000);// 50000번 포트에 바인드
    System.out.println("소켓 서버 구동 시작");
    printInetAddressInfo(serverSocket.getInetAddress());

    // 데몬 스레드로 클라이언트의 접속 처리
    Thread connectionProcessThread = new Thread(() -> {
      try {
        while (true) {
          Socket clientSocket = serverSocket.accept();
          // 연결이 완료되면 별도 스레드에게 통신 작업 위임
          SocketCommunicator communicator = new SocketCommunicator(clientSocket);
          executorService.execute(communicator);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    connectionProcessThread.setDaemon(true);
    connectionProcessThread.start();
  }

  private static void closeSocketServer() {
    try {
      serverSocket.close();
      executorService.shutdown();
      for (SocketCommunicator socketCommunicator : room.values()) {
        socketCommunicator.clientSocket.close();
      }
      System.out.println("소켓 서버 종료");
    } catch (IOException e) {
    }
  }

  public static class SocketCommunicator extends Thread {
    private Socket clientSocket;
    private String host;
    private String id;
    private DataInputStream is;
    private DataOutputStream os;

    public SocketCommunicator(Socket clientSocket) {
      this.clientSocket = clientSocket;
      InetSocketAddress remoteSocketAddress = (InetSocketAddress) clientSocket.getRemoteSocketAddress();
      this.host = remoteSocketAddress.getHostName();
      try {
        this.is = new DataInputStream(clientSocket.getInputStream());
        this.os = new DataOutputStream(clientSocket.getOutputStream());
      } catch (IOException e) {
      }
    }

    private void receive(String payload) {
      JSONObject jsonObject = new JSONObject(payload);
      int operation = jsonObject.getInt("operation");
      switch (operation) {
        case PARTICIPATION:
          participate(jsonObject.getString("name"));
          break;
        case MESSAGE:
          sendToAll(jsonObject.getString("message"));
          break;
        default:
          break;
      }
    }

    // 채팅 전송 처리
    private void sendToAll(String message) {
      for (String key : room.keySet()) {
        if (this.id.equals(key)) {
          continue;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("operation", MESSAGE);
        jsonObject.put("from", this.id);
        jsonObject.put("message", message);

        SocketCommunicator socketCommunicator = room.get(key);
        try {
          socketCommunicator.os.writeUTF(jsonObject.toString());
        } catch (IOException e) {
        }
      }
    }

    // 채팅방 입장 처리
    private void participate(String name) {
      this.id = String.format("%s@%s", name, host);
      // 채팅방에 추가
      room.put(id, this);

      printAlert(id + " 입장, 현재 참여인원: " + room.size());

      // 채팅방 참가자들에게 boradcast
      notifyToAll(id + " 님이 입장하셨습니다.");

    }

    private void notifyToAll(String message) {
      for (String key : room.keySet()) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("operation", NOTIFICATION);
        jsonObject.put("message", message);

        SocketCommunicator socketCommunicator = room.get(key);
        try {
          socketCommunicator.os.writeUTF(jsonObject.toString());
        } catch (IOException e) {
        }
      }
    }

    @Override
    public void run() {
      try {
        is = new DataInputStream(clientSocket.getInputStream());
        os = new DataOutputStream(clientSocket.getOutputStream());

        // 채팅방 입장 허가 응답 처리
        permit(os);

        while (true) {
          String payload = is.readUTF();
          receive(payload);
        }
      } catch (EOFException e) {
        closeClientSocket();
      } catch (SocketException e) {
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    private void closeClientSocket() {
      try {
        this.clientSocket.close();
      } catch (IOException e) {
      }
      room.remove(this.id);
      System.out.printf("<%s> 퇴장. 현재 참여인원: %s", this.id, room.size());
      System.out.println();
    }

    private void permit(DataOutputStream os) {
      try {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("operation", PERMIT);
        jsonObject.put("message", "채팅방 입장을 허가합니다. 채팅방에서 사용하실 이름을 입력하고 입장해주세요!");
        os.writeUTF(jsonObject.toString());
      } catch (IOException e) {
      }
    }
  }

  private static void printInetAddressInfo(InetAddress inetAddress) throws IOException {
    System.out.println("호스트명: " + inetAddress.getHostName());
    System.out.println("IP 주소: " + inetAddress.getHostAddress());
    System.out.println("접속 가능 여부: " + inetAddress.isReachable(1000));
    System.out.println("로컬 호스트 여부: " + inetAddress.isLoopbackAddress());
    System.out.println();
  }

  private static void printAlert(String message) {
    System.out.println("=====================================");
    System.out.println(message);
    System.out.println("=====================================");
  }
}
