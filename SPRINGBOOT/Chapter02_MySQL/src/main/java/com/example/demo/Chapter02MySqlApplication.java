package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// @ComponentScan(basePackages = {"main.controller", "user.controller", "user.bean", "user.dao", "user.service"})
@ComponentScan(basePackages = {"com.example.demo", "main.controller", "user.*"})
public class Chapter02MySqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chapter02MySqlApplication.class, args);
		
//		SpringApplication springApplication = new SpringApplication(Chapter02MySqlApplication.class);
//		springApplication.setWebApplicationType(WebApplicationType.NONE);
//		web으로 실행하지 않겠다는 선언 후, localhost 호출 시 error 발생
//		springApplication.run(args);

	}

}
