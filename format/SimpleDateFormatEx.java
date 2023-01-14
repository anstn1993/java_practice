package format;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 날짜 정보를 원하는 형식의 문자열로 변환
 *
 * 1. y: 년
 * 2. M: 월
 * 3. d: 일
 * 4. D: 월 구분이 없는 일(1~365)
 * 5. E: 요일
 * 6. a: 오전/오후
 * 7. w: 년의 몇 번째 주
 * 8. W: 월의 몇 번째 주
 * 9. H: 시(0~23)
 * 10. h: 시(1~12)
 * 11. K: 시(0~11)
 * 12. k: 시(1~24)
 * 13. m: 분
 * 14. s: 초
 * 15. S: 밀리세컨드
 */
public class SimpleDateFormatEx {
  public static void main(String[] args) {
    Date now = new Date();
    System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(now));
    System.out.println(new SimpleDateFormat("yyyy년 MM월 dd일").format(now));
    System.out.println(new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss").format(now));
    System.out.println(new SimpleDateFormat("오늘은 E요일").format(now));
    System.out.println(new SimpleDateFormat("오늘은 올해의 D번째 날").format(now));
    System.out.println(new SimpleDateFormat("오늘은 이달의 d번째 날").format(now));
  }
}
