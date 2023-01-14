package thread.pool;

public record Email(String from, String to, String content) {
  public void send() {
    Thread thread = Thread.currentThread();
    System.out.println(String.format("[%s] %s => %s: %s", thread.getName(), from, to, content));
  }
}
