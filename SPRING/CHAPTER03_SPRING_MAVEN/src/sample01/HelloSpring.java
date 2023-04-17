package sample01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("acQuickStart.xml");
		// .xml 읽도록 지정
		
/*	Before	
		MsgBean msgBean = (MsgBean) context.getBean("msgBeanImpl");
		msgBean.showPrintBefore();
		System.out.println();
		msgBean.viewPrintBefore();
		System.out.println();
		msgBean.display();
*/
		
/*		
		MsgBean msgBean = (MsgBean) context.getBean("msgBeanImpl");
		msgBean.showPrintAfter();
		System.out.println();
		msgBean.viewPrintAfter();
		System.out.println();
		msgBean.display();
*/
		
		// Around
		MsgBean msgBean = (MsgBean) context.getBean("msgBeanImpl");
		msgBean.showPrint();
		System.out.println();
		msgBean.viewPrint();
		System.out.println();
		msgBean.display();
		
		
		
	}

}
