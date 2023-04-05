package sample03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloSpring {
	public static void main(String[] args) {
		
		// MessageBeanKo msgBeanKo = new MessageBeanKo();
		// 1 대 1 관계: 결합도 100% ▶ 다형성을 통한 코드의 유연성 부여		

		// MessageBean msgBean = new MessageBeanKo();
		// msgBean: java 객체 = spring bean
		// springBean > applicationContext.xml에서 객체 생성

		// msgBean.sayHello("Spring");
		
		// web.xml 제외 applicationContext.xml은 자동으로 인식되지 않음
		// .xml 호출

		// ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
		// java: new 연산자를 통한 객체 생성 및 기본 생성자 호출
		// Spring: xml 파일에서 객체 생성 및 기본 생성자 호출
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// web.xml 제외 xml 파일 자동으로 읽기 X
		MessageBean msgBean = (MessageBean) context.getBean("msgBean"); // Bean = 객체
		// MessageBeanKo class에 method 호출 시, msgBean 사용
		msgBean.sayHello("Spring");
		
		MessageBean msgBean2 = context.getBean("msgBean", MessageBean.class); // Bean = 객체
		// MessageBean msgBean2 = (MessageBean) context.getBean("msgBean"); // Bean = 객체
		// MessageBeanKo class에 method 호출 시, msgBean 사용
		msgBean2.sayHello("Spring");

		MessageBean msgBean3 = (MessageBean) context.getBean("msgBean"); // Bean = 객체
		// MessageBeanKo class에 method 호출 시, msgBean 사용
		msgBean3.sayHello("Spring");
}
}
