package sample03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SungJukDTO{ // Setter Injection
	private String name;
	private int kor, eng, math, tot;
	private double avg;
	
	public String getName() {
		return name;
	}

	@Autowired
	public void setName(@Value("홍길동") String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	@Autowired
	public void setKor(@Value("97") int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	@Autowired
	public void setEng(@Value("100") int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	@Autowired
	public void setMath(@Value("95") int math) {
		this.math = math;
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
		this.tot = tot;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}


	@Override
	public String toString() {
	// 객체 출력 시, 주소가 아닌 값 return하도록 override
		return name + "\t"
			 + kor+ "\t"
			 + eng+ "\t"
			 + math+ "\t"
			 + tot+ "\t"
			 + String.format("%.3f", avg);
	}

}
