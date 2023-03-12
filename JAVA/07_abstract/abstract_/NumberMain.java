package abstract_;

import java.text.*;
import java.util.*;

public class NumberMain {

	public static void main(String[] args) {
//		NumberFormat nf = new NumberFormat();
		// abstract class new 연산자 사용 불가(Cannot instantiate the type NumberFormat)
		
		NumberFormat nf1 = new DecimalFormat();
		// subClass(abstract class)를 구현한 class를 이용하여 new 사용
		System.out.println(nf1.format(12345678.456789));
		System.out.println(nf1.format(12345678));
		System.out.println();
		
		NumberFormat nf2 = new DecimalFormat("#,###.##원");
		// subClass(abstract class)를 구현한 class를 이용하여 new 사용
		System.out.println(nf2.format(12345678.456789));
		System.out.println(nf2.format(12345678));
		System.out.println();

		NumberFormat nf3 = new DecimalFormat("#,###.00원");
		// subClass(abstract class)를 구현한 class를 이용하여 new 사용
		System.out.println(nf3.format(12345678.456789));
		System.out.println(nf3.format(12345678));
		System.out.println();
		
//		NumberFormat nf4 = NumberFormat.getInstance();
		// Using Method abstract class 구현
		// new 연산자 대신 method 직접 구현
		NumberFormat nf4 = NumberFormat.getCurrencyInstance(); // ₩
//		nf4.setMaximumFractionDigits(2); // 최대 소수 이하 2째자리
		nf4.setMinimumFractionDigits(2); // 최소 소수 이하 2째자리
		System.out.println(nf4.format(12345678.456789));
		System.out.println(nf4.format(12345678));
		System.out.println();

		NumberFormat nf5 = NumberFormat.getCurrencyInstance(Locale.US); // $
		nf5.setMaximumFractionDigits(2); // 최대 소수 이하 2째자리
		nf5.setMinimumFractionDigits(2); // 최소 소수 이하 2째자리
		System.out.println(nf5.format(12345678.456789));
		System.out.println(nf5.format(12345678));
		System.out.println();
		
	}
}
