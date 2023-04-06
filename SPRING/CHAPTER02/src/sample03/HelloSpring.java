package sample03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SungJuk sungJukImpl = context.getBean("sungJukImpl", SungJuk.class);
		sungJukImpl.calcTot();
		sungJukImpl.calcAvg();
		sungJukImpl.display();
		System.out.println();
		
		sungJukImpl.modify();
		sungJukImpl.calcTot();
		sungJukImpl.calcAvg();
		sungJukImpl.display();
	}
}
