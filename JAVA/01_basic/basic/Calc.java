package basic;

public class Calc {

	public static void main(String[] args) {
	int a, b;
	a = 320;
	b = 258;
	
	int sum = a + b;
	int sub = a - b;
	int mul = a * b;
	double div = (a / b);
//	double div = (a*1.0 / b); // 정수간의 연산은 정수까지만 연산됨
//	double div = ((double) a / b);
	
	System.out.println("320 + 258 = " + sum);
	System.out.println("320 - 258 = " + sub);
	System.out.println("320 * 258 = " + mul);
	System.out.println("320 / 258 = " + String.format("%.2f", div));
	// format() -> String class의 static method
	
	}

}

/*
[문제] a = 320, b = 258을 변수에 저장하여 합(sum), 차(sub), 곱(mul), 몫(div) 구하기
(단, 소수이하 2째자리까지 출력)

[실행 결과]
320 + 258 = 578
320 - 258 = 62
320 * 258 = 82560
320 / 258 = 1.24

*/
