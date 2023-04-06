package sample02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// @Component
public class CalcMul implements Calc {
	private int x, y;
	
	// 생성자: 객체를 생성 시 자동으로 읽음
	// setter: 객체 생성 시 자동으로 읽지 않음 > @Autowired
	
	@Autowired // setX가 읽히면서 field x의 값에 value가 인식됨
	public void setX(@Value("25") int x){
		this.x = x;
	} 
	@Autowired
	public void setY(@Value("36") int y){
		this.y = y;
	}

	@Override
	public void calculate() {
		System.out.println(x +" * " + y + " = " + (x*y));

	}

}
