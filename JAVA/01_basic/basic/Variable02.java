package basic;

class Test { // public class가 아닌 경우, java file은 생성되지 않고 complie된 class 파일만 생성
	int a = 10;
	static int b = 20;
	static String str; // Default: Null
}


public class Variable02 { // class에서 public은 1개만 사용 가능	
	int a; // Field, Global Variable: Class 내부에서 사용 가능
	// Field는 초기화가 되어있으므로 변수값 지정없이 사용 가능
	double b;
	static int c;
	// static variable의 경우, memory 할당이 필요없이 자동으로 메모리에 공간이 부여되어있음(메모리 할당 선언 필요X)
	
	public static void main(String[] args) {
		int a=5; // 4byte(32bit) memory instance 생성
		// local variable: method 내부의 variable, method 내부에서 사용 가능
		// lv의 경우, 초기값이 garbage가 저장되어있으므로 Initialize 필요
		System.out.println("Local Variable a = " + a);
		
		Variable02 v = new Variable02(); // Variable Class memory 생성
		// Object v: Variable02의 address 보유(Class_name@16진수_참조주소값)
		System.out.println("Variable02: " + v);
		// Variable02: basic.Variable02@515f550a (pkg.Class@ref_address)
		
		System.out.println("Field a = " + v.a); // field 출력
		// Variable02의 주소값을 가진 v에서 해당 클래스의 필드값 a를 출력
		System.out.println("Field b = " + v.b); // field 출력
		System.out.println("Field c = " + c); // field 출력
		// Variable02.c와 동일
		// v.c 사용 필요 X
		System.out.println();
		
		Test t = new Test();
		System.out.println("Test class.a: " + t.a);

//		System.out.println("Test class.b: " + f);
//		해당 class 외부 class의 sv를 끌어올 때는 class명 생략 불가
		System.out.println("Test class.b: " + Test.b);
		System.out.println("Test class.b: " + t.b);
		// The static field Test.b should be accessed in a static way
		// static variable의 경우에는 instance로 생성한 변수를 이용하여 선언할 수 있지만 warning 발생

		System.out.println("Test class.str: " + Test.str); // Default: Null

	}
	
}
