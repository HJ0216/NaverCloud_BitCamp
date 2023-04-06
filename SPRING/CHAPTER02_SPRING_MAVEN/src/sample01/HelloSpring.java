package sample01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MessageBean msgBean = context.getBean("messageBeanImpl", MessageBean.class);
		msgBean.sayHello();
		
		msgBean.sayHello("Grape", 12000);
		msgBean.sayHello("StrawBerry", 12000, 5);
	}

}
