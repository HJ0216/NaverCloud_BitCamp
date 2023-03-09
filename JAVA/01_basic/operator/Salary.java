package operator;

import java.text.*; // DecimalFormat
import java.util.*; // scanner

public class Salary {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.print("Name: ");
		String name = scan.next(); // 키보드로부터 입력받아 money에 저장
//		String name = scan.nextLine();
		
		System.out.print("Position: ");
		String position = scan.next();
		
		System.out.print("Basic Salary: ");
		int bs = scan.nextInt();
		
		System.out.print("Benefit: ");
		int benefit = scan.nextInt();
		

		int total = bs + benefit;

		double tax  = (total>=5_000_000) ? total*0.03 : (total>=3_000_000) ? total*0.02 : total*0.01;
		// 연산결과가 반드시 int type이 반환되더라도, 과정에 실수가 있을 경우, 결과값은 반드시 실수가 됨
		// 1,000단위 구분 시, _ 활용
		
		DecimalFormat df = new DecimalFormat(); // DecimalFormat에 1,000단위 ',' 자체 내장
//		DecimalFormat df = new DecimalFormat("###,###");
		// #: 해당 위치의 숫자가 없을 경우, 공란 반환 (23 ### -> 23)
		// 0: 해당 위치의 숫자가 없을 경우, 0 반환 (23 000 -> 023)
		
		System.out.println("*** " + name + " " + position + " 월급 ***");
		System.out.println("기본급 : " + df.format(bs) + "원");
		System.out.println("수당 : " + df.format(benefit) + "원");
		System.out.println("합계 : " + df.format(total) + "원");
		System.out.println("세금 : " + df.format(tax) + "원");
		System.out.println("월급 : " + df.format(total-tax) + "원");

		
		scan.close();

	}

}


/*
[문제] 월급 계산 프로그램 - 조건 연산자
이름, 직급, 기본급, 수당을 입력하여 합계, 세금, 월급을 출력하시오.
단 합계가 5,000,000원 이상이면 3%
       3,000,000원 이상이면 2%
       아니면 1%
       
합계 = 기본급 + 수당
세금 = 합계 * 세율
월급 = 합계 - 세금

[실행결과]
이름 입력 : 홍길동
직급 입력 : 부장
기본급 입력 : 4900000
수당 입력 : 200000

*** 홍길동 부장 월급 ***
기본급 : 4,900,000원
수당 : 200,000원
합계 : 5,100,000원
세금 : 153,000원
월급 : 4,947,000원

 *
Logic
1. 입력
2. 입력받은 것을 출력
2.1. 기본급: format
2.2. 수당: 상동
2.3. 합계: 기본급 + 수당
2.4. 세금: 합계 -> 조건문
2.5. 월급: 합계 - 세금
 */
