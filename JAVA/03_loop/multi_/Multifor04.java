package multi_;

import java.text.*;

public class Multifor04 {

	public static void main(String[] args) {
		int dan, i;
		
		DecimalFormat df = new DecimalFormat("00");
		
		for(int w = 2; w <= 8; w+=3) {
			for(i = 1; i<=9; i++) {
				for(dan = w; dan<=w+2; dan++) {
					if(dan != 10) {System.out.print(df.format(dan) + " * " + df.format(i) +" = " + df.format(dan*i) + "  ");}
					// or dan<=w+2 -> for(dan = w; dan <= w+2 && dan < 10; dan++)
				} // for i
				System.out.println();
			} // for dan
			System.out.println();
		} // for w
	}

}
