package collection;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;


public class setMain {

	public static void main(String[] args) {
		Set<String> set = new HashSet<>(); // 중복 허용 X, 순서 보장 X
		set.add("호랑이");
		set.add("호랑이");
		set.add("사자");
		set.add("코끼리");
		
		Iterator<String> iter = set.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}


// interface Collection: ArrayList
// interface Set: HashSet, TreeSet