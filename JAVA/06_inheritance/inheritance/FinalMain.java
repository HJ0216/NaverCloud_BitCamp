package inheritance;

enum Color {
	RED, GREEN, BLUE;
} // enum: 상수 집합
// enum 상수: 묵시적으로 static final status형으로 선언
// 초기값: 0부터 부여

class Final {
	public final String FRUIT = "Apple";
	public final String FRUIT2;
	
	public static final String ANIMAL = "Giraffa";
	// static, 실행 시 메모리에 자동으로 생성되므로 new 연산자를 통한 메모리 할당 필요 X
	public static final String ANIMAL2;
	
	// public static final int: enum 활용
//	public static final int RED = 0;
//	public static final int GREEN = 1;
//	public static final int BLUE = 2;
	// 빛의 3요소: RGB(1 byte * 3, 000: Black, 255255255: White)
	
	static { // static initializer
		System.out.println("Static initialize");
		ANIMAL2 = "Elephant";
	}
	
	public Final() {FRUIT2 = "StrawBerry";}
	// new를 통한 obj 생성을 하지 않으므로 static은 constructor를 사용하지 않음
	// constructor를 통한 static value의 initialize 불가
	
	
} // Final Class

public class FinalMain {

	public static void main(String[] args) {
		final int A = 10;
//		A = 20;
		// The final local variable a cannot be assigned. It must be blank and not using a compound assignment
		System.out.println("A: " + A);
		
		final int B;
		B = 30; // final value에 초기값을 설정하지 않았을 경우, 최초 한 번만 설정 가능
		System.out.println("B: " + B);
		
		Final f = new Final();
		System.out.println("Fruit: " + f.FRUIT);
		System.out.println("Fruit2: " + f.FRUIT2); // field, instant
		
		System.out.println("Animal: " + Final.ANIMAL); // static variable
		System.out.println("Animal: " + Final.ANIMAL2); // static variable

		System.out.println("RED: " + Color.RED);
		System.out.println("GREEN: " + Color.GREEN);
		System.out.println("BLUE: " + Color.BLUE);
		
		System.out.println("RED: " + Color.RED.ordinal());
		System.out.println("GREEN: " + Color.GREEN.ordinal());
		System.out.println("BLUE: " + Color.BLUE.ordinal());
		
		for(Color color_value : Color.values()) { // Data type: Color(Enum Type)
			System.out.println(color_value + "\t" + color_value.ordinal());
		}

	} // main()
} // FinalMain class

/*
순서
1. local variable
2. static constructor
3. new: constructor call
4. print 순서
*/
