package user.service;

import java.util.List;

import lombok.Setter;
import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserSelectService implements UserService {
	@Setter
	private UserDAO userDAO = null;
	

	public void execute() {
		List<UserDTO> list = userDAO.getUserList();
/*		
		class Test {
			int a;
			double b;
			Sample sample = new Sample();
		}
		
		메모리에 Test가 생성되기 위해 a, b, sample이 먼저 로드되고 생성자 호출		
		
		UserSelectService 메모리 로드
		userDAO 생성
		list obj 생성 (userDAO null Exception 발생)
		
		execute() 내부에 list 생성을 선언하여, null Exception 방지

*/		
		
		System.out.println("\n이름\t아이디\t패스워드");
		for(UserDTO userDTO : list) {
			System.out.println(userDTO.getName() + "\t"
							 + userDTO.getId() + "\t"
							 + userDTO.getPwd());
			
		}
		
	}

}
