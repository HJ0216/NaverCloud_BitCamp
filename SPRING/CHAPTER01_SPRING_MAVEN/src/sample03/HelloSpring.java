package sample03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloSpring {
	public static void main(String[] args) {		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MessageBean msgBean = (MessageBean) context.getBean("msgBeanKo"); // Bean = 객체
		// MessageBeanKo class에 method 호출 시, msgBeanKo 사용
		// .xml에 "msBeanKo"와 일치하는 <bean/>이 없을 경우,
		// .xml 파일 전체를 읽어서 다른 sample의 <bean/>이 읽히고 해당 bean의 생성자가 자동으로 호출됨
		msgBean.sayHello("Spring");
		
		MessageBean msgBean2 = context.getBean("msgBeanKo", MessageBean.class);
		msgBean2.sayHello("Spring");

		MessageBean msgBean3 = (MessageBean) context.getBean("msgBeanKo");
		msgBean3.sayHello("Spring");
}
}
