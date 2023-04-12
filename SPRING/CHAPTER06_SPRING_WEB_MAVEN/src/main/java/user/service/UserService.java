package user.service;

import java.util.List;

import user.bean.UserDTO;

public interface UserService {

	public void write(UserDTO userDTO);

	public List<UserDTO> getUserList();

	public String isExistId(String id);

	public UserDTO getUser(String id);

	public void update(UserDTO userDTO);

	public int delete(String id);

}
