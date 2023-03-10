package for_;

import java.util.*;

public class For04 {

	public static void main(String[] args) {
		int x, y;
		int mul=1;
		
		Scanner scan  = new Scanner(System.in);
		System.out.print("Please Enter x: ");
		x = scan.nextInt();

		System.out.print("Please Enter y: ");
		y = scan.nextInt();
		
		for(int i =0; i<y; i++) {
			mul *= x; // 곱셈 시, 초기값 설정 유의
		}
		
		System.out.println(x + "의 " + y + "승은 " + mul);
		
		
		scan.close();
	}
	
}
