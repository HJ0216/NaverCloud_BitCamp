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
		// msgBean.sayHello("Spring");


		// ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
		// springBean > applicationContext.xml에서 객체 생성
		// web.xml 제외 applicationContext.xml은 자동으로 인식되지 않음 > .xml 호출

		
		// java: new 연산자를 통한 객체 생성 및 기본 생성자 호출
		// Spring: xml 파일에서 객체 생성 및 기본 생성자 호출

		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
		MessageBean msgBean = (MessageBean) context.getBean("msgBean"); // Bean = 객체
		// .xml: <bean id="msgBean" class="sample03.MessageBeanKo" scope="prototype">
		// MessageBeanKo 객체 호출 시, 변수명 msgBean 사용
		msgBean.sayHello("Spring");
		
		MessageBean msgBean2 = context.getBean("msgBean", MessageBean.class);
		msgBean2.sayHello("Spring");

		MessageBean msgBean3 = (MessageBean) context.getBean("msgBean");
		msgBean3.sayHello("Spring");
	}
}
