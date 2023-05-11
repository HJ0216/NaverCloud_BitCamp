package user.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import user.bean.UserDTO;
import user.service.UserService;

@Controller // application.properties 설정을 적용 받음
@RequestMapping("user") // "/user"와 동일
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("writeForm")
	public String writeForm() {
		return "user/writeForm";
	}
	
	@PostMapping("write")
	@ResponseBody // .jsp로 파일로 return되는 것이 아니라 webBrowser로 이동하라는 의미
	public void write(@ModelAttribute UserDTO userDTO) {
		// @ModelAttribute: String ▶ UserDTO 자동 Mapping
		// ajax-data: $('#writeForm').serialize() ▶ UserDTO userDTO
		userService.write(userDTO);
	}
	
	

	// 중복 검사
	@PostMapping("isExistId")
	@ResponseBody
	public String isExistId(@RequestParam String id) {
		return userService.isExistId(id);
	}
	
	
	
	
	
	@GetMapping("listForm")
	public String listForm() {
		return "user/listForm";
	}
	
	@PostMapping("list")
	@ResponseBody // jsp파일 이동이 아닐 경우, @Responsbody 선언
	public List<UserDTO> list(){
		return userService.list();
	}	

	
	
	// 목록 - 검색
	@PostMapping("search")
	@ResponseBody
	public List<UserDTO> search(@RequestParam Map<String, String> map) {
	// 넘겨준 데이터 2개를 @RequestParam로 받아서 Map에 자동으로 담음
		
	// public List<UserDTO> search(@RequestParam String SearchOpt, @RequestParam String keyword) {

	// $('#SearchForm').serialize()도 동일하게 받음
		
		System.out.println(userService.search(map));
		
		return userService.search(map);
	}

	
	
	
	
	@GetMapping("updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
	
	@PostMapping("getUserInfo")
	@ResponseBody
	public Optional<UserDTO> getUserInfo(@RequestParam String searchId) {
		System.out.println(userService.getUserInfo(searchId));
		return userService.getUserInfo(searchId);
	}
	
	// 1. update: @PostMapping("write") 재사용
	// 2. update: update 메서드 생성 후, jpa save 메서드 사용
	// cf. jpa-save(): id col-PK, 동일 id 존재-update, 동일 id 존재 X-insert
	
	
	
	
	
	@GetMapping("deleteForm")
	public String deleteForm() {
		return "user/deleteForm";
	}
	
	// 회원 정보 조회: @PostMapping("getUserInfo") 사용
	
	@PostMapping("delete")
	@ResponseBody
	public void delete(@RequestParam String id) {
		userService.delete(id);
	}	
	
	
}
