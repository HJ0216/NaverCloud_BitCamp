package user.controller;

import java.util.List;

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
		// ajax: data: $('#writeForm').serialize() ▶ UserDTO userDTO
		userService.write(userDTO);
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

	@PostMapping("isExistId")
	@ResponseBody
	public String isExistId(@RequestParam String id) {
		return userService.isExistId(id);
	}


}
