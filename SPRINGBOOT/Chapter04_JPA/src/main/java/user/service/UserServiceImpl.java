package user.service;

import java.util.List;
import java.util.Map;
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
		// 기존의 record가 없을 경우, save 메서드 사용 시 insert
		// 기존의 record가 있을 경우, save 메서드 사용 시 update
		// record가 insert되는 순서: 임의적(순서가 필요할 경우, sort 필요)
	}


	@Override
	public String isExistId(String id) {
		
		Optional<UserDTO> userDTO = userDAO.findById(id);
		// Optional Class: Optional: 'null일 수도 있는 객체'를 감싸는 일종의 Wrapper class
		// findById = SELECT * FROM USERTABLE WHERE ID=?
		System.out.println(userDTO); // id가 없을 경우, Optional.empty 출력
		
		// if(userDTO == null) ← Optional에 의해 null이 반환되지 않으므로 사용 불가
		
		if(userDTO.isPresent()) {
			return "exist";
		} else {
			return "non_exist";
		}

		// userDAO는 interface이므로 method 사용 불가 ▶ JpaRepository method 사용
		// Type mismatch: cannot convert from Optional<UserDTO> to String
		// 아이디를 찾을 경우, 없을 때 null을 반환하는데 이 때, 'null일 수도 있는 객체'를 감싸는 일종의 Wrapper 클래스, Optional 사용
		// 실행 시, 발생할 수 있는 NullPointException을 방지		
		
	}

	
	
	
	
	@Override
	public List<UserDTO> list() {
		return userDAO.findAll();
	}

	
	// Query Method
	/*
	@Override
	public List<UserDTO> search(Map<String, String> map) {
		String searchOpt = map.get("searchOpt");
		String keyword = map.get("keyword");
		
		if(searchOpt.equals("name")) {
			return userDAO.findByNameContaining(keyword);
		} else {
			return userDAO.findByIdContaining(keyword);
		}

	}
	*/
	

	
	// @Query: queryName 임의 지정 가능
	@Override
	public List<UserDTO> search(Map<String, String> map) {
		String searchOpt = map.get("searchOpt");
		String keyword = map.get("keyword");
		
		if(searchOpt.equals("name")) {
			return userDAO.getUserSearchName(keyword);
		} else {
			return userDAO.getUserSearchId (keyword);
		}

	}
	
	
	

	
	@Override
	public Optional<UserDTO> getUserInfo(String searchId) {
		
		Optional<UserDTO> userDTO = userDAO.findById(searchId);
		System.out.println(userDTO);
		// id가 없을 경우, Optional.empty 출력

		return userDTO;

	}

	
	
	
	
	@Override
	public void delete(String id) {
		userDAO.deleteById(id);
		// SELECT * FROM TABLENAME WHERE ID=?
		// DELETE FROM TABLENAME WHERE ID=?
		// : deleteById(): 내부적으로 findById()를 수행 후 delete 실행
		// (만일, findById()가 Optional.empty라면, EmptyResultDataAccessException 발생)
		
		// userDAO.delete();
		// findById()를 수행하지 않고 delete 수행(매개변수 userDTO)
		// id와 pwd를 넘겨서 모두 일치하는 경우에 삭제하고자 할 때, DTO를 이용하여 delete 쿼리 메서드 사용
	}
	
}

