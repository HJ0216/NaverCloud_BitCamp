package sungJuk;

import java.util.ArrayList;
import java.util.Scanner;

public class SungJukService {

	private ArrayList<SungJukDTO> arrayList = new ArrayList<>();
	// ArrayList: Element 추가, 수정, 삭제 가능(add / remove method 활용)
	// return ref_address
	

	public SungJukService() {} // Default Constructor

	Scanner scan = new Scanner(System.in);

	int no;	
	SungJuk sungJuk = null; // Initialize
	
	public void menu() {
		while(true) {
			System.out.println();
			System.out.println("********************");
			System.out.println("\t1. Insert");
			System.out.println("\t2. List");
			System.out.println("\t3. Update");
			System.out.println("\t4. Delete");
			System.out.println("\t5. Sort");
			System.out.println("\t6. Terminate");
			System.out.println("********************");
			System.out.print("  번호:  ");
			no = scan.nextInt();
			
			if(no==6) {
				System.out.println("Terminate Program");
				break;
			} // if: no==6
			
			if(no==1) {
				sungJuk = new SungJukInsert();
//				SungJukInsert sungJukInsert = new SungJukInsert();
				// Class Value_name이 다수 생성 문제
				// -> 다형성 활용((interface)SungJuk sungJuk)
				// no==1일 때마다 SungJukInsert obj memory allocation
			} // if: no==1
			
			else if(no==2) {
				sungJuk = new SungJukList();
			} // else if: no==2
			
			else if(no==3) {
				sungJuk = new SungJukUpdate();
			} // else if: no==3
			
			else if(no==4) {
				sungJuk = new SungJukDelete_T();
			} // else if: no==4
			
			else if(no==5) {
				sungJuk = new SungJukSort_T();
			} // else if: no==5
			
			else {
				System.out.println("Please enter the number betwen 1 and 6");
				continue;
			} // else: Wrong Number
			
			sungJuk.execute(arrayList);
			// SungJukService class에서 arrayList를 생성하였으므로, 해당 arrayList를 sungJuk.execute(arrayList)로 전달
			// SungJukDTO obj가 담긴 arrayList를 sungJuk interface의 abstract method의 parameter로 활용하여 class간 arrayList를 공유할 수 있도록 함

		} // while: menu
		
	} // menu()
	
}
