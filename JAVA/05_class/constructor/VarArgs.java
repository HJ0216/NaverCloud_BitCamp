package constructor;

public class VarArgs {
	
	public VarArgs() {System.out.println("Default Constructor");}

	// parameter가 int type일 경우, 가변인자를 매개변수로 받을 수 있음 ... ar(name)
	public int sum(int ... ar) {
		int hap=0;
		for(int i=0; i<ar.length; i++) {
			hap += ar[i];
		}
		return hap;
	}

//	public int sum(int a, int b) {return a+b;}
//	// parameter 내, type은 각자 선언
//	public int sum(int a, int b, int c) {return a+b+c;} // overload
//	public int sum(int a, int b, int c, int d) {return a+b+c+d;} // overload

	
	public static void main(String[] args) {
		VarArgs va = new VarArgs();
		System.out.println("Sum: " + va.sum(10, 20));
		System.out.println("Sum: " + va.sum(10, 20, 30));
		System.out.println("Sum: " + va.sum(10, 20, 30, 40));
		
	}
	
}
