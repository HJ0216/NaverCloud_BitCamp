package operator;

public class Operator03 {

	public static void main(String[] args) {		
		int a; 

		a = 5;
		a += 2; // 7
		// a = a*2; 2항 연산자 사용 시, 연산자와 =를 붙여서 사용해야 함
		a *= 3; // 21 
		a /= 5; // 4

		System.out.println("a = " + a); // 4
	
		a++;
		System.out.println("a = " + a); // 5
		
		int b = a++; // int b = a -> a = a+1
		System.out.println("a = " + a + ", b = " + b); // a=6, b=5

		int c = ++a * b--; // ++a -> a*b -> b--, b가 연산되고나서 1이 감소
		System.out.println("a = " + a + ", b = " + b + ", c = " + c); // a=7, b=5, c=35

		System.out.println("a++ = " + a++); // a++=7, 연산 후에 1이 증가
		System.out.println("a = " + a); // a=8
	
		
		System.out.println(Math.random());
		
	}
	
}


/*
3항 연산자: a = a + 2;
2항 연산자: a + =2;
1항(단항) 연산자: a++ (초기값 유지) / ++a (초기값 증가)
 */
