package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // application.properties 설정을 적용하기 위해 Controller 선언
public class MainController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
}
