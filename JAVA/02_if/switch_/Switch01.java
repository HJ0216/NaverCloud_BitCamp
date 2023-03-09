package switch_;

import java.util.*;

public class Switch01 {

	public static void main(String[] args) {
		int days;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please Enter the Month.");
		int month = scan.nextInt();
		
		switch(month) {
		case 2 : days = 28; break;

		case 1 :
		case 3 :
		case 5 :
		case 7 :
		case 8 :
		case 10 :
		case 12 : days = 31; break;
		
		case 4 :
		case 6 :
		case 9 :
		case 11 : days = 30; break;

		default: days = 0; // break 문이 없을 경우, 최종값이 반환값이 되는 문제 발생
		}
		
		System.out.println(month + "월은 " + days + "일 입니다.");
		// The local variable days may not have been initialized.
		// lv의 경우, initialized 필요 -> case에 해당하지 않는 month일 경우, days가 초기화되지 않음
		// 1. int days = 0; Initialized 진행
		// 2. switch -> default 진행
		
		
		scan.close();
	}
	
}
