package multi;

public class MultiArray03 {
	public static void main(String[] args) {
		String[] name = new String[] {"홍길동", "프로도", "죠르디"};
//		String[] name = {"홍길동", "프로도", "죠르디"};

		int[][] score = {{91, 95, 100, 0}, {100, 88, 75, 0}, {75, 80, 48, 0}};
		// total value 부분을 0으로 초기화하여 array 생성
		
		double[] avg = new double[3];
		char[] grade = new char[3];

		for(int i=0; i<avg.length; i++) {
			for(int j=0; j<score[i].length-1; j++) {
				score[i][3] += score[i][j];
			}
		
			avg[i] = score[i][3] / 3.0;			
			
			if(avg[i]>=90) {grade[i] = 'A';}
			else if(avg[i]>=80) {grade[i] = 'B';}
			else if(avg[i]>=70) {grade[i] = 'C';}
			else if(avg[i]>=60) {grade[i] = 'D';}
			else {grade[i] = 'F';}

		}

		
		System.out.println("----------------------------------------------------------------");
		System.out.println("이름\t국어\t영어\t수학\t총점\t평균\t학점\t");
		System.out.println("----------------------------------------------------------------");
		
		for(int i=0; i<score.length; i++) {
			System.out.print(name[i] + "\t");
			
			for(int j=0; j<score[i].length; j++) {
				System.out.print(score[i][j] + "\t");
			} // for_j: 이름 당 국어 영어 수학 총점
			
			System.out.println(String.format("%.2f", avg[i]) + "\t" + grade[i]);
			// 이름 당 평균 학점
			
		} // for_i: 총 전체 학생 수

		System.out.println("----------------------------------------------------------------");
		
	} // main()

} // class

/*
[문제] 성적 처리
- 총점, 평균, 학점을 구하시오

총점: 국 + 영 + 수
평균: 총점 / 과목수 (소수 이하 2째자리 절삭)
학점(switch / break) -> 실수는 switch X
평균 90이상 = 'A'
평균 80이상 = 'B'
평균 70이상 = 'C'
평균 60이상 = 'D'
평균 60미만 = 'F'

----------------------------------------------------------------
이름      국어      영어      수학      총점      평균      학점
----------------------------------------------------------------
홍길동      90        95       100
프로도     100        89        75
죠르디      75        80        48
----------------------------------------------------------------
 */
