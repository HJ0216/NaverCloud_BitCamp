package abstract_;

import java.util.*;
import java.text.*;

public class Today {

	public static void main(String[] args) throws ParseException {
		// ParseException 예방 -> Compile Error 예방
		Date date = new Date(); // new를 통한 시간 저장
		System.out.println("오늘 날짜: " + date);
		
		SimpleDateFormat sdf = new SimpleDateFormat("y년 MM월 dd일 E요일 HH:mm:ss");
		System.out.println("오늘 날짜: " + sdf.format(date));
		
		// 입력: 내 생일
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
		Date birth = sdf2.parse("19910716091415"); // String -> Date 형변환
		System.out.println("BirthDay: " + birth);
		System.out.println("BirthDay: " + sdf.format(birth));
		
//		Calendar cal = new Calendar(); // Cannot instantiate the type Calendar
		// Calendar class: abstract class
		
		// How to deal with Abstract class: Using SubClass
		Calendar cal = new GregorianCalendar();

		// How to deal with Abstract class:  Using Method
		Calendar cal2 = Calendar.getInstance(); // static
		
		int year = cal.get(Calendar.YEAR); // Capital Variable: static final, 상수화
		// 동일: int year = cal.get(1);
		int month = cal.get(Calendar.MONTH) + 1; // Capital Variable: static final, 상수화
		// JANUARY which is 0
		// 동일: i
		int day = cal.get(cal.DAY_OF_MONTH); // Capital Variable: static final, 상수화
		// The static field Calendar.DAY_OF_MONTH should be accessed in a static way
		int week = cal.get(cal.DAY_OF_WEEK);
		// Sunday = 1;
		
		String dayOfWeek=null;
		// lv, 초기값 설정 필요
		switch(week) {
		case 1 : dayOfWeek = "일"; break; // break 미입력 시, 모든 case문 실행
		case 2 : dayOfWeek = "월"; break;
		case 3 : dayOfWeek = "화"; break;
		case 4 : dayOfWeek = "수"; break;
		case 5 : dayOfWeek = "목"; break;
		case 6 : dayOfWeek = "금"; break;
		case 7 : dayOfWeek = "토"; break;
		}
		
		int hour = cal.get(Calendar.HOUR_OF_DAY); // 12시간제: HOUR, 24시간제: HOUR_OF_DAY
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		System.out.print(year + "년 " + month + "월 " + day + "일 " + dayOfWeek + "요일 ");
		System.out.println(hour + "시 " + minute + "분 " + second + "초");
	}
	
}
