package sample04;

import java.util.List;

import lombok.Setter;

public class SungJukOutput implements SungJuk {
	@Setter
	private List<SungJukDTO2> list = null;

/*	Using Constructor
	public SungJukOutput(List<SungJukDTO2> list) {
		this.list = list;
	}
*/
	@Override
	public void execute() {
		System.out.println();
		System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
		for(SungJukDTO2 sungJukDTO2 : list) {
			System.out.println(sungJukDTO2);
			// toString() overriding을 통해서 주소값이 아닌 값 출력
		}
	}

}
