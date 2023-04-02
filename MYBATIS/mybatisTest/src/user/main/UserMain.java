package user.main;

import java.util.Scanner;

import user.service.UserService;
import user.service.UserDeleteService;
import user.service.UserInsertService;
import user.service.UserSearchService;
import user.service.UserSearchServiceT;
import user.service.UserSelectService;
import user.service.UserUpdateService;

public class UserMain {

	public void menu() {
		UserService userService=null;
		
		Scanner scan = new Scanner(System.in);
		int num;
		
		while(true) {
			System.out.println();
			System.out.println("************");
			System.out.println("  1. 입력");
			System.out.println("  2. 출력");
			System.out.println("  3. 수정");
			System.out.println("  4. 삭제");
			System.out.println("  5. 검색");
			System.out.println("  6. 종료");
			System.out.println("************");
			System.out.print("번호 입력: ");
			
			num = scan.nextInt();
			
			if(num==6) {break;}
			
			if(num==1) {
				// UserInsertService userInsertService = new UserInsertService();
				// 결합도 100%의 1:1 관계
				userService = new UserInsertService();
				// 다형성을 통해 결합도 낮추기
			} else if(num==2) {
				userService = new UserSelectService();
			} else if(num==3) {
				userService = new UserUpdateService();
			} else if(num==4) {
				userService = new UserDeleteService();
			} else if(num==5) {
				userService = new UserSearchServiceT();
			} 
			userService.execute();
			// 동일한 기능을 수행하는 함수를 interface의 추상 method로 선언하여 구현하게 함
			// 번호 입력에 따라 Insert, Select, Update, Delete, Search에서 각기 다르게 구현된 execute()가 실행됨
			// 이를 통해 class마다 함수 이름을 외우지 않고 함수를 사용하여 통일성을 높일 수 있음
			
		} // while

	}
	
	public static void main(String[] args) {
		UserMain userMain = new UserMain();
		userMain.menu();
		System.out.println("프로그램을 종료합니다.");
	}
}
