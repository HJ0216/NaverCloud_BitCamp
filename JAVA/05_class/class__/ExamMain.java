package class__;

import java.util.*;

public class ExamMain {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("인원수 입력: ");
		int number = scan.nextInt();
		System.out.println();

		Exam[] exam = new Exam[number];
		// array 생성

		for(int i=0; i<number; i++) {
			exam[i] = new Exam();
			// object 생성: array 생성 후, obj 별도 생성 필수
			exam[i].compare();
			
		} // for: obj 생성

		System.out.println("이름\t1\t2\t3\t4\t5\t점수");
		
		for(int i=0; i<number; i++) {
			exam[i].getName();
			exam[i].getOx();
			exam[i].getScore();
			
		} // for: getMethod()

		
		scan.close(); // Avoid Resource Leakage
		
	} // main()
} // class


/*
[문제] 사지선다형
- 총 5문제의 답을 입력받는다
- 정답은 "11111"이다
맞으면 'O', 틀리면 'X'
- 1문제당 점수는 20점씩 처리

클래스명: Exam
*Field
private String name=null;
private String dap=null;
private char[] ox=null;
private int score=0;
private final String JUNG = "1111"; // 상수화

*Method
생성자: Scanner를 이름과 답을 입력받음
compare()
getName()
getOx()
getScore()

클래스명: ExamMain

[실행 결과]
인원수 입력: 2

이름 입력: 홍길동
답 입력: 12311

이름 입력: 코난
답 입력: 24331

이름  1 2 3 4 5 점수
홍길동 O X X O O 60
코난  X X X X O 20
 */