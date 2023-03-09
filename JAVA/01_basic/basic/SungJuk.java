package basic;

public class SungJuk {

	public static void main(String[] args) {
		char name = 'L';
		int kor = 85;
		int eng = 78;
		int math = 100;
		
		int total = kor + eng + math;
		float avg = total / 3.0f;
		
		System.out.println("	***" + name + " Score ***");
		System.out.print("kor\teng\tmath\ttotal\tavg\n");
		System.out.print(kor + "\t" + eng + "\t" + math + "\t" + total + "\t" + String.format("%.2f", avg) + "\n");
		// double, float 모두 형식 지정자: %f
		
	}

}

/*
[문제] 성적 계산 (소수이하 2째자리까지 출력)
이름(name): L
국어(kor): 85
영어(eng): 78
수학(math): 100

총점(total) = 국어 + 영어 + 수학
평균(avg) = 총점 / 과목수

[실행 결과]
	***L Score ***
kor  eng  math  total  avg
85   78   100   263   87.67

\n, \t 활용
*/