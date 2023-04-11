package user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/*
@Controller
public class UserController {
	@RequestMapping(value="/user/writeForm/", method=RequestMethod.GET)
	// value: projectName 제외, 시작:/부터, 끝: servlet 연결 url-pattern
	public String writeForm() {
		return "user/writeForm";
	} // 최종 return : /WEB-INF/user/writeForm.jsp
}
*/


@Controller // 요청과 동일한 method callback
@RequestMapping(value="user")
public class UserController {
	@GetMapping(value="writeForm")
	public String writeForm() { // method 이름을 요청 url과 통일
		return "user/writeForm";
	} // 최종 return : /WEB-INF/user/writeForm.jsp
}