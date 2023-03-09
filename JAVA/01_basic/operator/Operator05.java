package operator;

public class Operator05 {

	public static void main(String[] args) {
		boolean a = 25 > 36;
		System.out.println("a = " + a); // false
		System.out.println("!a = " + !a); // true
		
		String b = "apple";
		// String class의 경우, literal(자료형의 type) 생성 가능
		// b가 보유하고 있는 것은 "apple"값이 아닌 "apple"의 주소값을 갖고 있음
		String c = new String("apple");
		// c가 보유하고 있는 것은 "apple"값이 아닌 "apple"의 주소값을 갖고 있음
		// 기본형과 달리 참조형은 같은 값이라도 새로운 메모리를 생성하여 상이한 주소값을 갖게 됨

		System.out.println(b == c ? "same" : "different"); // different
		System.out.println(b != c ? "true" : "false"); // true
		// b == c, b와 c의 '주소값' 비교
		System.out.println(b.equals(c) ? "same" : "different"); // same
		System.out.println(!b.equals(c) ? "true" : "false");// false
		// b == c, b와 c의 '값' 비교
		
		int i = 20;
		int i2 = 20;
		System.out.println(i == i2 ? "same" : "different"); // same
		// 기본형의 경우, 같은 값일 경우 새로 메모리를 생성하지 않아 동일한 주소값을 갖게 됨
		
		
	}
	
}
