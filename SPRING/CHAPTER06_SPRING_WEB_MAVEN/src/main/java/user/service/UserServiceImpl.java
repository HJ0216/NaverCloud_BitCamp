package user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserPaging;
import user.bean.UserDTO;
import user.bean.UserImgDTO;
import user.dao.UserDAO;

@Service // @Component보다 구체적으로 기재
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserPaging userPaging = null;

	@Override
	public void write(UserDTO userDTO) {
		userDAO.write(userDTO);
	}

	@Override
	public Map<String, Object> getUserList(String pg) {
		// 페이지당 3명의 user 출력
		int endNum = Integer.parseInt(pg)*3; // 3
		int startNum = endNum-2; // 1

		Map<String, Integer> map = new HashMap<>();
		map.put("startNum", startNum); // key, value
		map.put("endNum", endNum);
		
		List<UserDTO> list = userDAO.getUserList(map); // 1 2 3 

		
		// 페이징 처리
		int totalA = userDAO.getTotalA(); // 6
		
		userPaging.setCurrentPage(Integer.parseInt(pg));
		userPaging.setPageBlock(3); // 페이지당 3개
		userPaging.setPageSize(3); // 묶음당 3페이지
		userPaging.setTotalA(totalA);
		
		userPaging.makePagingHTML(); // 이전, 다음 버튼
		
		// return 대상은 1개만 가능하므로 list와 userPaging 모두 전달하기 위해 map 선언
		Map<String, Object> map2 = new HashMap<>();
		map2.put("list", list);
		map2.put("userPaging", userPaging);
				
		return map2;
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

	@Override
	public void upload(UserImgDTO userImgDTO, List<String> fileNameList) {
		userDAO.upload(userImgDTO, fileNameList);
	}

	@Override
	public List<UserImgDTO> getUploadFormAjaxList() {
		return userDAO.getUploadFormAjaxList();
	}

}
