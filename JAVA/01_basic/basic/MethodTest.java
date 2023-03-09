package basic;

public class MethodTest {
	
	public static void main(String[] args) {
		int a = 25;
		int b = 36;
		
		int big = Math.max(a, b);
		// Method 호출: Math class.max method 사용
		// class 내부에서 method만 작성할 경우, 해당 클래스 내부에서만 method 탐색
		// -> method가 속하는 class가 아닐 경우, method 앞 class 지정 필요
		System.out.println("Max: " + big);
		
		// 25.8, 78.6 작은값 구하기
		double small = Math.min(25.8, 78.6); // Math class의 max method 사용 = method 호출
		System.out.println("Min: " + small);
		
		// 250을 2진수로 출력
		int i = 250;
		String binary = Integer.toBinaryString(i);
		// Integer class의 toBinaryString() 사용 -> MethodTest class Method가 아니므로 Class Name 지정 필요
		System.out.println("In base 2: " + binary); // 11111010(2)

		String oct = Integer.toOctalString(i);
		System.out.println("In base 8: " + oct); // 372(8)

		String hexa = Integer.toHexString(i);
		System.out.println("In base 16: " + hexa); // fa(16)
		// 11111010(2) -> 1111(2) 1010(2) -> 15 (10) 10 (10) -> fa(16)
		
	}

}
