package operator;

public class Comp {
	public static void main(String[] args) {
		char ch = 'e'; // 66

//		System.out.println((int)'A'); // 65
//		System.out.println((int)'a'); // 97
		
//		int result = ch >= 97 ? ch-32 : ch+32;
//		int result = (ch >= 'A' && ch <='Z') ? ch+32 : ch-32; // char result: 2byte, int ch+2: 4byte

		int sub = 'a' - 'A';
		int result = (ch >= 'A' && ch <='Z') ? ch+sub : ch-sub;
		
		System.out.println(ch + "->" + (char)result);
		
	}
	
}


/*
[문제] 변수의 값이 대문자이면 소문자로 변환해서 출력, 소문자이면 대문자로 변환해서 출력하시오
(단, 대소문자 차이 = 32)

[실행결과]
B → b         e → E

 *
Logic
1. int 변환
2. 조건문 활용
3. 형변환

 */
