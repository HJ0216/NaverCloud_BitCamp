package user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserDTO;
import user.dao.UserDAO;

@Service // 객체 생성
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void write(UserDTO userDTO) {
		userDAO.save(userDTO);		
	}

	@Override
	public List<UserDTO> list() {
		return userDAO.findAll();
	}

	@Override
	public String isExistId(String id) {
		
		Optional<UserDTO> userDTO = userDAO.findById(id);
		// Optional Class: Optional: 'null일 수도 있는 객체'를 감싸는 일종의 Wrapper class
		// findById = SELECT * FROM USERTABLE WHERE ID=?
		System.out.println(userDTO); // id가 없을 경우, Optional.empty() 출력
		
		// if(userDTO == null) ← Optional에 의해 null이 반환되지 않으므로 사용 불가
		
		if(userDTO.isPresent()) {
			return "exist";
		} else {
			return "non_exist";
		}

		// userDAO는 interface이므로 method 사용 불가 ▶ JpaRepository method 사용
		// Type mismatch: cannot convert from Optional<UserDTO> to String
		// 아이디를 찾을 경우, 없을 때 null을 반환하는데 이 때, 'null일 수도 있는 객체'를 감싸는 일종의 Wrapper 클래스, Optional 사용
		// = NullPointException를 compile 시 방지		
		
	}

}
