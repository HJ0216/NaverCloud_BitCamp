package multi_;

import java.text.DecimalFormat;

public class Multifor03 {

	public static void main(String[] args) {
		int dan;
		int i;
		
		DecimalFormat df = new DecimalFormat("00");
		
		for(dan=1; dan<10; dan++) {
			for(i=2; i<10; i++) {
				System.out.print(df.format(i) + "*" +  df.format(dan) + "=" + df.format(dan*i) + "\t");
			} // for i
			System.out.println();
		} // for dan				

	}
	
}
