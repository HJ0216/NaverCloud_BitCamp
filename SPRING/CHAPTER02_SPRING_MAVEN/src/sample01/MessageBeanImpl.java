package sample01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// @Component // @Component Bean 생성
public class MessageBeanImpl implements MessageBean {
	private String fruit; // constructor
	private int cost, qty; // setter

	
	public MessageBeanImpl(@Value("사과") String fruit) {
	// SpringConfiguratoin과 value를 동시에 입력할 경우, SpringConfiguration이 우선함
	// public MessageBeanImpl(String fruit) {
		super();
		this.fruit = fruit;
	}
	
	@Autowired // Setter는 자동으로 읽히지 않으므로 Autowired annotation 필요
	public void setCost(@Value("5000") int cost) { // Value 내 "" > 자동 형변환
		this.cost = cost;
	}
	
	@Autowired
	public void setQty(@Value("3") int qty) {
		this.qty = qty;
	}

	
	@Override
	public void sayHello() {
		System.out.println(fruit + "\t" + cost + "\t" + qty);
	}

	@Override
	public void sayHello(String fruit, int cost) {
		System.out.println(fruit + "\t" + cost + "\t" + qty);
		
	}

	@Override
	public void sayHello(String fruit, int cost, int qty) {
		System.out.println(fruit + "\t" + cost + "\t" + qty);
		
	}

}
