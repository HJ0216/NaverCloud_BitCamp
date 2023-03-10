package while_;

import java.util.*;

public class NumberGame_T {

	public static void main(String[] args) {
		int com;
		int user=0;
		int count=0;
		
		com = (int)(Math.random()*100) + 1;
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the number between 1 and 100 (" + com + ") : ");
		user = scan.nextInt();


		while(user!=com) {
			++count;
			if(user>com) {
				System.out.println("The number is less than " + user);
				user = scan.nextInt();
			} else {
				System.out.println("The number is greater than " + user);
				user = scan.nextInt();
			} // else
		} // while: execute game, user!=com
		if(user==com) {System.out.println("Congratuation! Try Number: " + (count+1));}
		// 초기 입력값을 포함하기 위해 count+1
		
		scan.close();

	}
	
}

/*
[문제] 숫자 맞추기 게임
- 컴퓨터가 1 ~ 100사이의 난수를 발생하면, 발생한 난수를 맞추는 게임
- 몇 번만에 맟주었는지 출력한다.

[실행결과]
1 ~ 100사이의 숫자를 맞추세요 (70)

숫자 입력 : 50
50보다 큰 숫자입니다.

숫자 입력 : 85
85보다 작은 숫자입니다.

~~~

숫자 입력 : 70
딩동뎅...x번만에 맞추셨습니다.
 */
