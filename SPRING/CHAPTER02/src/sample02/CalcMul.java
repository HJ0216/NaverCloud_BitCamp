package sample02;

import lombok.Setter;

@Setter
public class CalcMul implements Calc {
	// @Setter
	private int x, y;
	// 일부만 Setter 설정을 원할 경우, class 내부에서 선언가능
	

	@Override
	public void calculate() {
		System.out.println(x +" * " + y + " = " + (x*y));

	}

}
