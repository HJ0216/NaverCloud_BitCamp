package class__;

import java.util.*;

public class StringMain2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("문자열 입력: ");
		String input = scan.next();
		
		System.out.print("현재 문자열 입력: ");
		String repOld = scan.next();
		
		System.out.print("바꿀 문자열 입력: ");
		String repNew = scan.next();
				
		if(repOld.length()>input.length()) {
			System.out.println("입력한 문자열의 크기가 작습니다.");
			System.out.println("치환할 수 없습니다.");
		} else {
			int index = input.toLowerCase().indexOf(repOld.toLowerCase());
			// toLowerCase: input, repOld 소문자 치환 후 탐색(대소문자 구분X)
			int count=0;
			String inputNew=""; // Initialization

			for(int i=0; i<input.length(); i++) {
				
			}
			
			while(index > -1) {
				count++;
				index = input.toLowerCase().indexOf(repOld.toLowerCase(), index + repOld.length());
			} // while: find String
			
			if(input.toLowerCase().indexOf(repOld.toLowerCase())!=-1) {
				inputNew = input.toLowerCase().replace(repOld.toLowerCase(), repNew);
			} // if: replace
			
			
			
			System.out.println(inputNew);
			
			
			
			System.out.println(count + "번 치환");
			
		} // else: indexOf & replace
		
		
		scan.close();
		
	} // main()
} // class


/*
치환하는 프로그램을 작성하시오 - indexOf(?, ?), replace()
String 클래스의 메소드를 이용하시오
대소문자 상관없이 개수를 계산하시오
무한 루프X

[실행결과]
문자열 입력 : aabba
현재 문자열 입력 : aa
바꿀 문자열 입력 : dd
ddbba
1번 치환

문자열 입력 : aAbbA
현재 문자열 입력 : aa
바꿀 문자열 입력 : dd
ddbba
1번 치환

문자열 입력 : aabbaa
현재 문자열 입력 : aa
바꿀 문자열 입력 : dd
ddbbdd
2번 치환

문자열 입력 : AAccaabbaaaaatt
현재 문자열 입력 : aa
바꿀 문자열 입력 : dd
ddccddbbddddatt
4개 치환

문자열 입력 : aabb
현재 문자열 입력 : aaaaa
바꿀 문자열 입력 : ddddd
입력한 문자열의 크기가 작습니다
치환 할 수 없습니다
 */
