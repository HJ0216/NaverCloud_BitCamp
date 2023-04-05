package sample04;

import org.springframework.stereotype.Component;

@Component("calcAdd")
// 대소문자 구분없이 class name과 bean id가 동일하면 생략 가능
public class CalcAdd implements Calc {

	@Override
	public void calculate(int x, int y) {
		System.out.println(x +" + " + y + " = " + (x+y));
	}

}
