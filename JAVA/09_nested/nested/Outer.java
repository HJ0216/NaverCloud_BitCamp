package nested;

public class Outer {
	private String name;
	
	public Outer() {} // Default Constructor
	
	public void output() {
		Inner inner = new Inner();
		System.out.println("Name: " + this.name + "\tAge: " + inner.age);
		// Outer -> Inner 접근 X
		// 객체 생성 시, 가능
	}
	
	class Inner {
		private int age;
		
		public void disp() {
			System.out.println("Name: " + Outer.this.name + "\tAge: " + this.age);
			// inner class에서 outer class에 있는 private variable에 접근 가능
			// Outer.this: 상속 관계 X
		}
	}
	
	public static void main(String[] args) {
		Outer outerClass = new Outer();
		outerClass.name = "name";
		System.out.println("Name: " + outerClass.name);
		System.out.println();
		
//		Inner innerClass = new Inner(); // inner class로만 만들 수 없음
		Outer.Inner innerClass1 = outerClass.new Inner();
		// outer class 내부에 inner class memory allocation
		innerClass1.age = 25;
		innerClass1.disp();
		System.out.println();
		
		Outer.Inner innerClass2 = outerClass.new Inner();
		// OuterClass 안의 new InnerClass 추가 생성
		innerClass2.age = 30;
		innerClass2.disp();
		System.out.println();
		
		Outer.Inner innerClass3 = new Outer().new Inner();
		// Inner class를 새로 담아 줄 Outer 새로 생성
//		innerClass3.name="코난"; // inner class obj 생성 후, outer class의 variable에 접근 할 수 없음
		// class 안에서는 inner class가 outer class의 field값에 접근할 수 있지만,
		// static method 안에서는 inner class 내부를 가르킬 때, 외부 클래스의 variable에 접근할 수 없음
		innerClass3.age = 35;
		innerClass3.disp();
	
		
	}
}


/*
/bin
Outer.class
Outer$Inner.class // nested class: $
*/
