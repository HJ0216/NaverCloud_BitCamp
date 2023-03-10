package multi;

import java.util.Scanner;

public class MultiArray05 {
	public static void main(String[] args) {		
		Scanner scan = new Scanner(System.in);
		System.out.print("인원수: ");
		int number = scan.nextInt();
		System.out.println();

		String[] name = new String[number];
		String[][] subject = new String[number][];
		int[][] jumsu = new int[number][];
		
		for(int i=0; i<number; i++) {
			System.out.print("이름 입력: ");
			name[i] = scan.next();
			
			System.out.print("과목수 입력: ");
			int subjectCnt = scan.nextInt();

			subject[i] = new String[subjectCnt];
			jumsu[i] = new int[subjectCnt];
						
			for(int j=0; j<subjectCnt; j++) {			
				System.out.print("과목명 입력: ");
				subject[i][j] = scan.next();
			} // for_j: 과목명 질의
			
			for(int j=0; j<subjectCnt; j++) {			
				System.out.print(subject[i][j] + " 점수 입력: ");
				jumsu[i][j] = scan.nextInt();
			} // for_j: 점수 질의
			
			
			System.out.println("---------------------");
		
		} // for_i: Scanner
		
		
		System.out.println();
		
		
		// Print
		for(int i=0; i<number; i++) {
			int total=0; // 학생마다 total=0에서 시작할 수 있도록 value initialize
			double avg=0; // 학생마다 avg=0에서 시작할 수 있도록 value initialize
			
			System.out.print("이름\t");
			for(int j=0; j<subject[i].length; j++) {
				System.out.print(subject[i][j] + "\t");
			} // for_j: 과목명
			System.out.println("총점\t평균");
			
			// Result (total을 result란에서 계산)
			System.out.print(name[i] + "\t");
			for(int j=0; j<jumsu[i].length; j++) {
				System.out.print(jumsu[i][j] + "\t");
				total +=jumsu[i][j];
			} // for_j: 과목 점수
			System.out.print(total + "\t");
			avg = total / jumsu[i].length;
			System.out.println(String.format("%.2f", avg) + "\n");
			
		} // for_i: print
		
		scan.close();
	}
}

/*
가변 array
 
인원수를 입력하여 인원수만큼 데이터를 입력받고 총점과 평균을 구하시오
평균은 소수이하 2째자리까지 출력

[실행결과]
인원수 : 2 (cnt)

이름입력 : 홍길동   (name)
과목수 입력 : 2     (subjectCnt)
과목명 입력 : 국어  (subject)
과목명 입력 : 영어
국어 점수 입력 : 95 (jumsu)
영어 점수 입력 : 100
---------------------
이름입력 : 이기자
과목수 입력 : 3
과목명 입력 : 국어
과목명 입력 : 영어
과목명 입력 : 과학
국어 점수 입력 : 95
영어 점수 입력 : 100
과학 점수 입력 : 90
---------------------

이름     국어    영어     총점      평균
홍길동     95     100      195     97.50

이름    국어   영어   과학    총점      평균
이기자   95     100     90     285     95.00

 */
