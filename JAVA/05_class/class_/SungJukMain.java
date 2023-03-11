package class_;

public class SungJukMain {

	public static void main(String[] args) {
	
		SungJuk sj1 = new SungJuk();
		sj1.setData("홍길동", 91, 95, 100);
		sj1.calc(); // 계산 method도 같이 수행되어야 함
		
		System.out.println("----------------------------------------------------------------");
		System.out.println("이름\t국어\t영어\t수학\t총점\t평균\t학점");
		System.out.println("----------------------------------------------------------------");
		System.out.println(sj1.getName() + "\t"
						 + sj1.getKor() + "\t"
						 + sj1.getEng() + "\t"
						 + sj1.getMath() + "\t"
						 + sj1.getTotal() + "\t"
//						 + String.format("%.2f", sj1.getAvg()) + "\t"
						 + sj1.getAvg() + "\t"
						 + sj1.getGrade());


		SungJuk sj2 = new SungJuk();
		// 객체를 새로이 생성하면서 새로운 데이터 입력
		sj2.setData("프로도", 100, 89, 75);
		sj2.calc();
		System.out.println(sj2.getName() + "\t"
				 + sj2.getKor() + "\t"
				 + sj2.getEng() + "\t"
				 + sj2.getMath() + "\t"
				 + sj2.getTotal() + "\t"
//				 + String.format("%.2f", sj2.getAvg()) + "\t"
				 + sj2.getAvg() + "\t"
				 + sj2.getGrade());


		SungJuk sj3 = new SungJuk();
		sj3.setData("죠르디", 75, 80, 48);
		sj3.calc();
		System.out.println(sj3.getName() + "\t"
				 + sj3.getKor() + "\t"
				 + sj3.getEng() + "\t"
				 + sj3.getMath() + "\t"
				 + sj3.getTotal() + "\t"
				 + String.format("%5s", sj3.getAvg()) + "\t"
				 // %10s: 10자리 String 오른쪽 정렬
				 // %-10s: 10자리 String 왼쪽 정렬
				 // String.format("%.2f", sj3.getAvg()) + "\t"
				 // %.2f: 소수 2째자리까지 double/float return
//				 + sj3.getAvg() + "\t"
				 + sj3.getGrade());

		System.out.println("----------------------------------------------------------------");
		
		
	}
}


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

class name: SungJuk
field: name, kor, eng, math, total, avg, grade
method: setData(name, kor, eng, math), get
		calc() : total, avg, score 계산
		getName()
		getKor()
		getEng()
		getMath()
		getTotal()
		getAvg()
		getGrade()

class: SungJukMain
		
----------------------------------------------------------------
이름      국어      영어      수학      총점      평균      학점
----------------------------------------------------------------
홍길동      90        95       100
----------------------------------------------------------------
Array: data type
Class: 1 obj

*/
