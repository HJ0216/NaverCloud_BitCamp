package for_;

import java.util.*;

public class Game_T {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int com = 0;
		int user = 0;
		
		System.out.print("insert coin: ");
		int money = scan.nextInt();
		
		for(int i=0; i<money/300; i++) {
			com = (int) (Math.random()*3) + 1;
			
			System.out.println("Enter the number, 1(rock) 2(scissors) 3(paper)");
			user = scan.nextInt();
		
		if(com==1) {
			if(user==1) {
				System.out.println("com: rock, user: rock");
				System.out.println("Draw");
			} // if: user==1
			if(user==2) {
				System.out.println("com: rock, user: scissors");
				System.out.println("You Loss");
			} // if: user==2
			if(user==3) {
				System.out.println("com: rock, user: paper");
				System.out.println("You Win");
			} // if: user==3
		} // if: com==1
		
		
		if(com==2) {
			if(user==1) {
				System.out.println("com: scissors, user: rock");
				System.out.println("You Win");
			} // user==1
			if(user==2) {
				System.out.println("com: scissors, user: scissors");
				System.out.println("Draw");
			} // user==2
			if(user==3) {
				System.out.println("com: scissors, user: paper");
				System.out.println("You Loss");
			} // user==3
		} // com==2
			

		if(com==3) {
			if(user==1) {
				System.out.println("com: paper, user: rock");
				System.out.println("You Loss");
			} // user==1
			if(user==2) {
				System.out.println("com: paper, user: scissors");
				System.out.println("You win");
			} // user==2
			if(user==3) {
				System.out.println("com: paper, user: paper");
				System.out.println("Draw");
			} // user==3
		} // com==3

		} // for: game over

		scan.close();
	}
}