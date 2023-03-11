package class__;

import java.util.*;

public class StringMain2_T {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("문자열 입력: ");
		String original = scan.next();
		
		System.out.print("현재 문자열 입력: ");
		String old = scan.next();
		
		System.out.print("바꿀 문자열 입력: ");
//		String new = scan.next();
		// String cannot be resolved to a variable: new 연산자로 인해 variable name으로 사용 불가
		String rep = scan.next();
		
		if(original.length()<old.length()) {
			System.out.println("입력한 문자열의 크기가 작습니다.");
			System.out.println("치환할 수 없습니다.");
			return; // return type이 void인 경우, method 빠져나오기
//			System.exit(0); // program forced terminated
		} else {
			original = original.toLowerCase(); // original이 소문자 치환되서 나오므로 결과값이 달라짐
			old = old.toLowerCase();
			// original, old toLowerCase(): 입력값 변경 -> indexOf에서 처리 필요 X
			// String: 문자열 편집(수정)을 할 수 없으므로 변경 시, 새로운 memory allocation
			// 원본값 수정하지 말기
			
			int index = 0;
			int count = 0;
			
			while((index = original.indexOf(old, index))!=-1) {
				index += rep.length();
				count++;
			}
			
			System.out.println(original.replace(old, rep));
			System.out.println(count + "번 치환");
			
		}
		
		scan.close();
	}
}
