package user.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import user.service.UserService;

public class HelloSpring {
	public void menu(ApplicationContext context) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserService userService = null;

		int number = 0;

		while(true) {
			System.out.println("**************");
			System.out.println("1. 입력");
			System.out.println("2. 출력");
			System.out.println("3. 수정");
			System.out.println("4. 삭제");
			System.out.println("5. 종료");
			System.out.println("**************");
			
			System.out.print("\nEnter the Number: ");

			try {
				number = Integer.parseInt(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
						
			if(number==5) {break;}
			
			// 다형성 활용: SungJuk
			// 변수명을 통일 시, 하나의 변수명으로 각 class의 다양한 메서드 호출 가능
			if(number==1) {
				userService = context.getBean("userInsertService", UserService.class);
				// userService = new UserInsertService();
			}
			else if(number==2) {
				userService = context.getBean("userSelectService", UserService.class);
			}
			else if(number==3) {
				userService = context.getBean("userUpdateService", UserService.class);
			}
			else if(number==4) {
				userService = context.getBean("userDeleteService", UserService.class);
			}
			userService.execute();

		} // while()
		
	} // menu()

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		// ClassPath 기준점: src, src에 위치하지 않은 경우 절대경로 지정 필요
		// path 설정이 잘못된 경우: class path resource [applicationContext.xml] cannot be opened because it does not exist
		HelloSpring helloSpring = context.getBean("helloSpring", HelloSpring.class);
		helloSpring.menu(context); // context 공유
		System.out.println("Program Terminated");
	}

}
