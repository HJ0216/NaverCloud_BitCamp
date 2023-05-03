package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
// context:component-scan 인식 가능
public class MainController {

/*
	@GetMapping(value="/")
	// value="client 요청값" ▶ Mapping이 될 method 선언
	public String index() {
		return "index";
		// 문자열 return 시, 파일명으로 인식됨
	}
*/
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	// request: /
	public String index() {
		return "index";
		// 최종 return : /WEB-INF/index.jsp
	}
	
}
