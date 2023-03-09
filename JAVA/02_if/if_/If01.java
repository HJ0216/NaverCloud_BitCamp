package if_; // if는 지정어라서 pkg_name 그대로 사용할 수 X

import java.util.*;

public class If01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.print("Please enter the number: ");
		int a = scan.nextInt();
		
				
		if(a >= 50) System.out.println(a + " is greater than 50.");
		System.out.println(a + " is smaller than 50."); // true일 경우에도 같이 출력 됨
		System.out.println();
		
		if(false) // 실행 X
			if(true) System.out.print("A");
			else System.out.print("B"); // 가장 근처에 있는 if와 짝을 이룸
		System.out.print("C");
		System.out.println("");
		// C
		
		if(true)
			if(true) System.out.print("A");
			else System.out.print("->B"); // 가장 근처에 있는 if와 짝을 이룸
		System.out.print("->C");
		System.out.println("");
		// A -> C
		
		if(true)
			if(false) System.out.print("A");
			else System.out.print("B"); // 가장 근처에 있는 if와 짝을 이룸
		System.out.print("->C");
		System.out.println("");
		// B -> C
		
		
		// 다중 if
		if(a>='A' && a<='Z') // 65-90
			System.out.println((char)a + " is Capital.");
		else if(a>='a' && a<='z') // 97-122
			System.out.println((char)a + " is small letter.");
		else
			System.out.println((char)a + " is number or special letter.");
		
		
		scan.close();
		
	}
	
}
