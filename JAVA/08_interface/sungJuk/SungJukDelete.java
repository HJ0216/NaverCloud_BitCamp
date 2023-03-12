package sungJuk;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class SungJukDelete implements SungJuk {
	Scanner scan = new Scanner(System.in);
	
	@Override
	public void execute(ArrayList<SungJukDTO> arrayList) {
		System.out.println("삭제할 이름 입력: ");
		String name_del = scan.next();
		
		int count=0;				
		for(Iterator<SungJukDTO> iterator = arrayList.iterator(); iterator.hasNext();) {
			// 조건식이 false일 경우, for문이 종료, 증감식 생략
			SungJukDTO sjDTO = iterator.next();
			if(sjDTO.getName().equals(name_del)) {
				iterator.remove();
				count++;
			}
		} System.out.println(count + "건을 삭제하였습니다.");

		if(count==0) {
			System.out.println("회원 정보가 없습니다.");
			return;
		} 			
		
		
		// Sol2
		
//		int count = 0;
//	    Iterator<SungJukDTO> iterator = arrayList.iterator();
//	    // Method를 통한 Interface 구현: iterator()
//	    // iterator(): Returns an array containing all of the elements in this collection.
//	    while (iterator.hasNext()) {
//	        if (name_del.equals(iterator.next().getName())) {
//	        	// String: ref_address(==), value(equals)
//	            iterator.remove();
//	            count++;
//	        }
//	    }
//
//	    if (count == 0) {
//	        System.out.println("회원 정보가 없습니다.");
//	        return;
//	    }
//
//	    System.out.println(count + "건을 삭제하였습니다.");

		
	} // execute()

}
