package operator;

public class Operator04 {

	public static void main(String[] args) {
		int num1 = 0, num2 = 0;
		int num3 = 0, num4 = 0;
		boolean result;
		
		result = ((num1+=10) < 0 && (num2+=10) > 0);
		System.out.println("result: " + result); // false
		System.out.println("num1: " + num1 + ", num2: " + num2); // 10, 0
		// &&: (num1+=10) < 0 : False -> (num2+=10) > 0 수행 X

		result = ((num3+=10) < 0 & (num4+=10) > 0);
		System.out.println("result: " + result); // false
		System.out.println("num3: " + num3 + ", num4: " + num4); // 10, 10
		// &: (num1+=10) < 0 : False -> (num2+=10) > 0 수행 O

		result = ((num1+=10) > 0 || (num2+=10) > 0);
		System.out.println("result: " + result); // true 
		System.out.println("num1: " + num1 + ", num2: " + num2); // 20, 0
		// ||: (num1+=10) > 0 : True -> (num2+=10) > 0 수행 X

		result = ((num3+=10) > 0 | (num4+=10) > 0);
		System.out.println("result: " + result); // true 
		System.out.println("num3: " + num3 + ", num4: " + num4); // 20, 20
		// |: (num1+=10) > 0 : True -> (num2+=10) > 0 수행 O

	}
	
}


/*
&&: A && B -> A = False -> B 수행X
&: A & B -> A = False -> B 수행
||: A || B -> A = True -> B 수행X
|: A | B -> A = True -> B 수행

 */


