package user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import user.bean.UserDTO;
import user.service.UserService;

/*
@Controller
public class UserController {
	@RequestMapping(value="/user/writeForm/", method=RequestMethod.GET)
	// value: projectName 제외
	// 시작:/부터, 끝: servlet 연결 url-pattern
	public String writeForm() {
		return "user/writeForm";
	} // 최종 return : /WEB-INF/user/writeForm.jsp
}
*/

@Controller // 요청과 동일한 method callback
@RequestMapping(value="user")
public class UserController {
// .jsp폼이 필요할 때는 String으로 해당 파일명으로 넘어가고
// 해당 폼에서 단순히 DB를 들려서 데이터를 뿌리는 경우에는 @ResponseBody를 통해서 Servlet 이동 방지
	
	@Autowired // 설정된 bean들 중에서 UserService를 찾아서 연결
	// @Autowired: 상속관계인 경우, 타입이 정확하게 일치하지 않더라도 Mapping 가능
	private UserService userService;
	
	
	@GetMapping(value="writeForm")
	public String writeForm() { // method 이름을 요청 url과 통일
		return "user/writeForm";
	} // 최종 return : /WEB-INF/user/writeForm.jsp

/*	
	@PostMapping(value="write")
	public String write(@RequestParam String name,
						@RequestParam String id,
						@RequestParam String pwd){}
*/

/*
	@PostMapping(value="write")
	public String write(@ModelAttribute UserDTO userDTO){
		return "write";
		// 단순 문자열이 아니라, 파일명으로 인식(/WEB-INF/angel.jsp)
		// @Controller 처리가 끝나면 DispatcherServlet으로 돌아가려고 함
		// return을 String 타입으로 선언할 수 없음
	}
*/
	
	@PostMapping(value="write")
	@ResponseBody // Controller 작업 후, Dispatcher Servlet으로 반환되는 것을 방지
	public void write(@ModelAttribute UserDTO userDTO){
		userService.write(userDTO);
	}
	
	
/*
페이징 처리 전
	@GetMapping(value="list")
	public String list() {
		// DB를 가지 않고 바로 화면에 틀만 띄움

		return "user/list";
		// @responseBody 처리가 되지 않은 String은 파일명으로 인식
		// user dir 안의 list.jsp로 이동
		// 접미사 설정은 application.context 참조
	}
*/	
	
		
	// 페이징 처리 후
	@GetMapping(value="list")
	public String list(@RequestParam(required=false, defaultValue = "1") String pg, Model model) {
		// 쿼리로 pg값이 넘어왔으므로 @RequestParam String pg을 추가 
		// 쿼리로 pg값이 들어오지 않을 경우, 필수 유무는 false / defaultValue = "1"로 진행
		// default pg 처리
		
		// 데이터를 싣어가기 위해 Model 사용
		model.addAttribute("pg", pg);

		return "user/list";
	}
	
	
	@PostMapping(value="getUserList")
	@ResponseBody
	// 1. DispatcherServlet 이동 방지
	// 2. Return DataType을 JSON 객체로 변경
	public Map<String, Object> getUserList(@RequestParam String pg) {
		// controller는 요청과 응답만

		return userService.getUserList(pg);
		// List<UserDTO> list = userService.getUserList();
		// return list
		// return "String"
		// DispatcherServlet으로 돌아가는 것을 방지하기 위해 @ResponseBody
	}
	
	
	@ResponseBody // 주소값이 아닌 문자열 인식
	@PostMapping(value="isExistId")
	public String isExistId(@RequestParam String id) {
	// @RequestParam = $.ajax-data: 'id='+$('#id').val()
		return userService.isExistId(id);
	}

	
	// 글 수정	
	@GetMapping(value="updateForm")
	public String updateForm() { // method 이름을 요청 url과 통일
		return "user/updateForm";
	} // 최종 return : /WEB-INF/user/writeForm.jsp

	
	@PostMapping(value="getUser")
	@ResponseBody
	// 1. DispatcherServlet 이동 방지(jsp로 이동 목적이 아닌 데이터만 반환하고자 할 경우)
	// 2. Return DataType을 JSON 객체로 변경
	public UserDTO getUser(@RequestParam String id) {
		return userService.getUser(id);
	}
	
	@PostMapping(value="update")
	@ResponseBody // Controller 작업 후, Dispatcher Servlet으로 반환되는 것을 방지
	public void update(@ModelAttribute UserDTO userDTO){
		userService.update(userDTO);
	}
	
	
	// 글 삭제
	@GetMapping(value="deleteForm")
	public String deleteForm() { // method 이름을 요청 url과 통일
		return "user/deleteForm"; // ▶ applicationContent: Resolver ▶ .jsp 반환
		// String의 경우에는 파일명으로 인식
		// 파일명으로 인식되는 게 싫으면 ResponseBody 사용
	}

	@PostMapping(value="delete")
	@ResponseBody
	// 1. DispatcherServlet 이동 방지
	// 2. Return DataType을 JSON 객체로 변경
	public int delete(@RequestParam String id) {
		return userService.delete(id);
	}
	
	
}