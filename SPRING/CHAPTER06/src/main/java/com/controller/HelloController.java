package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller // 자바파일이 아닌 컨트롤러 역할하는 것을 안내
public class HelloController {

		@RequestMapping(value="/hello.do", method=RequestMethod.GET)
		public ModelAndView hello() { // 사용자 callback method
				ModelAndView mav = new ModelAndView();
				mav.addObject("result", "Hello, Spring!");
				// request.setAttribute("result", "Hello, Spring"); 와 동일
				
				mav.setViewName("Hello"); // 파일명 지정
				// .xml: 폴더명(<property name="prefix" value="/view/"></property>)
				// .xml: 확장자(<property name="suffix" value=".jsp"></property>)
				//weapp
				
				return mav;
		}
	
}


/*
Request: http://localhost:8080/Context명(Project명)/hello.do
(*.do) ▶ DispatcherServlet 
		* HandlerMapping
		* (servlet과 controller mapping)
		* controller 찾기(RequestMapping)
▶ HelloController.java
 

CallBack Method
 - 일정 기준을 충족할 경우, 운영체제나 스프링에 의해서 호출되는 method
 예: java- JVM에 의해서 main()가 callback

Spring: 사용자 callback method
-> spring container에 의해서 자동으로 호출되는 method

 */
