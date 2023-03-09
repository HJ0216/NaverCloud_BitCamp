package operator;

import java.util.*;

public class Operator02 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please Enter the Number: ");
		int num = scan.nextInt(); // int가 아닌 값 입력 시, InputMismatchException 발생

		String result = num%2==0 ? "Even Number" : "Odd Number";
		// 홀수: num%2!=0 num%2==1
		System.out.println(result);
		
		String result2 = num%2==0 && num%3==0 ? "Common Multiple" : "Not Common multiple";
		System.out.println(result2);
		
		
		scan.close(); // scanner 사용 후, close
	}
	
}
