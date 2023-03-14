package member;

import java.util.List;
import java.util.Scanner;

import java.util.Comparator;
import java.util.Collections;

public class MemberNameAsc implements Member {

	Scanner scan = new Scanner(System.in);

	@Override
	public void execute(List<MemberDTO> list) {
		
		// Comparator
//		Comparator<MemberDTO> comparator = new Comparator<MemberDTO>() {
//			
//			@Override
//			public int compare(MemberDTO mDTO1, MemberDTO mDTO2) {
//				return mDTO1.getName().compareTo(mDTO2.getName());
//			} // compareTo
//
//		};
//
//		Collections.sort(list, comparator);

		
		// Comparable
		Collections.sort(list);
		// MemberDTO에서 comparable<MemberDTO>를 활용한 이름 오름차순 정렬
		// comparable은 collections.sort(list)만 하면 끝
		// Comparable을 사용하지 않고 저장된 파일을 Comparable로 불러오면 
		// java.io.InvalidClassException:
		
		System.out.println("\n이름\t나이\t핸드폰\t주소");
		for(MemberDTO mDTO : list) {
			System.out.println(mDTO + "  ");
		}
		
	} // execute

} // class
