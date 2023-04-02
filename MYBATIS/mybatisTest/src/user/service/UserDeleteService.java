package user.service;

import java.util.Scanner;

import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserDeleteService implements UserService {

	@Override
	public void execute() {
		// Data
		Scanner scan = new Scanner(System.in);

		System.out.println();
		System.out.print("ID 입력:");
		String id = scan.next();
		
		UserDAO userDAO = UserDAO.getInstance();
		UserDTO userDTO = userDAO.getUser(id);
		
		if(userDTO==null) {
			System.out.println("해당 ID를 찾을 수 없습니다.");
		} else {
			userDAO.getUserDelete(id);
			// userDAO 상 delete method와 userMapper.xml상 SQL delete 실행 구문 id 맞추기
			System.out.println("데이터를 삭제하였습니다.");
		}
	}

}


/*
찾고자 하는 아이디를 입력: aa
아이디가 없습니다.

찾고자 하는 아이디를 입력: 1
데이터를 삭제하였습니다.
*/
