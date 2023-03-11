package inheritance;

class Test{}

class Sample{
	@Override
	public String toString() {
		return getClass() + "Hello World";
	}
	
}

class Exam {
	private String name = "HGD";
	private int age = 25;

	@Override
	public String toString() {		
//		return super.toString(); // super: parent
		return name + "\t" + age;
	}
}

public class ObjectMain {

	public static void main(String[] args) {
		Test t = new Test();
		System.out.println("t: " + t); // inheritance.Test@6f2b958e, pkg.class@ref_address(16)

		System.out.println("t.toString(): " + t.toString()); // inheritance.Test@6f2b958e
		// return getClass().getName() + "@" + Integer.toHexString(hashCode());
		// toString: Obj class method
		
		System.out.println("t.hashCode(): " + t.hashCode()); // ref_address: 16 -> 10
		
		
		Sample s = new Sample();
//		System.out.println("s.toString(): " + s.toString()); // inheritance.Sample@1c4af82c
		System.out.println("s.toString(): " + s.toString()); // @Override, getClass() + Hello World
		
		
		Exam e = new Exam();
		System.out.println("e.toString(): " + e.toString());
		
		
		String aa = "apple";
		System.out.println("aa: " + aa);
		// apple -> 주소값 반환을 하지 않음, String class에서 toString()이 Override된 상태
		System.out.println("aa.toString(): " + aa.toString());
		System.out.println("aa.hashCode(): " + aa.hashCode());
		// 무한값인 String을 int hashCode로 나타낼 수 없음(신뢰 X)

		String bb = "apple";
		String cc = "apple";		
		System.out.println("bb==cc: " + (bb==cc)); // value, true
		// String에서 toString은 value로 override된 상태이므로 true return
		System.out.println("bb.equals(cc): " + (bb.equals(cc))); // value, true
		
		
		String dd = new String();
		dd = "banana";
		String ee = new String();
		ee = "banana";
		System.out.println("dd==ee: " + (dd==ee)); // ref_address, false
		System.out.println("dd.equals(ee): " + (dd.equals(ee))); // value, true


		Object ff = new Object();
		Object gg = new Object();
		System.out.println("ff==gg: " + (ff==gg)); // ref_address, false
		System.out.println("ff.equals(gg): " + (ff.equals(gg))); // ref_address, false

	
		Object hh = new String("driver");
		// String class 및 Object class 생성
		Object ii = new String("driver");
		System.out.println("hh==ii: " + (hh==ii)); // ref_address, false
		System.out.println("hh.equals(ii): " + (hh.equals(ii))); // value, true
		// Override된 method를 사용 시, child class(String)의 method 사용
	
	}
	
}
