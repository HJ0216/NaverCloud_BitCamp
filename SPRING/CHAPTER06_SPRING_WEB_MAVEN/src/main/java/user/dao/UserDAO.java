package user.dao;

import java.util.List;
import java.util.Map;

import user.bean.UserDTO;

public interface UserDAO {

	public void write(UserDTO userDTO);

	public List<UserDTO> getUserList(Map<String, Integer> map);

	public UserDTO getUser(String id);

	public void update(Map<String, String> map);

	public int delete(String id);

	public int getTotalA();

}
