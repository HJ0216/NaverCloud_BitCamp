package switch_;

import java.text.DecimalFormat;
import java.util.*;

public class Switch02 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a, b;
		String op;
		
		System.out.println("Please Enter the Integer(a): ");
		a = scan.nextInt();
		
		System.out.println("Please Enter the Integer(b): ");
		b = scan.nextInt();
		
		System.out.println("Please Enter the Operator: ");
		op = scan.next();
		
		
		int sum = a + b;
		int sub = a - b;
		int mul = a * b;
		double div = (double) a / b;
		// a, b값을 입력받고 sum 등 변수가 선언되어야 함
		// 아닐 경우, Not initialized 문제 발생
		
		DecimalFormat df = new DecimalFormat("0.00");

		
		switch(op) {
		// op: 실수형 변수 불가
		// switch case 값: 실수, 변수, 수식 사용 불가
		case "+" : System.out.println(a + " " + op + " " + b + " = " + (sum)); break;
		case "-" : System.out.println(a + " " + op + " " + b + " = " + (sub)); break;
		case "*" : System.out.println(a + " " + op + " " + b + " = " + (mul)); break;
		case "/" : System.out.println(a + " " + op + " " + b + " = " + df.format(div)); break;
		default : System.out.println("Operator Error");
				
		} // switch


		scan.close();
		
	}
	
}


/*
[문제] 2개의 정수형 숫자와 연산자(+,-,*,/)를 입력하여 계산하시오.
1. a값 입력받기
2. 연산자 입력받기(타입)
3. 출력값 형식 맞추기
4. Error 예외 처리


[실행결과]
a의 값 : 25
b의 값 : 36
연산자(+,-,*,/)를 입력 : +

25 + 36 = xx

a의 값 : 25
b의 값 : 36
연산자(+,-,*,/)를 입력 : /

25 / 36 = 0.6944444444444444

a의 값 : 25
b의 값 : 36
연산자(+,-,*,/)를 입력 : #

연산자 error


 */
