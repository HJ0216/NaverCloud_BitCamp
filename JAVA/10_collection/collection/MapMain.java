package collection;

import java.util.Map;
import java.util.HashMap;


public class MapMain {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>(); // Map<Key, Value>
		// key 중복 허용(덮어쓰기), value 중복 허용
		map.put("book101", "백설공주");
		map.put("book102", "백설공주");
		map.put("book201", "인어공주");
		map.put("book301", "신데렐라");
		map.put("book101", "엄지공주");
		
		System.out.println(map.get("book101")); // value return
		System.out.println(map.get("book102"));
		System.out.println(map.get("book201"));
		System.out.println(map.get("book301"));
		System.out.println(map.get("book501")); // null return
	}
}
