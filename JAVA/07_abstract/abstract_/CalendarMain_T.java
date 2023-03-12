package abstract_;

import java.util.Calendar;
import java.util.Scanner;

class CalendarEx_T {
	private int year, month, week, lastDay;
	
	public CalendarEx_T() {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("년도 입력: ");
		this.year = scan.nextInt();
		
		System.out.print("월 입력: ");
		this.month = scan.nextInt();
		
		scan.close();
	}
	
	public void calc() {
		Calendar cal = Calendar.getInstance();
		// system date로 Calendar cal 저장

		cal.set(year, month-1, 1);
//		cal.set(Calendar.YEAR, this.year);
//		cal.set(Calendar.MONTH, this.month);
//		cal.set(Calendar.DAY_OF_MONTH, 1);

		week = cal.get(Calendar.DAY_OF_WEEK);
		// 입력된 월의 1일의 요일(일요일: 1)
		lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 해당 월 일자 중 가장 큰 값 반환
		
	}
	
	public void disply() {
		System.out.println("\n일\t월\t화\t수\t목\t금\t토");
		
		for(int i=1; i<week; i++) {System.out.print("\t");}
		
		for(int i=1; i<=lastDay; i++) {
			System.out.print(String.format("%02d", i) + "\t");
//			if((week+i-1)%7==0) {System.out.println();}
			if(week%7==0) {System.out.println();}
			week++;
		}
	}





}

public class CalendarMain_T {

	public static void main(String[] args) {
		CalendarEx_T clnd = new CalendarEx_T();
		clnd.calc();
		clnd.disply();
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
