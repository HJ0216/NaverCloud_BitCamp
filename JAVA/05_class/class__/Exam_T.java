package class__;

import java.util.*;

public class Exam_T {

	private String name;
	private String dap;
	private char[] ox = null; // array size allocation 필요
	private int score;
	private final String JUNG = "11111";
	
	public Exam_T() {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("이름 입력: ");
		name = scan.next();
		System.out.print("답 입력: ");
		dap = scan.next();
		
//		scan.close(); // scan을 생성자에서 닫을 경우, for문에서 Error 발생
		
		ox = new char[5];
	} // Default Constructor
	
	public void compare() { // 문자열: 문자들의 배열
		for(int i=0; i<JUNG.length(); i++) { // i<ox.length
			if(dap.charAt(i)==JUNG.charAt(i)) {
				ox[i] = 'O';
				score += 20;
			} else {ox[i]='X';}
		}
	}
	
	public String getName() {
		return name;
	}

	public char[] getOx() {
		return ox;
	}

	public int getScore() {
		return score;
	}
	

}
