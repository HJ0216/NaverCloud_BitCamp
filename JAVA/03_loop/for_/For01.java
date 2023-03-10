package for_;

import java.text.*;

public class For01 {

	public static void main(String[] args) {

		DecimalFormat df = new DecimalFormat("00");
		
//		for(int i=1; i<=10; i++) {
//			System.out.println(df.format(i) + ": Hello Java:)");
//		} // for
//		
//		System.out.println("i = " + i);
		// lv: 해당 method 이외에서 lv 사용 시, cannot be resolved to a variable error occur

		int i; // lv의 사용범위 넓히기
		
		
		for(i=10; i>0; i--) { // 처음부터 -1이 아닌 다음 연산부터 -1: 후행 연산자
			System.out.print(df.format(i) + " "); // 10 09 08 07 ... 01
		} // for_i
	
		System.out.println();

//		for(char ch='A'; ch<='Z'; ch++) {
//			System.out.print(ch + "  ");
//		} // for
		
		for(int ch='A'; ch<='Z'; ch++) {
			System.out.print((char)ch + "  "); // A B C ... Z
		} // for
			
	}
	
}
