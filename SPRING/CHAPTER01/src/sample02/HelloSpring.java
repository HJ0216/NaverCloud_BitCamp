package sample02;

public class HelloSpring {
	public static void main(String[] args) {
		// MessageBeanKo msgBeanKo = new MessageBeanKo();
		// 1 대 1 관계: 결합도 100% ▶ 다형성을 통한 코드의 유연성 부여
		MessageBean msgBean = new MessageBeanKo();
		// msgBean: java-객체, spring-bean
		msgBean.sayHello("Spring");
	}
}
