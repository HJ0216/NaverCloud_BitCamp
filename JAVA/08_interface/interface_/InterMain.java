package interface_;

public class InterMain implements InterC {
//public class InterMain implements InterA, InterB {
	// implements 시, 모든 abstract method 구현
	// 구현 X 시, abstract class -> New 생성 X
	
	// InterA
	public void aa() {}
	public void bb() {}

	// InterB
	public void cc() {}
	public void dd() {}

	public static void main(String[] args) {
		
	}
	
}
