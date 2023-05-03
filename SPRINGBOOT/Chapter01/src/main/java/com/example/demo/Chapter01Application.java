package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.example.demo.controller", "board.controller"})
// Spring: root-context.xml > SpringBoot: @Component-scan
// mainclass 밖에서 controller 생성 시, @ComponentScan 필요
@SpringBootApplication
public class Chapter01Application {

	public static void main(String[] args) {
		// SpringApplication.run(Chapter01Application.class, args);
		
		SpringApplication springApplication = new SpringApplication(Chapter01Application.class);
		springApplication.setWebApplicationType(WebApplicationType.NONE);
		springApplication.run(args);
	}

}

/*
SpringBoot Application
: 일반 자바 애플리케이션 또는 웹 애플리케이션으로 실행할 수 있음
: (Default) 메인 클래스 실행 시, 웹 애플리케이션으로 실행

WebApplicationType 타입
① WebApplicationType.NONE – 웹으로 동작하지 않도록 설정
② WebApplicationType.SERVLET – 기존의 스프링 MVC를 기반으로 웹 애플리케이션을 구동하는 설정
③ WebApplicationType.REACTIVE – 스프링 5.0에서 추가된 비동기 처리와 논블로킹 입출력을 지원하는 웹플럭스(WebFlux)를 적용할 때 사용


 * 외부 프로퍼티 사용
src/main/resources/application.properties: 전체 프로젝트의 프로퍼티 정보를 관리하는 설정 파일
자바 소스보다 application.properties 설정이 우선순위가 높음
 * 자바 소스: WebApplicationType.NONE / application.properties: WebApplicationType.SERVLET
 * 프로퍼티 설정의 우선순위가 높기 때문에 웹 애플리케이션이 실행됨

application.properties에 설정한 프로퍼티 정보들: 실제 해당 Properties 객체의 Setter 메소드가 호출되어 의존성이 주입됨
ctrl 키를 누른 상태에서 server.port를 클릭하면 ServerProperties 클래스의 setPort() 메소드가 선택됨


사용자가 정의한 클래스들이 자동으로 빈으로 등록되기 때문에 스프링 부트에서는 패키지 이름을 주의해서 작성해야 한다.
만약 루트 패키지인 "com.example" 가 아닌 다른 패키지에 클래스를 작성하면 스프링 컨테이너는 해당 클래스를 빈으로 등록하지 않는다. 
다른 패키지의 클래스까지 스캔 대상에 포함 시키려면 메인 클래스에 @ComponentScan을 추가하여 패키지를 직접 지정하면 된다.


 * 스프링 DevTools 사용

수정된 컨트롤러를 반영하기 위해서는 반드시 실행 중인 애플리케이션을 중지하고 애플리케이션을 재실행해야 한다.
단, 스프링 부트가 제공하는 DevTools 기능을 이용하게 되면 자동으로 서버가 reloading하여 재실행할 필요가 없다.

*/
