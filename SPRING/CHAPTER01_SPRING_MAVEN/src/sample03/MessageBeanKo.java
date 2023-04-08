package sample03;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("msgBeanKo")
@Scope("prototype")
// appplicatoinContext.xml <bean id="msgBean" class="sample03.MessageBeanKo" scope="prototype"></bean>
public class MessageBeanKo implements MessageBean {
	private int num; // field default: initialized
	
	public MessageBeanKo() {
		System.out.println("Defualt Constructor, 한글");
	}
	
	@Override
	public void sayHello(String name) {
		num++;
		System.out.println("num: " + num);
		System.out.println("안녕 " + name);
		System.out.println();
		
	}
	
}
