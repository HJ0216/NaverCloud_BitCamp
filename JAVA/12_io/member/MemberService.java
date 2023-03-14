package member;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberService {
	
	Scanner scan = new Scanner(System.in);
	
	public MemberService () {} // Default Constructor
	
	private List<MemberDTO> list = new ArrayList<>();
	// 등록 시 마다 파일을 저장할 경우, 과정의 중복이 많이 발생하므로
	// 일정량을 arrayList에 임시저장 후 일정량이 모이면 파일로 추출
	// MemberDTO에서 name, age, phone, address mDTO에 저장
	// M
	
	int input_num;
	Member member = null; // Initialize
	public void menu() {
		while(true) {
			System.out.println("********************");
			System.out.println("  1. 등록");
			System.out.println("  2. 출력");
			System.out.println("  3. 핸드폰 검색");
			System.out.println("  4. 이름으로 오름차순");
			System.out.println("  5. 파일 저장");
			System.out.println("  6. 파일 읽기");
			System.out.println("  7. 종료");
			System.out.println("********************");
			System.out.print("  번호 :");
			
			input_num = scan.nextInt();
			
			if(input_num==7) {break;}

			if(input_num==1) {
				member = new MemberInsert();
				// 결합도가 높을 경우, 코드 작성의 유연성이 떨어질 수 있음 -> 다형성 활용
			}
			
			else if(input_num==2) {
				member = new MemberPrint();
			}
			
			else if(input_num==3) {
				member = new MemberPhoneSearch();
			}
			
			else if(input_num==4) {
				member = new MemberNameAsc();
			}
			
			else if(input_num==5) {
				member = new MemberFileOutput();
			}
			
			else if(input_num==6) {
				member = new MemberFileInput();
			}
			
			else {
				System.out.println("Please enter the number betwen 1 and 6\n");
				continue;
			} // else: Wrong Number
			
			member.execute(list);
			// List의 ref_address를 공유
			
		}
		
	}
}
