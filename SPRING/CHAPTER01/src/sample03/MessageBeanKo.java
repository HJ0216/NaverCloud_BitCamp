package sample03;

public class MessageBeanKo implements MessageBean {
	private int num; // field default: initialized
	
	public MessageBeanKo() {
		System.out.println("Defualt Constructor, 한글");
	}
	
	@Override
	public void sayHello(String name) {
		num++;
		System.out.println("num: " + num);
		// singleton: 1, 2, 3, prototype: 1, 1, 1
		System.out.println("안녕 " + name);
		System.out.println();
		
	}
	
}
