package user.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Setter;
import user.bean.UserDTO;
import user.dao.UserDAO;

@Component
public class UserDeleteService implements UserService {
	@Autowired
	private UserDTO userDTO = null;
	@Autowired
	private UserDAO userDAO = null;
	

	@Override
	public void execute() {
		// 1. DATA
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제할 아이디 입력: ");
		String id = scan.next();
		
		// 2. DB
		UserDTO userDTO = userDAO.getUser(id);
		
		if(userDTO==null){
			System.out.println("검색결과 없음");
			return;
		}
		
		System.out.println(userDTO.getName() + "\t"
				+ userDTO.getId() + "\t"
				+ userDTO.getPwd());
	 
	 	userDAO.userDelete(id);
	 	
	 	} // execute()

/*	
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
	
	} // execute()
*/
}
