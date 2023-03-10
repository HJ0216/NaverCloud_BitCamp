package while_; // 지정어를 pkg, class, method 등의 이름으로 사용할 수 없음

public class While01 {

	public static void main(String[] args) {
		int a=0;
		
		while(a<10) {
			a++; // variable의 위치에 따라 return value 확인
			System.out.print(a + "  ");
		} // 1 2 3 ... 10
		
		System.out.println();
		
		
		a = 0;
		while(true) {
			a++;
			if(a>10) break;	// 제어문 위치에 따른 return value 확인
			System.out.print(a + "  ");
		} // 1 2 3 ... 10
		
	}
}
