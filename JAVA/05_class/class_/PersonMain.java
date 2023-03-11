package class_;

public class PersonMain {

	public static void main(String[] args) {
		Person a;
		// class type variable(Obj) Declaration
		
		a = new Person();
		// class Person memory allocation

		System.out.println("Obj a: "+a);
		// class_.Person@6f2b958e: pkg.cls_name@ref_address
		
		a.setName("길동");
		// variable search path: args, 해당 class 내부
		// 외부 class의 변수를 참조하고자 할 경우, 해당 obj variable 사용
		a.setAge(25);
				
		System.out.println("Name: " + a.getName() + " / Age: " + a.getAge());
		
		Person b = new Person();
		// Person a와는 다른 새로운 객체 b를 생성하여, memory allocation
		b.setName("둘리");
		b.setAge(20);
		System.out.println("Name: " + b.getName() + " / Age: " + b.getAge());
		
		b.setName("또치");
		b.setAge(15);
		System.out.println("Name: " + b.getName() + " / Age: " + b.getAge());

		Person c = new Person();
		// Person a, b와는 다른 새로운 객체 c를 생성하여, memory allocation
		c.setData("희동", 10);

		Person d = new Person();
		// Person a, b와는 다른 새로운 객체 c를 생성하여, memory allocation
		d.setName("희동");
		d.setAge(10);
		System.out.println("Name: " + d.getName() + " / Age: " + d.getAge());

	}
}

class Person { // class 당 class file 생성
	private String name;
	// field -> 값을 처음에 지정하지 않아도 초기화되어있음(String=null)
	// The field Person.name is not visible\
	// -> private 선언 시, 외부 class에서 해당 데이터에 직접 접근 불가
	private int age; // (int=0)

	// private variable 접근을 위한 public method 구현
	// public return_type method_name(argument) {method 구현;}
	// set method
	public void setName(String n) { // 설정 method
		name = n;
	}
	public void setAge(int a) {
		age = a;
	}

	public void setData() {} // 초기값으로 return
	// Overloading: 하나의 class안에 동일한 method_name이 있는 경우
	
	public void setData(String n, int a) {
		name = n;
		age = a;
	}

	
	// get method
	public String getName() {return name;}
	public int getAge() {return age;}

	public Object getData() {return name;}
	 // return 값은 반드시 1개
}
