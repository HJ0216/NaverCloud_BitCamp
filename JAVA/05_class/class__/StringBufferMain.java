package class__;

import java.util.*;

public class StringBufferMain {
	// 클래스에 직접 입력되는 vairable, method는 전역적으로 사용되는 경우에만 작성하기

	int dan;
	// dan의 경우에는 다른 method에서도 사용해야하므로 전역 변수로 선언
	
	public void input() {
		Scanner scan = new Scanner(System.in);
		// scanner class는 input에서만 사용하므로 해당 method 내에서 선언하기
		
		System.out.print("원하는 단을 입력: ");
		dan = scan.nextInt();
		System.out.println("------------------------------------");
		
		scan.close();
	}
	
	
	public void output() {
		StringBuffer buffer = new StringBuffer();
//		StringBuffer buffer = "";
//		"": String type != StringBuffer
		
		for(int i=1; i<10; i++) {
//			System.out.println(dan + "*" + i + "=" + dan*i);
			buffer.append(dan);
//			append(): 가장 끝 자리에 추가, delete()
			buffer.append("*");
			buffer.append(i);
			buffer.append("=");
			buffer.append(dan*i);
			
			System.out.println(buffer.toString()); // StringBuffer -> String
			// StringBuffer는 값이 사라지지 않고 보관됨
			// 2*1=2 2*2=4 2*3=6 2*4=8 2*5=10 2*6=12 2*7=14 2*8=16 2*9=18 -> delete() 필요
			
			buffer.delete(0, buffer.length());
			// 1. buffer append 값 추가
			// 2. buffer에 추가된 값 print
			// 3. buffer에 추가된 값 제거
			// 4. 최종 결과값만 출력
			// 5*1=5(Buffer에 저장 후 출력)
			// 5*1=55*2=10
			// delete 사용 시,
			// 5*1=5(Buffer에 저장 후 출력)
			// 5*1=5(Buffer에서 삭제)
			// 5*2=10(새로이 Buffer에 추가한 값 출력)
			

		}
	}
	
	public static void main(String[] args) {		
		StringBufferMain sbm = new StringBufferMain();
		// 같은 class 내 다른 method 사용을 위한 obj 생성
		sbm.input();
		sbm.output();
	}
	
}

/*

[문제] 구구단
input()
원하는 단을 입력: 

output()
5*1=5
5*2=10
...
5*9=45

 */
