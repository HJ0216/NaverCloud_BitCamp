package for_;

import java.util.*;

public class Game {
	
	public static void main(String[] args) {

		int coin = 1000;
		int game_fee = 300;

		System.out.println("Inserted coin : 1000");

		Scanner scan = new Scanner(System.in);
		
		while((coin-game_fee)>0) {
			coin -= game_fee;

			System.out.print("Scissors(1), Rock(2), Paper(3), Please Enter the number: ");
			
			int num_rps_u = scan.nextInt();
			
			System.out.println(num_rps_u + " (user, 사용자)");

			String rps_u;

			switch(num_rps_u){
			case 1  : rps_u = "Scissors";     break;
			case 2  : rps_u = "Rock";         break;
			case 3  : rps_u = "Paper";        break;
			default : rps_u = "Wrong Number";
			} // switch
			
				while(rps_u == "Wrong Number") {
					System.out.println("You entered the Wrong number. Please Enter the one of 1, 2, 3");
					num_rps_u = scan.nextInt();
					// nextInt() 입력 위치에 따른 반복 횟수 조정

					switch(num_rps_u){
					case 1  : rps_u = "Scissors";     break;
					case 2  : rps_u = "Rock";         break;
					case 3  : rps_u = "Paper";        break;
					default : rps_u = "Wrong Number";
					} // switch
				} // while: wrong_number
			
			int num_rps_c = (int)(Math.random()*3)+1;

			String rps_c;
			switch(num_rps_c){
			case 1  : rps_c	= "Scissors";     break;
			case 2  : rps_c = "Rock";         break;
			case 3  : rps_c = "Paper";        break;
			default : rps_c = "Wrong Number";
			} // switch: rps_num -> String rps

			System.out.println("Computer: " + rps_c + " / User: " + rps_u);

			if(rps_c=="Scissors") {
				if	   (rps_u=="Rock")  	{System.out.println("You Win!");}
				else if(rps_u=="Paper")		{System.out.println("You Lose!");}
				else 						{System.out.println("Draw!");}
			} // if
				else if(rps_c=="Rock") {
				if	   (rps_u=="Scissors") {System.out.println("You Lose!");}
				else if(rps_u=="Paper")    {System.out.println("You Win!");}
				else 					   {System.out.println("Draw!");}				
			} // else if
				else {
				if	   (rps_u=="Scissors") {System.out.println("You Win!");}
				else if(rps_u=="Rock")	   {System.out.println("You Lose!");}
				else 					   {System.out.println("Draw!");}								
			} // else

			System.out.println("Balance: " + coin);
			System.out.println();
			} // while: going game, if the coin>0
		
		System.out.println("Lack of Balance.");
		
		scan.close();
		// 반복문 내부에서 Scanner 객체 생성 시, scan을 close()할 수 없음
		// 객체 생성은 반복문 밖에서 진행하고, Scanner method()만 반복문 안에서 선언
	}
}

/*
1. insert coin
2. 조건문: 잔액 부족 시 게임 진행 불가
3. 가위바위보 입력받기
4. 컴퓨터 random으로 입력받기
5. 게임 조건문 작성
6. 게임 결과 반환


[문제] 가위, 바위, 보 게임
- 가위(1), 바위(2), 보자기(3) 지정한다.
- 컴퓨터(com)는 1 ~ 3까지 난수로 나온다
- 1게임당 300원으로 한다.

[실행결과]
insert coin : 1000

가위(1), 바위(2), 보(3) 중 번호 입력 : 3 (user, 사용자)
컴퓨터 : 바위   나 : 보자기
You Win!!

가위(1),바위(2),보(3) 중 번호 입력 : 1 (user)
컴퓨터 : 가위   나 : 가위
You Draw!!

가위(1),바위(2),보(3) 중 번호 입력 : 3 (user)
컴퓨터 : 가위   나 : 보자기
You Lose!!

 */
