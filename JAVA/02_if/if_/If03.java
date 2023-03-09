package if_;

import java.util.Scanner;

public class If03 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("The score of subject a: ");
		int a = scan.nextInt();
		
		System.out.println("The score of subject b: ");
		int b = scan.nextInt();

		System.out.println("The score of subject c: ");
		int c = scan.nextInt();

		scan.close();
		
		
		if(a>=b && a>=c) {
			if(b>=c) System.out.println(c + " " + b + " " + a);
			else System.out.println(b + " " + c + " " + a);
		}
		
		else if(b>=a && b>=c) { // 등호가 겹쳐도 interpreter방식으로 선제조건에서 먼저 계산됨
			if(a>=c) System.out.println(c + " " + a + " " + b);
			else System.out.println(a + " " + c + " " + b);
		}
		
//		else if(c>a && c>b) {
//			if(a>=b) System.out.println(b + " " + a + " " + c);
//			else System.out.println(a + " " + b + " " + c);
//		}
		
		// code 간결하게 작성하기 -> 잔여 부분은 작성 X
		else {
			if(a>=b) System.out.println(b + " " + a + " " + c);
			else System.out.println(a + " " + b + " " + c);
		}
		
	}
}

/*
[문제] 3개의 숫자(a,b,c)를 입력받아서 순서대로 출력하시오 (if문 사용하시오)

[실행결과]
a의 값 : 98
b의 값 : 90
c의 값 : 85

85 90 98
---------------------
a의 값 : 75
b의 값 : 25
c의 값 : 36

25 36 75

1. a가 b와 c보다 크다면
2. (다중) b가 c보다 크다면
 */
