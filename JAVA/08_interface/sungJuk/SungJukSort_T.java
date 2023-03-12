package sungJuk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class SungJukSort_T implements SungJuk {
	Scanner scan = new Scanner(System.in);

	@Override
	public void execute(ArrayList<SungJukDTO> arrayList) {	

		System.out.println();
		this.menu(arrayList);
		// execute() 실행 시, 같은 class의 menu() 호출
		// menu() 실행 후 종료되고나서, execute() 종료
			
	}
	
	public void menu(ArrayList<SungJukDTO> arrayList) {
		int num;

		System.out.println();
		while(true) {
			System.out.println("********************");
			System.out.println("1. 총점으로 내림차순");
			System.out.println("2. 이름으로 오름차순");
			System.out.println("3. 이전 메뉴");
			System.out.println("********************");
			System.out.print("번호: ");
			num = scan.nextInt();
			
			if(num==3) {break;}
			
			Comparator<SungJukDTO> comparator=null;
			// initialize
			
			if(num==1) {
//				Comparator<SungJukDTO> comparator = new Comparator<SungJukDTO>();
				// Interface new X
				comparator = new Comparator<SungJukDTO>() {
					
					@Override
					public int compare(SungJukDTO sjDTO1, SungJukDTO sjDTO2) {
						// Descending Total
						if(sjDTO1.getTotal()<sjDTO2.getTotal()) {return 1;}
						else if(sjDTO1.getTotal()>sjDTO2.getTotal()) {return -1;}
						return 0;
					}
				}; // Comparator method를 통한 interface 구현
				
			} else if(num==2) {
				comparator = new Comparator<SungJukDTO>() {
					@Override
					public int compare(SungJukDTO sjDTO1, SungJukDTO sjDTO2) {
						// Ascending Name
						return sjDTO1.getName().compareTo(sjDTO2.getName());
					}
				}; // Comparator method를 통한 interface 구현
				
			} // else
			else {
				System.out.println("잘못된 번호입니다.");
				continue;
			}
			
			Collections.sort(arrayList, comparator);
			// if num==1: num1의 comparator 실행
			// if num==2: num2의 comparator 실행
			
			new SungJukList().execute(arrayList);
			// Sort된 List 출력을 위한 1회용 객체 생성
			
		}
		
	}
	
}

// 성적 서비스에서 sungJuk.execute(arrayList) arrayList parameter 전달
// execute 실행, arrayList 값 전달