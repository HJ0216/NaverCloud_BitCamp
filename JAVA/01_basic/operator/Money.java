package operator;

import java.text.*;
import java.util.*;

public class Money {

	public static void main(String[] args) {
		int money;
		// 변수를 먼저 선언하여 전역적으로 해당 변수를 사용할 수 있도록 함
		
		Scanner scan = new Scanner(System.in);
		// 키보드로부터 입력받는 scanner class type variable(obj) scan 생성
		// 콘솔과 관련된 Class: System
		// 입력(in), 출력(out)
		// System.in: static method
		System.out.print("Input: ");
		money = scan.nextInt(); // 키보드로부터 입력받아 money에 저장
		
		int a = money/1000;
		int money_tmp = money%1000;
		
		int b = money_tmp/100;
		money_tmp = money_tmp%100;
		
		int c = money_tmp/10;
		money_tmp = money_tmp%10;

		int d = money_tmp;
		
		
		DecimalFormat df = new DecimalFormat();
		
		System.out.println("현금 : " + df.format(money) + "원");
		System.out.println("천원 : " + a + "장");
		System.out.println("백원 : " + b + "개");
		System.out.println("십원 : " + c + "개");
		System.out.println("일원 : " + d + "개");


		// 상이한 variable 사용
		int money2 = 5378;
		int money_1000 = money2%1000;
		int money_100 = money_1000%100;
		int money_10 = money_100%10;
		
		int m = money/1000;
		int n = money_1000/100;
		int l = money_100/10;
		int o = money_10;
		

		System.out.println("천원 : " + m + "장");
		System.out.println("백원 : " + n + "개");
		System.out.println("십원 : " + l + "개");
		System.out.println("일원 : " + o + "개");
		
		
		scan.close();
	}
	
}

/*
[문제] 현금 교환기: 5378원

[실행 결과]
현금: 5378원
천원: 5개
백원: 3개
십원: 7개
일원: 8개
*/