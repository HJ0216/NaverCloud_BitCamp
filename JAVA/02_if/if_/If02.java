package if_;

import java.util.*;

public class If02 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("The score of subject a: ");
		int a = scan.nextInt();
		
		System.out.println("The score of subject b: ");
		int b = scan.nextInt();

		System.out.println("The score of subject c: ");
		int c = scan.nextInt();
		
		
		double avg = (a+b+c) / 3.0; // variable 활용하기

		List<String> iArr = new ArrayList<String>();
		// List add 불가, ArrayList add 가능
		
		if(a<40)
			iArr.add("a");
		if(b<40)
			iArr.add("b");
		if(c<40)
			iArr.add("c");
		
		
		if (a<40 || b<40 || c<40)
			System.out.println("Fail because the " + iArr + " score is lower than 40.");
		else if (avg>=60)
			System.out.println("Pass");
		else
			System.out.println("Fail becuase the avg is lower than 60.");

//		if(avg>=60) {
//			if(a>=40 && b>=40 && c>=40)
//				System.out.println("Pass");
//			else System.out.println("Fail: The score is lower than 40.");
//		} else {
//			System.out.println("Fail: The avg is lower than 60.");
//		}

		scan.close();

	}
}


/*
[문제] 
3과목(a,b,c)의 점수를 입력받아서 합격인지 불합격인지 출력하시오
합격은 평균이 60점 이상이어야 하고 각 과목이 40점 이상이어야 한다

[실행결과]
a의 값 : 98
b의 값 : 90
c의 값 : 85
합격

a의 값 : 98
b의 값 : 90
c의 값 : 35
과락으로 불합격

a의 값 : 68
b의 값 : 50
c의 값 : 45
불합격


1. 3과목이 40이상인 경우에만 60 확인
->40이하의 과목이 있다면 불합격
2. 잔여는 평균만 계산

 */
