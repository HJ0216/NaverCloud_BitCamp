package class__;

import java.util.*;

public class ExamMain_T {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("인원 수 입력: ");
		int count = scan.nextInt();
		
		Exam_T[] exam2 = new Exam_T[count]; // 객체 배열 생성

		
		for(int j=0; j<count; j++) {
			exam2[j] = new Exam_T(); // 객체 생성
			exam2[j].compare();		
		} // for

		System.out.println("이름\t1\t2\t3\t4\t5\t점수");
		
		// print
		for(Exam_T data : exam2) { // 확장형 for문
			System.out.print(data.getName() + "\t");
			for(int i=0; i<data.getOx().length; i++) {
				System.out.print(data.getOx()[i] + "\t");
			}
			// exam2.getOx: return array address
			// 다른 class에서 만든 method 호출 시, 매개변수 사용
			System.out.println(data.getScore());
		} // for
		
		scan.close();

	}
	
}
