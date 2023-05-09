package user.dao;

import java.util.List;
import java.util.Map;

import user.bean.UserDTO;
import user.bean.UserImgDTO;

public interface UserDAO {

	public void write(UserDTO userDTO);

	public List<UserDTO> getUserList(Map<String, Integer> map);

	public UserDTO getUser(String id);

	public void update(Map<String, String> map);

	public int delete(String id);

	public int getTotalA();

	public void upload(UserImgDTO userImgDTO, List<String> fileNameList);

	public List<UserImgDTO> getUploadFormAjaxList();

}
