package member;

import java.util.List;

public class MemberPrint implements Member {

	public void execute(List<MemberDTO> list) {
		System.out.println("\n이름\t나이\t핸드폰\t\t주소");

		for(MemberDTO mDTO : list) {
			System.out.println(mDTO); // using toString Override
		} // for: getter print
	};
}
