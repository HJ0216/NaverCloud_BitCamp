package user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserSearchServiceT implements UserService {

	@Override
	public void execute() {
		System.out.println();
		System.out.println("  1. ID 입력");
		System.out.println("  2. 이름 입력");
		Scanner scan = new Scanner(System.in);
		int answer = scan.nextInt();
		
		
		String columnName = null;
		String value = null;
		
		if(answer==1) {
			System.out.println("찾고자 하는 ID 입력: ");
			value = scan.next();
			columnName = "ID";
		
		} else if(answer==2) {
			System.out.println("찾고자 하는 이름 입력: ");
			value = scan.next();
			columnName = "NAME";
		}
		
		Map<String, String> map = new HashMap<>();
		// map<key, value> name 맞추기
		map.put("columnName", columnName);
		map.put("value", value);

		UserDAO userDAO = UserDAO.getInstance();
		List<UserDTO> list = userDAO.searchT(map);

		System.out.println();
		System.out.println("이름\t아이디\t비밀번호");
		for(UserDTO userDTO : list) {
			System.out.println(userDTO.getName() + "\t" + userDTO.getId() + "\t" + userDTO.getPwd());
		} // for

	}

}
