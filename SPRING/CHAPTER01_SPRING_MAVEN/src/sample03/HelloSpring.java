package sample03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloSpring {
	public static void main(String[] args) {		
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
