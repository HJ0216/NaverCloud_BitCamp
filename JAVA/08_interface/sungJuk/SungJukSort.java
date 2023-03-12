package sungJuk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class SungJukSort implements SungJuk {

	Scanner scan = new Scanner(System.in);
	
	@Override
	public void execute(ArrayList<SungJukDTO> arrayList) {	
		int num;
		
		whole:
		while(true) {
		System.out.println("********************");
		System.out.println("1. 총점으로 내림차순");
		System.out.println("2. 이름으로 오름차순");
		System.out.println("3. 이전 메뉴");
		System.out.println("********************");
		System.out.print("번호: ");
		num = scan.nextInt();
		
		
		if(num==1) {
			Collections.sort(arrayList);
			
			System.out.println("After Sorting(total_decending): ");
			System.out.println("번호\t이름\t국어\t영어\t수학\t총점\t평균");
			for(SungJukDTO sungJukDTO : arrayList) {
				System.out.println(sungJukDTO + "  ");
			}
			break whole;
		} // if: Descending_total
		
		else if(num==2) {
			System.out.println("After Sorting(name_ascending): ");

			Comparator<SungJukDTO> comparator = new Comparator<SungJukDTO>() {
				@Override
				public int compare(SungJukDTO sjDTO1, SungJukDTO sjDTO2) {
					return sjDTO1.getName().compareTo(sjDTO2.getName());
				}
			};

			Collections.sort(arrayList, comparator);
			// Sort: Comparator(Not SungJukDTO comparable)
			
			System.out.println("번호\t이름\t국어\t영어\t수학\t총점\t평균");
			for(SungJukDTO sungJukDTO : arrayList) {
				System.out.println(sungJukDTO + "  ");
			}
			
			break whole;
			
		} // else if: Ascending_name
		
		else if(num==3) {return;} // SungJukSort terminate
		else {
			System.out.println("잘못된 번호를 입력하였습니다.\n");
			continue;
		} // else: Wrong Number
		
	} // while: whole_loop
		
	} // execute()

} // class
