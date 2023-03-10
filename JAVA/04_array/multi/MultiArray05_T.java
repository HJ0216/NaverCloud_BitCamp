package multi;

import java.util.Scanner;

public class MultiArray05_T {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("인원수: ");
		int cnt = scan.nextInt();
		
		String[] name = new String[cnt];
		
		int subjectCnt;
		
		String[][] subject = new String[cnt][];
		int[][] jumsu = new int[cnt][];
		double[] avg = new double[cnt];
		
		for(int i=0; i<cnt; i++) {
			System.out.print("이름 입력: ");
			name[i] = scan.next();
			
			System.out.print("과목수 입력: ");		
			subjectCnt = scan.nextInt();

			// 다차원 배열에서 가변배열 생성 시, new String[]을 통한 Memory Load 필요
			subject[i] = new String[subjectCnt];
			for(int j=0; j<subjectCnt; j++) {
				System.out.print("과목명 입력: ");
				subject[i][j] = scan.next();					
			}
				
			jumsu[i] = new int[subjectCnt+1]; // total 포함을 위해
			for(int j=0; j<subjectCnt; j++) {
				System.out.print(subject[i][j] + " 점수 입력: ");
				jumsu[i][j] = scan.nextInt(); // score
				
				jumsu[i][subjectCnt] += jumsu[i][j]; // total
			} // for_j: 점수

			avg[i] = (double)jumsu[i][subjectCnt] / subjectCnt;

			
		} // for_i: 총 인원수
		
		
		// Print
		for(int i=0; i<cnt; i++) {
			System.out.print("\n이름\t");

			for(int j=0; j<subject[i].length; j++) {
				System.out.print(subject[i][j] + "\t");
			} System.out.println("총점\t평균");
			
			
			System.out.print(name[i] + "\t");

			for(int j=0; j<jumsu[i].length; j++) {
				System.out.print(jumsu[i][j] + "\t");
			} System.out.print(String.format("%.2f", avg[i]) + "\n");
			
		} // for_i: 총 인원수 출력
		
		scan.close();
	}
	
}
