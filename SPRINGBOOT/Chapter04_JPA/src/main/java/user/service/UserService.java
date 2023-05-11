package user.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import user.bean.UserDTO;

public interface UserService {

	public void write(UserDTO userDTO);

	public List<UserDTO> list();

	public String isExistId(String id);

	public List<UserDTO> search(Map<String, String> map);

	public Optional<UserDTO> getUserInfo(String searchId);

	public void delete(String id);

}
