package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	public HelloController() {
		System.out.println("HelloController Default Constructor");
		// 실행되는 mainClass와 실행되는 controller와 pkg 소속이 다를 경우, 실행이 되지 않으므로 같은 pkg or 소속 pkg로 지정
		// 다른 pkg 소속의 controller를 실행하고자할 경우, @Componentscan 사용
	}
		
	@GetMapping(value="/hello")
	public String hello(String name) {
		return "안녕하세요, " + name + "님";
	}

}
