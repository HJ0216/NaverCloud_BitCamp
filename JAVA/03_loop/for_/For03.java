package for_;

import java.text.*;

public class For03 {

	public static void main(String[] args) {
		int i, sum=0, mul=1;

		DecimalFormat df = new DecimalFormat();
		
		for(i=1; i<11; i++) {
			sum += i;
			mul *= i;
			
			System.out.println("i: " + i + ", sum: " + sum + ", mul: " + df.format(mul));
			// i: 1, sum: 1, mul: 1
			// i: 2, sum: 3, mul: 2
			// i: 3, sum: 6, mul: 6
			// ...
		}
		
	}
	
}
