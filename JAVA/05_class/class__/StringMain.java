package class__;

public class StringMain {

	public static void main(String[] args) {
		String a = "apple";
		// String class만 literal(자료형 type)로 생성 가능
		// 단, 문자열 편집은 불가능하며 연산으로 시 memory allocation 진행
		// Memory에 literal은 동일한 값이 있을 때, 새로운 memory allocation을 하지 않음
		String b = "apple";
		
		System.out.println("String a: " + a);
		System.out.println("String b: " + b);
		System.out.println("a==b: " + (a==b)); // true
		// "apple"이 있는 곳을 String a와 String b가 동일하게 가리킴
		// 동일한 ref_address 반환
		System.out.println("a.euqals(b): " + a.equals(b)); // false
		// String a와 String b의 값은 동일
		
		String c = new String("car");
		String d = new String("car");
		
		System.out.println("\nString c: " + c);		
		System.out.println("String d: " + d);		
		System.out.println("c==d: " + (c==d)); // false
		// "apple"이 있는 곳을 String a와 String b가 새로이 memory allocation을 함
		// 상이한 ref_adress 반환
		System.out.println("c.euqals(d): " + c.equals(d)); // true

		String e = "오늘 날짜는 " + 2023 + 2 + 10;
		// 문자열 편집이 안되므로,
		// "오늘 날짜는"
		// "오늘 날짜는 2023"
		// "오늘 날짜는 20232"
		// "오늘 날짜는 2023210"
		// 연산마다 momory 생성 -> memory 낭비
		// -> Java Virtual Machine이 자동적으로 Garbage Collector에 사용되지 않는 memory 정리
		// Garbage Collector가 실행되면 컴퓨터는 일시 정지 상태에 들어감
		
		System.out.println("e: " + e);
		// String  + int -> String, +: 결합, 오늘 날짜는 20232010
		// 왼쪽에서 오른쪽으로 연산
		
		System.out.println("문자열 크기: " + e.length()); // 문자열: length(), 배열 length
		for(int i=0; i<e.length(); i++) {
			System.out.println(i + " : " + e.charAt(i));
		} // charAt()은 0부터 시작, ' '도 1자리로 취급
		
		System.out.println("부분 문자열 추출: " + e.substring(7)); // 7이상 ~
		System.out.println("부분 문자열 추출: " + e.substring(7, 11)); // 7이상 11미만
		
		System.out.println("대문자 변경 = " + "Hello".toUpperCase());
		System.out.println("소문자 변경 = " + "Hello".toLowerCase());
		
		// 글자 검색
		System.out.println("문자열 검색: " + e.indexOf("짜")); // 0부터 시작, ' ' 1자리 취급
		System.out.println("문자열 검색: " + e.indexOf("날짜"));
		System.out.println("문자열 검색: " + e.indexOf("요일")); // 대소문자 구분, 검색 내용 없을 경우, -1 반환
	
		System.out.println("문자열 치환: " + e.replace("날짜", "일자"));
		
	}
	
}
