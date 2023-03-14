package exception;

import java.util.Scanner;

public class ExceptionMain {
	
	public static void main(String[] args) {
		if(args.length>1) {
			System.out.println(args[0]);
			System.out.println(args[1]);			
		} // Logic 구현을 위한 IndexOutOfBoundsException 방지
		
		Scanner scan = new Scanner(System.in);
		
		try {
			int num1 = Integer.parseInt(args[0]);
			// NumberFormatException
			// args[0] = "55", args[]="딸기" // 딸기는 parseInt 할 수 없음
			
			System.out.print("Enter the Number: ");
			int num2 = scan.nextInt();
			
			System.out.println(num1 + " / " + num2 + " = " + (num1/num2));
			
		} catch(NumberFormatException e) {
			System.out.println("Please Entered the numberFormat");
			e.printStackTrace();
		} catch(ArithmeticException e) {
			System.out.println("Cannot be divided by zero");
			e.printStackTrace();
		} finally { // error 발생 여부에 상관없이 무조건 실행되어질 명령어
			System.out.println("Execute on regardless of Error");
		}
		
		scan.close();
	}
	
}
