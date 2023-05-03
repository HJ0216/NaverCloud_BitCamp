package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloGradleApplication.class, args);
		System.out.println("Hello SprimgBoot");
	}

}
