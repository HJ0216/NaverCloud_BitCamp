package array;

import java.util.Scanner;

public class BaseBall_T {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[] com = new int[3];
		int[] user = new int[3];
		
		String yn;
		
		do {
			System.out.println("Are you ready to start Game(Y/N)? ");
			yn = scan.next();
			// yn은 내부에서 선언할 경우, 지역변수로 do{}에서만 사용 가능
		} while(!yn.equals("Y") && !yn.equals("y") && !yn.equals("N") && !yn.equals("n"));
		
		if(yn.equals("Y") || yn.equals("y")) {
			System.out.println("Start Game");	
			
			for(int i=0; i<com.length; i++) {
				com[i] = (int)(Math.random()*9+1);
				
				for(int j=0; j<i; j++) {
					if (com[i]==com[j]) {
						i--;
						break;} // if: 중복 발생 시,
				} // for_j: 중복 확인
				
			} // for_i: 난수 생성
			
			System.out.println(com[0] + ", " + com[1] + ", " + com[2]);
			
			
			// 사용자 숫자 입력
			while(true) {
				int strike = 0;
				int ball = 0;
				// 게임 반복 시마다 값 초기화
				
				System.out.println("\nEnter the number: ");
				int num = scan.nextInt();
				
				user[0] = num/100;
				user[1] = (num%100)/10;
				user[2] = (num%100)%10;

				/*
				String num = scan.next();
				
				user[0] = num.charAt(0);
				user[1] = num.charAt(1);
				user[2] = num.charAt(2);				
				 */
				
				System.out.println(user[0] + ", " + user[1] + ", " + user[2]);

				
				// comparison
				for(int i=0; i<com.length; i++) {
					for(int j=0; j<com.length; j++) {
						
						if(com[i]==user[j]) {
							if(i == j) {strike++;}
							else {ball++;}
						} // if: classify strike, ball
						
					} // for_j
				} // for_i
				
				System.out.println(strike + "스트라이크\t" + ball + "볼");
				
				if(strike==3) {
					System.out.println("Correct!");
					break; // break while(true)
				} // if: Game finish
							
			} // while: Loop Game
						
		} // if: game start - y or n
		
		else System.out.println("Terminated Program");

		scan.close();
	}
	
}

/*
[문제] 야구게임
정수형 배열: 크기(3), 1~9사이의 난수
발생한 수를 맞추는 게임
중복 불허

[실행결과]
게임을 실행하시겠습니까(Y/N) :
게임을 실행하시겠습니까(Y/N) :
게임을 실행하시겠습니까(Y/N) :

게임을 시작합니다
* rule: 위치+숫자 = strike, 숫자 = ball

숫자입력 : 123
0스트라이크 0볼

숫자입력 : 567
0스트라이크 2볼

숫자입력 : 758
1스트라이크 2볼
...

숫자입력 : 785
3스트라이크 0볼

프로그램을 종료합니다.
 */
