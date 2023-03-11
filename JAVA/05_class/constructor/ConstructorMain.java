package constructor;

public class ConstructorMain {
	private String name; // Null
	private int age; // 0
	
	public ConstructorMain() {
		System.out.println("Default Constructor");
	}

	public ConstructorMain(String name) { // Constructor Overload
		this(); // calling default constructor
		System.out.println("Name Constructor");
		this.name = name;
	} // Default Constructor 외의 생성자가 존재
	할 경우, 자동으로 Default Constructor 생성 X
	
	public ConstructorMain(int age) { // Constructor Overload
		this("코난"); // Constructor가 Constructor 호출 시, 맨 첫줄에 위치해야 함
		System.out.println("Age Constructor");
		this.age = age;
	}
	
	// Method
//	public void ConstructorMain() {
//		System.out.println("Default Constructor");
//	}

	
	
	public static void main(String[] args) {
		ConstructorMain aa = new ConstructorMain();
		// new: memory allocation
		// new: call Construction
		System.out.println(aa + "\t" + aa.name + "\t" + aa.age + "\n");
		
		ConstructorMain bb = new ConstructorMain("홍길동");
		// Constructor는 강제 호출이 안되므로 객체 생성을 해야 함
		System.out.println(bb + "\t" + bb.name + "\t" + bb.age + "\n");
		
		ConstructorMain cc = new ConstructorMain(25);
		System.out.println(cc + "\t" + cc.name + "\t" + cc.age + "\n");
	}
	
}
