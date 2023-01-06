package record;

/**
 * DTO에서 반복적으로 작성되는 코드를 모두 포함하여 클래스 생성
 *
 * 1. private final 필드 생성
 * 2. 생성자 및 Getter 메서드 추가
 * 3. hashCode, equals, toString 메서드 오버라이드
 */
public record Person(String name, int age) {
}
