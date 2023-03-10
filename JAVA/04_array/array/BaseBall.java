package array;

import java.util.Scanner;

public class BaseBall {

	public static void main(String[] args) {
		int[] com = new int[3];
		int[] user = new int[3];
		
		Scanner scan = new Scanner(System.in);
		
		Loop:
		while(true) {
			System.out.print("게임을 실행하시겠습니까(Y/N) :");
			String y_or_n = scan.next();

			if(y_or_n.equals("y") || y_or_n.equals("Y")) {
				System.out.println("게임을 시작합니다.");				
				
				// computer random
				for(int i=0; i<com.length; i++) {									
					for(int j=0; j<i; j++) {
						com[i] = (int)(Math.random()*9+1);
						if(com[i]==com[j]) {
							i--;
							break;}
						// 중복 제거: continue, break 시, i가 증가된 채로 진행되므로 i를 감소시켜서 random값 다시 반환
					} // for_j: 중복 제거
					System.out.print(com[i] + "  ");					
				} // for_i: random

				System.out.println();
				
				
				while(true) {
					int cnt_strike=0;
					int cnt_ball=0;

					for(int i=0; i<user.length; i++) {
						System.out.print("숫자 입력 (" + (i+1) + "/3) : ");
						user[i] = scan.nextInt();
					} // for

					
					// 입력된 숫자 출력
					System.out.print("입력된 숫자: ");
					for(int i=0; i<user.length; i++) {
						System.out.print(user[i]);
					}
					
					for(int i=0; i<com.length; i++) {
						if(com[i]==user[i]) {cnt_strike++;}
					}
					// com  0 1 2
					// user 0 1 2
					
					for(int i=0; i<com.length-1; i++) {
						if(com[i]==user[i+1]) {cnt_ball++;}
					}
					// com  0 1
					// user 1 2
					
					for(int i=0; i<com.length-2; i++) {
						if(com[i]==user[i+2]) {cnt_ball++;}						
					}
					// com  0
					// user 2

					for(int j=0; j<user.length-1; j++) {
						if(user[j]==com[j+1]) {cnt_ball++;}						
					}
					// user 0 1
					// com  1 2

					for(int j=0; j<user.length-2; j++) {
						if(user[j]==com[j+2]) {cnt_ball++;}						
					}
					// user 0
					// com  2

					// strike, ball 출력
					System.out.println();
					System.out.println(cnt_strike + "스트라이크");
					System.out.println(cnt_ball + "볼");		
					
					// 3 strike 게임 종료
					if(cnt_strike==3) {
						System.out.println("프로그램을 종료합니다.");
						break Loop; // while inner 탈출
					} // if
					

					// strike, ball 처리
//					int strike=0;
//					int ball=0;
//				
//					for(int i=0; i<com.length; i++) {
//						for(int j=0; j<user.length; j++) {
//							if(com[i]==user[j] && i==j) {
//								strike++;
//							} else if(com[i]==user[j] && i!=j) {
//								ball++;
//							} // for
//						} // for_j
//					} // for_i
//					// 문제: i,j 중첩 반복문을 사용하면서 i=0일 때, j=0,1,2를 모두 검토하게 됨

					} // while inner

			} // if
						
				else if(y_or_n.equals("n") || y_or_n.equals("N")) {
					System.out.println("프로그램을 종료합니다.");
					break;
				}
			
		} // while
		
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
