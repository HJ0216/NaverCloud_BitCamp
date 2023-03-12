package interface_;

import java.util.Scanner;

public class DivImpl implements Compute {
	private int x, y;
	Scanner scan = new Scanner(System.in);
	
	public DivImpl() {
		System.out.print("Enter x: ");
		x = scan.nextInt();
		
		System.out.print("Enter y: ");
		y = scan.nextInt();
				
	}

	@Override
	public void disp() {
		System.out.println(x + " / " + y  + " = " + String.format("%.2f", (x/(y*1.0))));
	}
}
