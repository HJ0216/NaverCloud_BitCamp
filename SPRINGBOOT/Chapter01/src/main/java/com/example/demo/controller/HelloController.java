package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
// RestController = Controller + ResponseBody
public class HelloController {

	public HelloController() {
		System.out.println("HelloController Default Constructor");
		// 실행되는 mainClass와 실행되는 controller와 pkg 소속이 다를 경우, 실행이 되지 않으므로 같은 pkg or 소속 pkg로 지정
	}
	
	
	@GetMapping(value="/hello")
	public String hello(String name) {
		return "안녕하세요, " + name + "님";
	}
	// Spring에서는 @Controller로 선언할 경우 String을 반환할 때, 자동으로 jsp파일로 인식되므로 @ResponseBody 선언이 필요
	// 단, SpringBoot에서 @RestController로 선언할 경우 @ResponseBody를 선언하지 않아도 jsp가 아닌 String으로 인식

}
