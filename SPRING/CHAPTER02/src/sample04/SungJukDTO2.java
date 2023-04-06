package sample04;

import lombok.Getter;
import lombok.Setter;

@Setter // Setter Injection
@Getter
public class SungJukDTO2 {
	private String name;
	private int kor, eng, math, tot;
	private double avg;

	@Override
	public String toString() {
	// sungJukDTO2 객체 출력 시, 주소가 아닌 값 return하도록 override
		return name + "\t"
			 + kor + "\t"
			 + eng + "\t"
			 + math + "\t"
			 + tot + "\t"
			 + String.format("%.3f", avg);
	}
}
