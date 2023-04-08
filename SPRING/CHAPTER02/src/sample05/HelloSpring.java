package sample05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		MessageBean msgBean = context.getBean("messageBeanImpl2", MessageBean.class);
		msgBean.helloCall();
		
	}

}
