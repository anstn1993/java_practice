package switchexpressions;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String grade = sc.nextLine();
        
        // 바로 변수에 대입 가능(Java 12부터)
        int score = switch(grade) {
            
            case "A" -> 100;
            // 중괄호를 사용할 경우 yield 키워드 필요(java 13부터)
            case "B" -> {
                int result = 100 - 20;
                yield result;
            }
            default -> 60;
        };
        System.out.println("sroce is " + score);
    }    
}

