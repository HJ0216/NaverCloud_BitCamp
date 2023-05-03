package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// SpringBoot로 만든 application의 시작 클래스임을 의미
public class HelloTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloTestApplication.class, args);
		System.out.println("Hello SpringBoot");
	}

}


/*

- 스프링 부트로 만든 애플리케이션을 실행하기 위해서는 메인 클래스를 작성해야 한다.
- 프로젝트 생성 시, 기본값으로 src/main/java 폴더에 [프로젝트명+Application.java] 라는 main() 메소드를 가진 파일이 생성됨
> Project 이름을 대문자로 생성하여 class naming 규칙에 맞추기

*/
