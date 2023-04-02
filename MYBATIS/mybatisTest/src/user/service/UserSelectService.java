package user.service;

import java.util.List;

import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserSelectService implements UserService {

	@Override
	public void execute() {
		System.out.println();
		// Data
		
		// DB
		UserDAO userDAO = UserDAO.getInstance();
		// 메모리에 생성되어있는 UserDAO 객체 주소 반환
		List<UserDTO> list = userDAO.getUserList();
		
		System.out.println("이름\t아이디\t비밀번호");
		for(UserDTO userDTO : list) {
			System.out.println(userDTO.getName() + "\t" + userDTO.getId() + "\t" + userDTO.getPwd());
		} // for
		
		// 
		
	}

}
