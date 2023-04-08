package sample03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloSpring {
	public static void main(String[] args) {
	
		// java 객체 = spring bean
		// java: new 연산자를 통한 객체 생성 및 기본 생성자 호출
		// Spring: xml 파일에서 객체 생성 및 기본 생성자 호출

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");

		// ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
		// springBean > applicationContext.xml에서 객체 생성
		// web.xml 제외 applicationContext.xml은 자동으로 인식되지 않음 > .xml 호출

		MessageBean msgBean = (MessageBean) context.getBean("msgBeanKo");
		// .xml: <bean id="msgBean" class="sample03.MessageBeanKo" scope="prototype">
		// MessageBeanKo 객체 호출 시, 변수명 msgBeanKo 사용
		
		msgBean.sayHello("Spring");
		
		MessageBean msgBean2 = context.getBean("msgBeanKo", MessageBean.class);
		msgBean2.sayHello("Spring");

		MessageBean msgBean3 = (MessageBean) context.getBean("msgBeanKo");
		msgBean3.sayHello("Spring");
	}
}
