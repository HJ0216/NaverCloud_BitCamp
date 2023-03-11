package class__;

class StaticTest {
	private int a; // field, instance variable, Memory: heap 영역
	private static int b; // field, static(class) variable, Memory: static 영역

	static {
		System.out.println("Static Initialization Area");
		StaticTest.b=5; // Cannot use this in a static context
		// Static value는 this로 불러오지 않음
		// this는 class의 구성요소를 불러올 때 사용하지만, static은 해당 this로 불러올 수 없음
	} // class StaticTest  호출 시 자동 load
	
	public StaticTest() {
		System.out.println("Default Constructor");
		this.a = 5;
	}
	
	public void calc() {
		a++;
		b++;
	}
	
	public void disp() {
		System.out.println("a: " + a + ", b: " +b);
	}
	
	public static void output() {
		System.out.println("Static Method");
//		System.out.println("a: " + this.a + ", b: " +StaticTest.b);
	} // Cannot make a static reference to the non-static field a
	// Static Method 내에서는 static(class) variable만 사용 가능
	
}

public class StaticMain {

	public static void main(String[] args) {
		StaticTest st = new StaticTest();
		st.disp(); // a: 5, b: 5
		st.calc();
		st.disp(); // a: 6, b: 6
		
		System.out.println();
		
		StaticTest st2 = new StaticTest();
		st2.calc();
		st2.disp(); // a: 6, b: 7
		System.out.println();
		
		StaticTest st3 = new StaticTest();
		st3.calc();
		st3.disp(); // a: 6, b: 8
		System.out.println();
		
		// heap 영역의 특징: 새로운 메모리 할당
		// static 영역의 특징: 동일한 변수 명을 사용할 수 없으며 해당 변수의 값을 그대로 이용
		// a: 매번 초기화 진행, b: memory 상 static area 1번만 생성, 동일 변수명 사용 시, 기존 static value 그대로 이용
		
		StaticTest.output(); // class.method()
		st.output();
		st2.output();
		st3.output();
		
	}
	
}
