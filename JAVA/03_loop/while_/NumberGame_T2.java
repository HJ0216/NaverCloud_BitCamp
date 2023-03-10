package while_;

import java.util.Scanner;

public class NumberGame_T2 {

	public static void main(String[] args) {
		int com;
		int user=0;
		int count=0;
		
		com = (int)(Math.random()*100) + 1;
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the number between 1 and 100 (" + com + ") : ");
		user = scan.nextInt();

		while(user>100 || user<1) {
			System.out.println("You entered: " + user + "\nPlease Enter the number between 1 and 100");
			user = scan.nextInt();				
		} // while: if the number is entered incorrectly
		
		do {++count;
		// count++일 경우, 가장 처음 입력값이 count에 포함되지 않음
			if(user==com) {
				System.out.println("Correct");
				break;}
			else if(user>com) {
				System.out.println("Under " + user);
				user = scan.nextInt();
				}
			else if(user<com) {
				System.out.println("Upper " + user);
				user = scan.nextInt();
				}
		} while(user!=com); // do-while
		
		System.out.println("You tried: " + count);
		
		scan.close();

	}
	
}
