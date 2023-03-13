package collection;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

public class CollectionMain {

	public static void main(String[] args) {
//		Collection col = new Collection(); // interface new X
		Collection<Object> col = new ArrayList<>(); // interface 구현 class를 통해서 다형성 사용
		// ArrayList: Idx num 부여
		// ArrayList: 중복허용, 순서 유지
		col.add("Tiger");
		col.add("Lion");
		col.add("Elephant");
		col.add(25);
		col.add(43.8);
		col.add("Giraffe");
		col.add("Snake");
		// 값을 설정했으나 불러올 수 있는 방법 X
		// rc: parent, obj: child, child method를 parent rc가 쓸 수 없음
		
//		Iterator iter = new Iterator(); interface obj X
		@SuppressWarnings("rawtypes")
		// <>에 대한 warning 억제
		Iterator iter = col.iterator(); // method를 통한 구현
		while(iter.hasNext()) { // obj의 다음 항목이 있는지 확인: O-true, 1-false
			System.out.println(iter.next()); // 항목을 꺼내고 다음 항목으로 이동
		}
		
		
		
	}
	
}


// interface Collection: implements
// interface 내 모든 abstract method Override
// 대신 override 해주는 class -> implementing class 사용


// interface collection 구현
