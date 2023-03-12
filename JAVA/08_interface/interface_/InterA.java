package interface_;

public interface InterA { // Not class But interface
	// Constant
	public static final String NAME = "Hong_Gil_Dong";
	int AGE = 25;
	// interface 구현 목적에 따라 public(구현) static fianl(상수화) 생략 가능
	
	// Abstract Method
	public void aa();
	// interface 내부에서는 abstract method만 사용가능하므로 abstract 생략 가능
	public void bb();

}
