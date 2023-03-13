package collection;

import java.util.Iterator;
import java.util.Vector;

public class VectorMain {

	public static void main(String[] args) {
		Vector<String> v = new Vector<>();
		// array처럼 Vector의 크기가 고정되어있음
		
		System.out.println("Size of Vector: " + v.size());
		System.out.println("Capacty of Vector: " + v.capacity());
		// 기본 용량: 10, 10단위 증가
		System.out.println();
		
		System.out.println("Add Item");
		for(int i=0; i<v.capacity(); i++) {
			v.add((i+1) + "");
			System.out.print(v.get(i) + "\t");
		}
		// int -> String: i + ""
		
		System.out.println();
		System.out.println("Size of Vector: " + v.size());
		System.out.println("Capacty of Vector: " + v.capacity());
		// 기본 용량: 10, 10단위 증가
		System.out.println();
		
		System.out.println("항목 1개 추가");
		v.addElement(5 + "  ");
		
		for(int i=0; i<v.size(); i++){System.out.print(v.get(i) + "\t");}
		
		System.out.println();
		System.out.println("Size of Vector: " + v.size());
		System.out.println("벡터용량: " + v.capacity());

		System.out.println();

		System.out.println("Delete Last Item");
//		v.remove("5") // 앞 부분의 5 삭제
		v.remove(v.size()-1); // idx = 11, 뒷 부분 5 삭제
		
		Iterator<String> it = v.iterator();
		// iterator(): method를 통한 interface Iterator 구현
		// iterator(): return Iterator
		while(it.hasNext()) {
			System.out.print(it.next() + "\t");
			// next(): element 추출 후, Buffer에 저장 -> 다음 항목으로 이동

		}
		
		System.out.println();
	}
}
