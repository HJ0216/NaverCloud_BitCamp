package while_;

import java.util.*;

public class NumberGame {

	public static void main(String[] args) {
		int com;
		int user;
		int count;
				
		Scanner scan = new Scanner(System.in);

		while_whole:
		while(true) {		
			com = (int)(Math.random()*100) + 1; // while 문 안에 위치해서 게임마다 random값 변화
			count = 0; // 게임 반복 시, count 초기화

			while(true) {
				count++; // 게임 실행 횟수
				System.out.print("Please enter the number between 1 and 100 (" + com + ") : ");
				user = scan.nextInt(); // 입력받는 곳에 위치

				if(com == user) {
					System.out.println("Game Over, tried number: " + count);
					break;
				}
				
				else if(com > user) {System.out.println("Upper than " + user);}		
				else {System.out.println("Lower than " + user);}
			} // while: game execute
			
			
			while(true) {	
				System.out.println("One more try? [y/n]");
				String yn = scan.next();
				if(yn.equals("n") || yn.equals("N")) {
					System.out.println("Terminated");
					break while_whole;}
				if(yn.equals("y") || yn.equals("Y")) {continue while_whole;}
	
//				int yn = scan.nextInt();			
//				if(yn == 'n' || yn == 'N') {break;}
				// 문자열: ==(주소값), equals(입력값)
			} // while: repeat game
			
	} // while: whole game
		
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
딩동댕...x번만에 맞추셨습니다.
 */
