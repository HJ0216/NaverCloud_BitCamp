package for_;

import java.util.Scanner;

public class AddGame_T {

	public static void main(String[] args) {
		int a, b, answer;
		int count = 0;
		
		Scanner scan = new Scanner(System.in);

		// Number of quizzes
		for(int i=0; i<5; i++){
			a = (int)(Math.random()*90+10);
			b = (int)(Math.random()*90+10);
			
			// Retry
			for(int j=1; j<=2; j++){
				System.out.print("[" + (i+1) + "]" + " " + a + " + " + b + " = ");
				answer = scan.nextInt();
				
				// Correct
				if(answer == (a+b)){
					System.out.println("Correct:)");
					count++;
					break; // for j
				
				// Wrong
				} else {
					if(j==1){
						System.out.println("Wrong");
					} else if(j==2){
						System.out.println("Wrong, the answer is: " + (a+b));
					}
				} // else
			} // for j
		} // for i
		 
		 System.out.println();
		 System.out.println("Total: " + count + "/5, Score: " + (count*20) + "점");


		 scan.close();
	}
}
