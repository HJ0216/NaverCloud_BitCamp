package operator;

import java.util.*; // Scanner

public class Operator01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the score: ");
		int score = scan.nextInt();
		
//		score>=80 && score<=100 ? "합격" : "불합격"; -> 결과값 관련 최종 method 필요
		
		String result = score>=80 && score<=100 ? "합격" : "불합격"; // 1. 변수 활용
		System.out.println(result);
		
		System.out.println(score>=80 && score<=100 ? "합격" : "불합격"); // 2. print 활용
		
		
		scan.close();
	}
	
}
