package user.service;

import java.util.List;

import lombok.Setter;
import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserSelectService implements UserService {
	@Setter
	private UserDAO userDAO = null;
	
	public void execute() {
		System.out.println();
		
		List<UserDTO> list = userDAO.getUserList();
		
		System.out.println("이름\t아이디\t패스워드");
		for(UserDTO userDTO : list) {
			System.out.println(userDTO.getName() + "\t"
							 + userDTO.getId() + "\t"
							 + userDTO.getPwd());
			
		}
		
	}

}
