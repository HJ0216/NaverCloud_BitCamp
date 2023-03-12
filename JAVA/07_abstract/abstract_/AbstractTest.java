package abstract_;

public abstract class AbstractTest {
	protected String name="홍길동"; // Capsule화, POJO형식(Plain Old Java Object, 일반 Java Obj)
	// Constructor를 통한 초기값 설정 or Setter를 통한 값 변경

	public AbstractTest() {}
	
	public AbstractTest(String name) {
		super(); // Object
		this.name = name;
	}
	
	
	public String getName() {return name;}

	public abstract void setName(String name);
	
	public abstract void abstractMethod();
	// abstract Method, 구현부 X
	// abstract Method -> abstract class에서만 사용 가능
	// abstract class에서 반드시 abstract method를 구현해야하는 것은 아님
	
}


class AbstractTest_sb extends AbstractTest {
	@Override
	public void abstractMethod() {
		System.out.println("Override");
	}
	
	public void setName(String name) {System.out.println("Name: " + name);}
}