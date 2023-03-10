package while_;

public class DoWhile {

	public static void main(String[] args) {
		int a = 0;
		
		do {a++;
			System.out.print(a + "\t"); // a: 출력 전, 0 -> 1
		} while(a<10); // ; 유의
		
		System.out.println();
		System.out.println();
		
		char ch = 'A';
		int count = 0;
		
		do {System.out.print((ch++) + "\t");
		// ch: 출력 후, A -> B
			count++;

			if(count%7 == 0) {System.out.println();}
			// n의 배수: x%n==0
		} while(ch<='Z'); // do-while
		
	}
	
}
