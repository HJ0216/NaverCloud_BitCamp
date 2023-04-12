package user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserDTO;
import user.dao.UserDAO;

@Service // @Component보다 구체적으로 기재
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public void write(UserDTO userDTO) {
		userDAO.write(userDTO);
	}

	@Override
	public List<UserDTO> getUserList() {
		return userDAO.getUserList();
	}

	@Override
	public String isExistId(String id) {
		UserDTO userDTO = userDAO.getUser(id);
		if(userDTO == null) {
			return "Non_Exist";
		} else {
			return "Exist";
		}
	}

	@Override
	public UserDTO getUser(String id) {
		return userDAO.getUser(id);
//		UserDTO userDTO = userDAO.getUser(id);
//		return userDTO;		
	
	}

	@Override
	public void update(UserDTO userDTO) {
		Map<String, String> map = new HashMap<>();
		map.put("name", userDTO.getName());
		map.put("id", userDTO.getId());
		map.put("pwd", userDTO.getPwd());
		userDAO.update(map);
	}

	@Override
	public int delete(String id) {
		return userDAO.delete(id);
	}

}
