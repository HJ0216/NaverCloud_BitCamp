package array;

import java.util.Scanner;

public class Array04 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);

		int input;
		int entrance;
		int exit;
		int[] ar = new int[5];
		
		Loop: // 다중 반복문 시, label을 통해 종료하는 반복문 확인
		while(true) {
		// for(;;)
			System.out.println();
			System.out.println("주차장 관리 프로그램");
			System.out.println("********************");
			System.out.println("       1. 입차		");
			System.out.println("       2. 출차		");
			System.out.println("       3. 리스트	");
			System.out.println("       4. 종료		");
			System.out.println("********************");
			System.out.print("      메뉴:			");
			
			input = scan.nextInt();

			switch(input) {

			case 1 : {
				System.out.print("희망 입차 위치: ");
				entrance = scan.nextInt();
				if(ar[entrance-1]==0) { // int[] 미 입력 시, default=0
					System.out.println(entrance + "에 입차");
					ar[entrance-1]=1;
				} else {System.out.println("이미 주차되어있습니다.");}				
			}; break;
			
			case 2 : {
				System.out.print("출차 위치: ");
				exit = scan.nextInt();
				if(ar[exit-1]==1) {
					System.out.println(exit + "에 출차");
					ar[exit-1]=0;
				} else {System.out.println("주차되어 있지않습니다.");}				
			}; break;
			
			case 3 : {
				for(int i=0; i<ar.length; i++) {
					System.out.println((i+1) + " 위치 : " + ar[i]);
				}
//				for(int data : ar[entrance-1]) {Systme.out.println(data + "  ")}
				}; break;
			
			case 4 : System.out.println("프로그램을 종료합니다."); break Loop;

			default : System.out.println("잘못된 입력");
			
			} // switch

		} // while
		
		scan.close();
	} // main
	
} // class


/*
[문제] 주차관리 프로그램

[실행결과]
주차장 관리 프로그램
**************
   1. 입차
   2. 출차
   3. 리스트
   4. 종료
**************
  메뉴 : 
  
1번인 경우
위치 입력 : 3
3위치에 입차 / 이미 주차되어있습니다

2번인 경우
위치 입력 : 4
4위치에 출차 / 주차되어 있지않습니다

3번인 경우
1위치 : true
2위치 : false
3위치 : true
4위치 : false
5위치 : false  

 */
