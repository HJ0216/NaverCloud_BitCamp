package class_;

class This {
	private int b; // field
	private static int c;
	
	public void setB(int b) {
		System.out.println("setB this = " + this);
		this.b = b; // parameter와 field를 구별하기 위해 this 사용
		} // Method 구현
	public void setC(int c) {
		System.out.println("setC this = " + this);
		this.c = c;
		} // Method 구현

	public int getB() {return this.b;} // 호출
	public int getC() {return c;} // 호출
		
}

public class ThisMain {
	private int a;
	// private: private 사용 class 외 외부 class 접근 불가

	public static void main(String[] args) { // static(): this 사용 불가
		ThisMain tm = new ThisMain(); // static variable이 아닌 경우, 객체 생성
		tm.a=10;
		System.out.println("tm.a: " + tm.a + "\n");
		
		This t = new This();
		System.out.println("객체 t = " + t);
		// 객체 t = class_.This@626b2d4a (pkg.class@ref_address(Hash Num))

//		t.b=20; private 외부 class 접근 불가
		t.setB(20);
		System.out.println("t.b: " + t.getB());
		
//		t.c = 30; private 외부 class 접근 불가
		t.setC(30);
		System.out.println("t.c: " + t.getC() + "\n");
		

		This w = new This(); // new -> memory allocation, ref-address 별도 생성
		System.out.println("객체 w = " + w);
		w.setB(40);
		w.setC(50);
		
		System.out.println("w.b: " + w.getB());
		System.out.println("w.c: " + w.getC());
	}
}
