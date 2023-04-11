package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
// @Component: 범용적 사용, 기재하지 않아도 Controller만으로도 context:component-scan 인식 가능
public class MainController {

/*
	@GetMapping(value="/")
	// value="client 요청값" ▶ Mapping이 될 method 선언
	public String index() {
		return "index";
		// 문자열 return 시, 파일명으로 인식됨
		// 기본 시작 경로: WEB-INF
	}
*/
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "index";
	} // 최종 return : /WEB-INF/index.jsp
	

}
