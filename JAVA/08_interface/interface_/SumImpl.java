package interface_;

import java.util.*;

public class SumImpl implements Compute {
	private int x, y;
	Scanner scan = new Scanner(System.in);
	
	public SumImpl() {
		System.out.print("Enter x: ");
		x = scan.nextInt();
		
		System.out.print("Enter y: ");
		y = scan.nextInt();
				
	}

	@Override
	public void disp() {
		System.out.println(x + " + " + y  + " = " + (x+y));
	}
}
