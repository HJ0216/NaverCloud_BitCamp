package basic;

public class Variable01 {

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE); // 2147483647
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Long.MAX_VALUE); // 9223372036854775807
		System.out.println(Long.MIN_VALUE);
		System.out.println();
		
		System.out.println(25 > 32); // False
		boolean a; // 1bit
		a = (25>32);
		System.out.println("a = " + a); // False = 0 / True = 1
		
		char b; // 2byte, 16bit
		b = 'A'; // 65, 0100 0001, 0x41
		System.out.println("b = " + b);
		
		char c;
		c = 65;
		System.out.println("c = " + c); // A(char type)
		
		int c2;
		c2 = 65;
		System.out.println("c2 = " + c2); // 65(int type)
		
		byte d;
		d = 127;
		System.out.println("d = " + d); // 127
	
		byte d2;
		d2 = (byte) 128;
		// cast: 강제 형변환(byte가 아니지만 변수에 들어갈 때만 잠시 형변환) -> 가장 작은수로 재이동
		System.out.println("d2 = " + d2);

//		byte d3; // NotInitialized -> d3 초기값 설정 필요
//		System.out.println("d3 = " + d3);

		int e = 65;
		System.out.println("e = " + e); // 127
		
		int f;
		f = 'A';
		System.out.println("f = " + f); // 127
		
		long g;
		g = 25L; // 'L': long type data, default: int
		System.out.println("g = " + g); // 127
		
		float h;
		h = 43.8f; // 'f': float type data, default: double
		System.out.println("h = " + h); // 127
		
		
	}

}