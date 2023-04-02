package user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserUpdateService implements UserService {

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("ID 입력: ");
		String id = scan.next();
		
		UserDAO userDAO = UserDAO.getInstance();
		// 메모리에 생성되어있는 UserDAO 객체 주소 반환
		UserDTO userDTO = userDAO.getUser(id);
		
		if(userDTO==null) {
			System.out.println("해당 ID를 찾을 수 없습니다.");
			return;
			// else를 사용하지 않을 경우, if문 종료 후 return으로 함수를 종료시킬 수 있음
		}
//		} else {
//			System.out.println(userDTO.getName() + "\t" + userDTO.getId() + "\t" + userDTO.getPwd());
//		}
		
		System.out.println();
		System.out.println("이름\t아이디\t비밀번호");
		System.out.println(userDTO.getName() + "\t" + userDTO.getId() + "\t" + userDTO.getPwd());
		
		System.out.println("변경할 이름 입력: ");
		String name = scan.next();		
		System.out.println("변경할 비밀번호 입력: ");
		String pwd = scan.next();		

		// mapper에 data를 1개밖에 못넘기므로 map or DTO에 담아서 보내기
//		userDTO.setName(name);
//		userDTO.setPwd(pwd);
//		userDAO.getUserUpdate(userDTO);
		
		Map<String, String> map = new HashMap<>();
		// <>를 Object으로 받을 경우, 모든 데이터 타입 입력 가능
		map.put("name", name);
		map.put("id", id);
		map.put("pwd", pwd);
		
		userDAO.update(map);
		
		System.out.println("데이터를 수정하였습니다.");
	}

}


/*
찾고자 하는 아이디 입력: angel
아이디가 존재하지 않습니다.

찾고자하는 아이디 입력: hong

이름	아이디	비밀번호
홍길동 	hong	111

변경할 이름 입력: 홍당무
변경할 비밀번호 입력: 333

데이터를 수정하였습니다.

sqlSession.selectList() <- 객체 여러개 list 반환
sqlSession.selectObject() <- 객체 1개 반환

 */
 