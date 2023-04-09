package user.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import lombok.Setter;
import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserDeleteService implements UserService {
	@Setter
	private UserDTO userDTO = null;
	@Setter
	private UserDAO userDAO = null;
	

	@Override
	public void execute() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String id = null;

		List<UserDTO> list = userDAO.getUserList();
				
		try {
			System.out.print("삭제할 아이디 입력: ");
			id = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int sw = 0;
		for(UserDTO userDTO : list) {
			if(id.equals(userDTO.getId())) {

				// DB
				userDAO.userDelete(id);
				System.out.println(id + "삭제 완료");
				return;
				
			} // if
			
		} // for
		
		System.out.println("일치된 검색 결과 없음");

		
	}

}
