package for_;

import java.util.*;

public class AddGame {

	public static void main(String[] args) {
		int a, b, answer;
		int count = 0;
		
		Scanner scan = new Scanner(System.in);
		
		for(int i = 0; i<5; i++) {
			a = (int)(Math.random()*90)+10; // 10~99
			b = (int)(Math.random()*90)+10; // 10~99
			
			System.out.print("[" + (i+1) + "]" + " " + a + " + " + b + " = ");
			answer = scan.nextInt();

			if(answer==(a+b)) {
				System.out.println("Correct:)");
				count++;
			} else {
				System.out.println("Wrong:(");
				System.out.print("[" + (i+1) + "]" + " " + a + " + " + b + " = ");
				answer = scan.nextInt();
					if(answer==(a+b)) {
						System.out.println("Correct:)");
						count++;
					} else {
						System.out.println("Wrong:(, The answer is " + (a+b) + ".");						
					}
			}
			// if-else문에서 true일 때 여러 조건을 수행하고 싶을 경우, {} 묶어줘야 함
			
		} System.out.println("Total: " + count + "/5, Score: " + (count*20) + "점");
		
		scan.close();
	}
	
}


/*
[문제] 덧셈 문제
- 2자리 숫자(10-99)만 제공

[실행 결과]
a	 b
87 + 56 = 78
Wrong

 */
