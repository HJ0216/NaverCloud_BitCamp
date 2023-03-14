package exception;

import java.util.Scanner;

public class ExceptionMain2 {
	private int x;
	private int y;
	
	Scanner scan = new Scanner(System.in);
	
	public void input() {
		System.out.print("x 입력: ");
		x = scan.nextInt();

		System.out.print("y 입력: ");
		y = scan.nextInt();
	} // input()

		
	public void output() {
		if(y>=0) {
			int result=1;
			// multiply default: 1

			for(int i=0; i<y; i++) {
				result *= x;
			} System.out.println(x + "의 " + y + "승은 " + result);			
		} else {
//			System.out.println("y should be at least 0.");
			try {throw new Exception("y should be at least 0.");
			// throw / throws
			} catch(Exception e) {e.printStackTrace();}
			// 프로그램 실행에 문제는 없지만 의도하지 않은 결과가 나올 경우, Error Message 전달
			// 2의 (-1) != (1/2)

		}
		
	} // output()
	
	
	public static void main(String[] args) {
		ExceptionMain2 exceptionMain2 = new ExceptionMain2();
		exceptionMain2.input();
		exceptionMain2.output();
	}
	
}


/*
[문제] 제곱 연산
- x의 y승을 처리 한다.
- for문 이용

[실행결과]
x 입력 : 2
y 입력 : 10           input()
----------------------------------
2의 10승은 xxx        output()
*/
