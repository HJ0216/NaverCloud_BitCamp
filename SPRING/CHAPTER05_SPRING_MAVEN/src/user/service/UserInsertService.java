package user.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import user.bean.UserDTO;
import user.dao.UserDAO;

@Service
public class UserInsertService implements UserService {
	@Autowired
	private UserDTO userDTO = null;
	@Autowired
	private UserDAO userDAO = null;

	
	@Override
	public void execute() {
		// 1. Data
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		String name = null;
		String id = null;
		String pwd = null;
		
		try {
			System.out.print("이름 입력: ");
			name = br.readLine();
			
			System.out.print("ID 입력: ");
			id = br.readLine();
			
			System.out.print("PWD 입력: ");
			pwd = br.readLine();
			
			userDTO.setName(name);
			userDTO.setId(id);
			userDTO.setPwd(pwd);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		// 2. DB
		userDAO.userInsert(userDTO);
		System.out.println(name + "님의 데이터를 저장하였습니다.");
		
	}

}
