package user.dao;

import java.util.List;

import user.bean.UserDTO;

public interface UserDAO {
	public void insert(UserDTO userDTO);
	public List<UserDTO> getUserList();
	
}
