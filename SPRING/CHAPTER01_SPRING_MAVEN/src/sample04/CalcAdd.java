package sample04;

import org.springframework.stereotype.Component;

@Component("calcAdd")
// Component () 지정 시, 지정된 이름으로 bean name 지정
public class CalcAdd implements Calc {

	@Override
	public void calculate(int x, int y) {
		System.out.println(x +" + " + y + " = " + (x+y));
	}

}
