package regexpr;

import java.util.regex.Pattern;

/**
 * 정규표현식
 * ================= 문자 표현 =================
 * 1. []: 한 개의 문자
 *  -[a-z]: 하나의 소문자 알파벳
 *  -[a-zA-z]: 하나의 대소문자 알파벳
 *  -[a-zA-z_0-9]: 한개의 대소문자 알파벳이나 숫자
 *  -[abc]: a, b, c 중 하나의 문자
 *  -[^abc]: a, b, c를 제외한 하나의 문자
 * 2. \d: 하나의 숫자([0-9]와 동일)
 * 3. \w: 하나의 문자 혹은 하나의 숫자([a-zA-z_0-9]와 동일)
 * 4. \s: 하나의 공백
 * 5. \.: .
 * ================= 개수나 패턴의 표현 =================
 * 1. ?: 하나 혹은 없음
 *  - [abc]?: a, b, c중 하나가 표현되거나 없을 수도 있음
 * 2. *: 하나 이상 혹은 없음
 *  - [abc]*: a, b, c중 하나 이상이 표현되거나 없을 수도 있음
 * 3. +: 하나 이상
 * 4. {n}: 정확히 n개
 *  - \d{3}: 정확히 3자리의 숫자
 * 5. {n,m}: n개부터 m개까지
 *  - \d{3,4}: 3자리 혹은 4자리 숫자
 * 6. a|b: a 혹은 b
 *  - \d|[abc]: 숫자 하나 혹은 a,b,c 중 하나
 * 7. (): 여러 문자 조합의 그룹핑
 *  - (\.\w+): .과 하나 이상의 문자 패턴으로 그룹핑
 *  - (\.\w+)+: .com, .co.kr, .a.b.c 등등이 가능
 */
public class Main {
  private static Pattern emailPattern = Pattern.compile("\\w+@\\w+\\.\\w+(\\.\\w+)?");
  // 02-123-4567 or 010-1234-5678
  private static Pattern telephoneNumberPattern = Pattern.compile("(02|010)-\\d{3,4}-\\d{4}");

  public static void main(String[] args) {
    String id = "0Angel1004";
    String regExp = "[a-zA-Z]\\w{7,11}";
    if(Pattern.matches(regExp, id)) {
      System.out.println("ID로 사용 가능");
    } else {
      System.out.println("ID로 사용 불가능");
    }

    String email = "anstn1993@gmail.com";
    if (emailPattern.matcher(email).matches()) {
      System.out.println("email 사용 가능");
    } else {
      System.out.println("email 사용 불가능");
    }

    String phoneNum = "010-1234-5678";
    if (telephoneNumberPattern.matcher(phoneNum).matches()) {
      System.out.println("전화번호 사용 가능");
    } else {
      System.out.println("전화번호 사용 불가능");
    }

  }

}
