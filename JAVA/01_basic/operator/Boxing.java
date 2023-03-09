package operator;

public class Boxing {
	public static void main(String[] args) {
		int a = 25;
		
		double b = (double)a / 3; // casting, 강제 형변환
		
		String c = "25";
//		int d = (int)c;
		// Cannot cast from String to int: 특정 객체형을 다른 기본형으로 강제 형변환 불가
		int d = Integer.parseInt(c);
		int d2 = Integer.valueOf(c);
		// AutoBoxing: Primitive = Wrapper
		
		int e = 5;
		Integer f = e;
		// AutoBoxing: primitive -> Wrapper
		
//		Integer g = new Integer(e); // Deprecated
		
		Integer g = 5;
		int h = g; // Auto-unboxing
		int i = g.intValue(); // Auto-unboxing
	}
	
}
