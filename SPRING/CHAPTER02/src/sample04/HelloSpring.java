package sample04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {
		// static method 호출: ClassName.method();
		// instance method(menu()) 호출: Class 객체 생성 필요		

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
		// web.xml 제외 .xml 파일 읽어오는 과정 필요

		HelloSpring helloSpring = context.getBean("helloSpring", HelloSpring.class);
		/*

		new HelloSpring();
		▼
		.xml: <bean id="helloSpring" class="sample04.HelloSpring"></bean>
		.java: 
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.getBean("helloSpring", HelloSpring.class);

		*/
		
		helloSpring.menu(context);
		/*
		context 변수 공유
		1. ApplicatoinContext context; 전역변수로 선언 
		2. menu() 호출 시, context를 인자로 전달
		3. ApplicatoinContext context; 메서드마다 생성
		*/

		System.out.println("Program terminated");

	}// main

	public void menu(ApplicationContext context) {
		while(true) {
			System.out.println("**************");
			System.out.println("1. 입력");
			System.out.println("2. 출력");
			System.out.println("3. 수정");
			System.out.println("4. 삭제");
			System.out.println("5. 종료");
			System.out.println("**************");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("\nEnter the Number: ");

			int number = 0;
			try {
				number = Integer.parseInt(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
						
			if(number==5) {break;}
			
			SungJuk sungJuk = null;
			// 다형성 활용: SungJuk
			// 변수명을 통일 시, 하나의 변수명으로 각 class의 다양한 메서드 호출 가능
			if(number==1) {
				sungJuk = context.getBean("sungJukInput", SungJuk.class);
			}
			else if(number==2) {
				sungJuk = context.getBean("sungJukOutput", SungJuk.class);
			}
			else if(number==3) {
				sungJuk = context.getBean("sungJukUpdate", SungJuk.class);				
			}
			else if(number==4) {
				sungJuk = context.getBean("sungJukDelete", SungJuk.class);								
			}
			sungJuk.execute();				

		}// while		

	} // menu

}
