package interface_;

import java.util.*;

public class ComputeService {

	public void menu() {
		Scanner scan = new Scanner(System.in);
		
		Compute cpt=null; // parent obj creation
		
		int num;
		
		while(true) {
			System.out.println();
			System.out.println("********************");
			System.out.println("\t1. Sum");
			System.out.println("\t2. Substract");
			System.out.println("\t3. Multiple");
			System.out.println("\t4. Divide");
			System.out.println("\t5. Terminate");
			System.out.println("********************");
			System.out.print("  번호:  ");
			num = scan.nextInt();
			
			if(num==5) break;

			
			if(num==1) {
//				SumImpl sum = new SumImpl();
				cpt = new SumImpl();
				
			} else if(num==2) {
				cpt = new SubImpl();
				
			} else if(num==3) {
				cpt = new MulImpl();
				
			} else if(num==4) {
				cpt = new DivImpl();
				
			}
			
			
			cpt.disp();
			// The local variable cpt may not have been initialized
			// obj default value: null 지정
			
			
			
		} // while
		
		scan.close();

	} // menu()
		
}



// ref. 05_class project, constructor pkg, MemberService java