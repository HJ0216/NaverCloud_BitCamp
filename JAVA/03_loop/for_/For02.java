package for_;

import java.util.*;
import java.text.*;

public class For02 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Please Enter the number that you want to check the multiplication table: ");
		int i = scan.nextInt();
		
		DecimalFormat df = new DecimalFormat("00");
		
		
		for(int i2 = 1 ; i2<10; i2++) {
			System.out.println(df.format(i) + "*" + df.format(i2) + "=" + df.format(i*i2));
		}
		// 03*01=03
		// 03*02=06
		// ...
		// 03*09=27
		

		scan.close();
		
	}

}
