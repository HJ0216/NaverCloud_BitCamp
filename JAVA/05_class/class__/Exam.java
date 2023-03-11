package class__;

import java.util.*;

public class Exam {
	private String name=null;
	private String dap=null;
	private char[] ox=null;
	private int score=0;
	private final String JUNG = "11111";

	Scanner scan = new Scanner(System.in);
	// Method 내에서 선언되지 않은 Scanner Class는 Resource Leakage 발생 X
	
	
	public Exam() {
		System.out.print("이름 입력: ");
		name = scan.next();

		System.out.print("답 입력: ");
		dap = scan.next();
		
		System.out.println();
	} // Default Constructor
	

	public void compare() {
		ox = new char[JUNG.length()]; // array 생성 후, 크기 지정 필수
		
		for(int i=0; i<JUNG.length(); i++) {
			if(dap.charAt(i)==JUNG.charAt(i)) {
				ox[i] = (char)'O';
			} else {ox[i] = (char)'X';}
		} // for: check ox
	} // compare
	
	
	public void getName() {
		System.out.print(name + "\t");
	}
	

	public void getOx() {
		for(int i=0; i<JUNG.length(); i++) {
			System.out.print(ox[i] + "\t");
		} // for: print_ox
	} // getOx
	

	public void getScore() {
		int count=0;
		
		for(int i=0; i<JUNG.length(); i++) { // for 중첩구문 줄이기
			if(ox[i]=='O') {
				count++;
				score=count*20;
				} // if: count & score
		} // for: count & score
		System.out.println(score);
	} // getScore

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

 */
