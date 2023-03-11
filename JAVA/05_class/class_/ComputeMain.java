package class_;

import java.util.Scanner;

public class ComputeMain {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		System.out.print("횟수 입력: ");
		int input = scan.nextInt();

		int[] x = new int[input];
		int[] y = new int[input];		
		Compute[] cpt = new Compute[input]; // 객체 배열 생성
		
		System.out.println();
		
		for(int i=0; i<input; i++) {
			System.out.println("["+ (i+1) + "번째]");
			System.out.print("x" + (i+1) + " 입력: ");
			x[i] = scan.nextInt();
			System.out.print("y" + (i+1) + " 입력: ");
			y[i] = scan.nextInt();
			
			System.out.println();
			
			cpt[i] = new Compute(); // 각 배열에 객체 생성
			cpt[i].setData(x[i], y[i]);

		} // for_i: 데이터 입력 및 객체에 저장

		
		System.out.println("X\tY\tSUM\tSUB\tMUL\tDIV");
				
		for(int i=0; i<cpt.length; i++) {
			cpt[i].calc();
			System.out.println(cpt[i].getX()   + "\t"
							 + cpt[i].getY()   + "\t"
							 + cpt[i].getSum() + "\t"
							 + cpt[i].getSub() + "\t"
							 + cpt[i].getMul() + "\t"
							 + String.format("%.2f", cpt[i].getDiv()));
		} // for_i: 객체에 저장된 값 출력
		
		// 확장형 for문
//		for(compute data: cpt) {
//		 	Compute[] cpt = new Compute[input];
			// cpt는 Compute[]이므로 data의 data type은 Compute(Class type, Obj)
//			data.calc();
			// 저장된 data의 값으로 calc() 실행
//			System.out.println(data.getX()   + "\t"
//							 + data.getY()   + "\t"
//							 + data.getSum() + "\t"
//							 + data.getSub() + "\t"
//							 + data.getMul() + "\t"
//							 + String.format("%.2f", data.getDiv()));
//			
//		}		
		
		
		scan.close();
	}
}

/*
Result
횟수 입력: 2

[1번째]
x1 입력: 4
y1 입력: 6

[2번째]
x2 입력: 9
y2 입력: 7

X	Y	SUM	 SUB	MUL 	DIV
4	6	10	  -2	 24	   0.67
9	7	16	   2	 63	   1.29

 */
