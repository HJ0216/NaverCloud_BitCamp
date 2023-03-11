package com.zoo;

public class Zoo { // 다른 pkg에서 접근 여부 결정: 접근 제어자

	public void tiger() {System.out.println("Afraid Tiger");}
	protected void giraffe() {System.out.println("Long neck Giraffe");}
	void elephant() {System.out.println("Fat Elephant");}
	private void lion() {System.out.println("Handsome Lion");}
	
	
}
