package io.network.hostinfo;

import java.io.IOException;
import java.net.InetAddress;

public class Main {

  public static void main(String[] args) throws IOException {
    InetAddress localHost = InetAddress.getLocalHost(); // 로컬 호스트 정보 조회
    printInetAddressInfo(localHost);

    InetAddress naver = InetAddress.getByName("naver.com");// DNS 리졸버를 통해서 입력받은 도메인 주소의 ip 주소 정보 반환
    printInetAddressInfo(naver);

    InetAddress[] navers = InetAddress.getAllByName("naver.com");
    for (InetAddress inetAddress : navers) {
      printInetAddressInfo(inetAddress);
    }
  }

  private static void printInetAddressInfo(InetAddress inetAddress) throws IOException {
    System.out.println("호스트명: " + inetAddress.getHostName());
    System.out.println("IP 주소: " + inetAddress.getHostAddress());
    System.out.println("접속 가능 여부: " + inetAddress.isReachable(1000));
    System.out.println("로컬 호스트 여부: " + inetAddress.isLoopbackAddress());
    System.out.println();
  }

}
