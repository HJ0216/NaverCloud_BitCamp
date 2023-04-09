package user.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import lombok.Setter;
import user.bean.UserDTO;
import user.dao.UserDAO;
@Setter
public class UserUpdateService implements UserService {
	private UserDTO userDTO = null;
	private UserDAO userDAO = null;


	@Override
	public void execute() {
		// 1. DATA
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String name = null;
		String id = null;
		String pwd = null;

		List<UserDTO> list = userDAO.getUserList();
		
		int sw = 0;
		try {
			System.out.print("수정할 아이디 입력: ");
			id = br.readLine();
			
			for(UserDTO userDTO : list) {
				if(id.equals(userDTO.getId())) {
					sw = 1;
					System.out.print("변경할 이름 입력:");
					name = br.readLine();
					userDTO.setName(name);
					System.out.print("변경할 비밀번호 입력:");
					pwd = br.readLine();
					userDTO.setPwd(pwd);

					// 2. DB
					userDAO.userUpdate(userDTO);
					System.out.println(name + "님의 데이터를 수정하였습니다.");
					
					return;
				} // if
			} // for
						
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		if(sw==0) {System.out.println("일치된 검색 결과 없음");}

	}

}
