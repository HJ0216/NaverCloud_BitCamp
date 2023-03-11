package class__;

import java.util.*;

public class Token {

	public static void main(String[] args) {
		String str = "학원,집,,게임방";
		
		StringTokenizer st = new StringTokenizer(str, ",");
		System.out.println("Token number: " + st.countTokens());
		// 빈 값은 계산하지 않으나, ' ' 공란은 계산됨
		// index가 없으므로 for st[i]로 계산할 수 없음
		// while문에서 token의 존재 유무로 값 반환 로직 생성
		
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken()); // Token을 반환하고 다음으로 이동
		} // while
		
		
		System.out.println("\nSplit()");
		String[] ar = str.split(",");
		// 비어있는 값도 꺼내옴
		
		for(String data : ar) {
			System.out.println(data);
		}
		
	}
}
