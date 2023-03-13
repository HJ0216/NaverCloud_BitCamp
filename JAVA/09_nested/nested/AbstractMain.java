package nested;

public class AbstractMain {

	public static void main(String[] args) {
		// 1. Inheritance(Parent-sub class)
//		public class AbstractMain extends AbstractTest {
//			public void setName(String name) {}
//			public static void main(String[] args){}
//		}		
		
		// 2. Using Method
		// method 구현부를 갖을 수 있는 구역은 Class
		AbstractTest abstractTest = new AbstractTest() {
			// method의 구현부를 갖을 수 있는 영역은 class
			// obj 뒤에 생성되는 {}는 class 영역
			// class 영역인데도 불구하고 class name이 나타나지 않는 것을 anonymous inner class
			// 익명 inner class는 일회용으로 사용
			public void setName(String name) {
				this.name = name;
			}
		}; // abstract obj 생성 후, 구현부에서 구현
		// private은 해당 class에서만 접근가능하므로 variable type을 protected or default로 변경
		
		InterA interA = new InterA() {
			// interface에 new 연산자를 사용하는 것이 아니라, anonymous class를 사용
			public void aa() {}
			public void bb() {}
		};
		
		AbstractExam abstractExam = new AbstractExam() {
			// empty body로 구현해놓은 method를 선택적으로 구현
		};
	}
}

// 익명 class에 대한 compile file: AbstractMain$1.class