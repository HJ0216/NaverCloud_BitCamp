package for_;

import java.util.*;
import java.text.*;

public class For06 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Number less than Integer 10: ");
		int number = scan.nextInt();
		
		while(number>10) {
			System.out.println("Please enter the number less than Integer 10.");
			number = scan.nextInt();
		} // while
			
		int mul = 1;
		
		for(int i=number; i>0 ; i--) {
			mul *= i;
		} // for의 첫번째 parameter(매개변수)는 argument(전달인자)가 아닌 variable을 받고 있음
		  // Scanner로 입력받은 arg인 number를 그대로 사용 할 수 없으므로 number를 받는 새로운 변수 i를 선언해서 사용해야 함

//		for(int i = 1; i<=number; i++) {
//		mul *= i;
//		} // for	
		
		DecimalFormat df = new DecimalFormat();
		System.out.println(number + "! = " + df.format(mul)); // number!

		scan.close();
	}
	
}


/*
[문제] 팩토리얼을 구하시오 (for)
- 입력되는 숫자는 1 ~ 10 사이만 입력한다.

[실행 결과]
숫자 입력 : 3
3! = 6 (1*2*3)
--------------------
숫자 입력 : 5
5! = 120 (1*2*3*4*5)

 */