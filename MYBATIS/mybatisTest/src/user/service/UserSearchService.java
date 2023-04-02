package user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserSearchService implements UserService {

	@Override
	public void execute() {
		System.out.println();
		System.out.println("  1. ID 입력");
		System.out.println("  2. 이름 입력");
		Scanner scan = new Scanner(System.in);
		int answer = scan.nextInt();
		
		UserDAO userDAO = UserDAO.getInstance();
		
		List<UserDTO> list = null;
		
		if(answer==1) {
			System.out.println("찾고자 하는 ID 입력: ");
			String id = scan.next();
			Map<String, String> map = new HashMap<>();
			map.put("col_name", "ID");
			map.put("value", id);
			list = userDAO.search(map);
			
		} else if(answer==2) {
			System.out.println("찾고자 하는 이름 입력: ");
			String name = scan.next();
			Map<String, String> map = new HashMap<>();
			map.put("col_name", "NAME");
			map.put("value", name);
			list = userDAO.search(map);
		}
		
		System.out.println();
		System.out.println("이름\t아이디\t비밀번호");
		for(UserDTO userDTO : list) {
			System.out.println(userDTO.getName() + "\t" + userDTO.getId() + "\t" + userDTO.getPwd());
		} // for

	}
	
}

/*
1. 이름 검색
2. 아이디 검색
번호 입력: 1

1번인 경우
찾고자하는 이름 입력: LIKE %홍%

이름	아이디	비밀번호
홍길동
홍당무

2번인 경우
찾고자 하는 아이디 입력: LIKE %n%

이름	아이디	비밀번호
		hong
		conan

userDAO.search() 호출 함수 1개

*/
