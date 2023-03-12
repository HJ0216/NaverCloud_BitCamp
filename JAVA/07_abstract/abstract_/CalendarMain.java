package abstract_;

import java.util.Scanner;
import java.util.Calendar;

class CalendarEx {
	private int year;
	private int month;
	private int START_DAY_OF_WEEK;
	private int END_DAY;
	
	Scanner scan = new Scanner(System.in);
	
	public CalendarEx() {
		System.out.print("년도 입력: ");
		year = scan.nextInt();
		
		System.out.print("월 입력: ");
		month = scan.nextInt();
		
	} // calendar()
	
	public void calc() {
		// Abstract class Method 구현
		Calendar sDay = Calendar.getInstance();
		Calendar eDay = Calendar.getInstance();

		sDay.set(year, month-1, 1); // 입력된 월 1일
		eDay.set(year, month, 1); // 입력된 월의 다음 달 1일
		eDay.add(Calendar.DATE, -1); // 입력된 월의 다음 달 1일 -1일 = 입력된 월의 마지막 날
		
		START_DAY_OF_WEEK = sDay.get(Calendar.DAY_OF_WEEK);
		// 입력된 월의 1일의 요일(일요일: 1)
//		System.out.println("START_DAY_OF_WEEK: " + START_DAY_OF_WEEK);

		END_DAY = eDay.get(Calendar.DATE);
//		System.out.println("END_DAY: " + END_DAY);
	} // calc()

	public void display() {
		System.out.println("\n일\t월\t화\t수\t목\t금\t토");
		
		for(int i=1; i < START_DAY_OF_WEEK; i++) {
			System.out.print("\t");
		} // for(): 빈칸 생성
		
		for(int i=1, n= START_DAY_OF_WEEK; i <= END_DAY; i++, n++) {
			System.out.print((i < 10)? " " + i + "\t" : i + "\t");
			if(n%7 == 0) System.out.println();
		}
		
	}
	
	
	
	
}

public class CalendarMain {

	public static void main(String[] args) {
		CalendarEx calEx = new CalendarEx();
		calEx.calc();
		calEx.display();
	}
	
}
/*
[문제] 만년달력
- 년도, 월을 입력하여 달력을 작성하시오
        
클래스명 : CalendarEx
필드 : 
기본 생성자 : 월, 일을 입력
메소드 calc()    : 매달 1일의 요일이 무엇인지? (Calendar에 메소드 준비)
                   매달 마지막이 28, 29, 30, 31 무엇인지? (Calendar에 메소드 준비)
       display() : 출력

클래스명 : CalendarMain

[실행결과]
년도 입력 : 2002
월 입력 : 10   

일   월   화   수   목   금   토
           1    2    3    4    5
 6    7    8    9   10   11   12
13   14   15   16   17   18   19
20   21   22   23   24   25   26
27   28   29   30   31
 */
