package collection;

import java.util.ArrayList;

public class CollectionMain2 {

	public static void main(String[] args) {
		ArrayList<String> arrList = new ArrayList<>();
		// Arraylist method 사용 가능
		// 중복 허용. 순서 유지
		arrList.add("Lion");
		arrList.add("Tiger");
		arrList.add("Penguin");
		arrList.add("Monkey");
		arrList.add("Bird");
		
		for(int i=0; i<arrList.size(); i++) {
			System.out.println(arrList.get(i));
		}
		
		for(String data : arrList) {
			System.out.println(data);
		}
		
	}
	
}
