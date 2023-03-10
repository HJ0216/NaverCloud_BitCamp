package array;

import java.util.Scanner;

public class BaseBall2 {
	public static void main(String[] args) {
		int[] com = new int[3];
		int[] user = new int[3];
				
		Scanner scan = new Scanner(System.in);		
		
		Loop:
		while(true) {
			System.out.println("게임을 실행하시겠습니까(Y/N) :");
			String yn = scan.next();

			if(yn.equals("y") || yn.equals("Y")) {
				System.out.println("게임을 시작합니다.");
				
				for(int i=0; i<com.length; i++) {
					com[i] = (int)(Math.random()*9+1);
									
					for(int j=0; j<i; j++) {
						if(com[i]==com[j]) {
							i--;
							break;}
						// continue, break 시, i가 증가된 채로 진행되므로 i를 감소시켜줘야 함
						// if - 참일 경우, 실행문이 2개 이상일 경우 { }로 묶어주기
					} // for_j: 중복 제거
					System.out.print(com[i] + "  ");
				} // for_i: 난수 생성
				
				
				while(true) {
					int strike=0;
					int ball=0;

					System.out.print("\n숫자 입력: ");
					String strUser = scan.next();

					for(int i=0; i<user.length; i++) {
						user[i] = (int)(strUser.charAt(i)-48);
					} // for: 입력된 문자, 배열 저장
					
					for(int i=0; i<com.length; i++) {
						for(int j=0; j<user.length; j++) {
							if(com[i]==user[j] && i==j) {strike++;}
							else if(com[i]==user[j] && i!=j) {ball++;}
						} // strike, ball 처리
					} // for_i

					// strike, ball 출력
					System.out.println();
					System.out.println(strike + "스트라이크");
					System.out.println(ball + "볼");		
					
					// 3 strike 게임 종료
					if(strike==3) {
						System.out.println("프로그램을 종료합니다.");
						break Loop; // while inner 탈출
						} // if
					
					} // while: game

			} // if: game start
			
				else if(yn.equals("n") || yn.equals("N")) {
					System.out.println("프로그램을 종료합니다.");
					break;}
			
		} // while
		
		scan.close();
	}
}


/*
[문제] 야구게임
크기가 3개인 정수형 배열을 잡고 1~9사이의 난수를 발생한다
발생한 수를 맞추는 게임
단, 중복은 제거한다

[실행결과]
* while(true) + break;
게임을 실행하시겠습니까(Y/N) : w
게임을 실행하시겠습니까(Y/N) : u
게임을 실행하시겠습니까(Y/N) : y

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
