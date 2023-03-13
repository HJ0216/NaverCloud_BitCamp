package collection;

class GenericTest<T> { // T: type 미정
	private T a;
	
	public GenericTest() {}
	
	public void setA(T a) {this.a = a;}
	public T getA() {return a;}
	
}

public class GenericMain {

	public static void main(String[] args) {
		GenericTest<String> gt1 = new GenericTest<String>();
		// Obj 생성 시, data type은 String으로 제한
		gt1.setA("홍길동");
		System.out.println("이름: " + gt1.getA());

		GenericTest<Integer> gt2 = new GenericTest<>();
		// T: Wrapper Class
		gt2.setA(25);
		System.out.println("나이: " + gt2.getA());
		
		GenericTest<Double> gt3 = new GenericTest<>();
		gt3.setA(33.33);
		System.out.println("Double: " + gt3.getA());
		
	}
}
