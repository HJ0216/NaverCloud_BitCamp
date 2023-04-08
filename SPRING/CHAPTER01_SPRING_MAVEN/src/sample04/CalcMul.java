package sample04;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
// Component () 생략 시, className으로 bean name 지정(CalcMul ▶ calcMul)
public class CalcMul implements Calc {

	@Override
	public void calculate(int x, int y) {
		System.out.println(x +" * " + y + " = " + (x*y));
		
	}

}
