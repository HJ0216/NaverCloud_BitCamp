package sample01;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class MessageBeanImpl implements MessageBean {
	@NonNull
	private String fruit; // constructor
	@Setter
	private int cost, qty; // setter
	// setter를 특정 변수 위에 선언할 경우, 해당 변수에 대해서만 setter 설정 가능

	/*
	public MessageBeanImpl(String fruit) {
		super();
		this.fruit = fruit;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
	*/
	
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
