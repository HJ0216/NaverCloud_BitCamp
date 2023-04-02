package user.service;

import java.util.Scanner;

import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserInsertService implements UserService {

	@Override
	public void execute() {
		System.out.println();
		
		// Data
		Scanner scan = new Scanner(System.in);
		
		System.out.print("이름 입력:");
		String name = scan.next();
		System.out.print("ID 입력:");
		String id = scan.next();
		System.out.print("PWD 입력:");
		String pwd = scan.next();
		
		UserDTO userDTO = new UserDTO();
		userDTO.setName(name);
		userDTO.setId(id);
		userDTO.setPwd(pwd);
		
		
		// DB
		// UserDAO userDAO = new UserDAO();
		// Singleton 방식을 통해서 SqlFactory를 한 번만 생성해서 재 사용하고자 함
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.insert(userDTO);
		
		System.out.println("데이터를 저장하였습니다.");
		
	}

}
