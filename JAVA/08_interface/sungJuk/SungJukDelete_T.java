package sungJuk;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class SungJukDelete_T implements SungJuk {
	Scanner scan = new Scanner(System.in);
	
	@Override
	public void execute(ArrayList<SungJukDTO> arrayList) {
	// arrayList: @ArrayList<SungJukDTO>_ref_address (Not arrList_element)
	// arrayList = {SungJukDTO@100, @200, @300} -> sjDTO의 @ref_address 저장
	// 
		System.out.print("삭제할 이름 입력: ");
		String name_del = scan.next();

/*		int count=0;
		for(SungJukDTO sungJukDTO : arrayList) {
			if(sungJukDTO.getName().equals(name_del)) {
				arrayList.remove(sungJukDTO);
				count++;
			}
		}
		// ConCurrentModificatoinException
//		for문 실행 시, arrayList가 처음에 고정
//		remove 시, size()가 변경되므로 Exception 발생
*/		
		
		
		
		
/*		int count=0;
		for(int i=0; i<arrayList.size(); i++) {
			if(arrayList.get(i).getName().equals(name_del)) {
				arrayList.remove(i);
				count++;
			}
			// arrayList의 [0][1]...의 getName()을 비교해야 함
		}

		// ConCurrentModificatoinException
		for문 실행 시, arrayList.size()가 계속 계산되어 Exception이 발생
		arrayList.remove() 실행 시,
		i=0
		arrayList[0] = aaa;
		arrayList[1] = aaa;
		arrayList[2] = bbb;
		arrayList[3] = aaa;
		-> idx 변경
		i=1
		arrayList[0] = aaa;
		arrayList[1] = bbb;
		arrayList[2] = aaa;
		-> idx=0인 aaa data를 삭제할 수 없음
		
*/
		
		int count=0;
		
		Iterator<SungJukDTO> iterator = arrayList.iterator();
		// iterator는 idx num과 무관

		while(iterator.hasNext()) {
			SungJukDTO sjDTO = iterator.next();
			// arrayListd에 항목을 꺼내서 sjDTO에 보관
			// iterator 다음 항목으로 이동
			
			if(sjDTO.getName().equals(name_del)) {
				iterator.remove();
				// remove(): iterator가 remove 전 꺼내놓은 항목(Buffer에 저장한 항목)을 제거
				// Buffer에 저장된 값을 제거하는 것이므로 next()를 사용하지 않으면 remove()를 사용할 수 없음
				count++;
			}
			
		}
		
		if(count==0) {System.out.println("There is no Memeber in the list.");}
		else {System.out.println(count + " member deleted");}

	}
	
}
