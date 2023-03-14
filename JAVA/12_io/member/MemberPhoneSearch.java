package member;

import java.util.List;
import java.util.Iterator;
import java.util.Scanner;

public class MemberPhoneSearch implements Member {

	Scanner scan = new Scanner(System.in);
	
	@Override
	public void execute(List<MemberDTO> list) {
		System.out.print("\n휴대폰 번호 입력: ");
		String search_num = scan.next();
		
		for(Iterator<MemberDTO> iterator = list.iterator(); iterator.hasNext();) {
			// 조건식이 false일 경우, for문이 종료, 증감식 생략
			MemberDTO mDTO = iterator.next();
			if(mDTO.getPhone_num().equals(search_num)) {
				System.out.println("\n이름\t나이\t핸드폰\t주소");				
				System.out.println(mDTO);
				// Sort된 List 출력을 위한 1회용 객체 생성
			} // if
			else {System.out.println("There is no Memeber in the list.");}
		} // for
		
		
/*		int sw=0;
 * 		for(MemberDTO memberDTO : list){
 * 			if(memberDTO.getPhone_num().equal(phone)){
 * 			System.out.println(memberDTO)
 *			sw=1;
 * 		}
 * }
*/
		
		
		
		
	} // execute()

} // class
