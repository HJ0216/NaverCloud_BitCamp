package class__;

public class FruitMain_T {

	public static void main(String[] args) {
		Fruit_T[] arrFruit = new Fruit_T[3];
		// class array 생성 및 obj_ref_address 저장
		
//		Fruit_T[] arrFruit = {new Fruit_T("Apple", 100, 80, 75),
//							  new Fruit_T("Grape", 30, 25, 10),
//							  new Fruit_T("StrBr", 25, 30, 90)}
		
		arrFruit[0] = new Fruit_T("Apple", 100, 80, 75);
		arrFruit[1] = new Fruit_T("Grape", 30, 25, 10);
		arrFruit[2] = new Fruit_T("StrBr", 25, 30, 90);
		// obj_ref_address -> class data: variable, method 저장

		System.out.println("-----------------------------------");
		System.out.println("PUM\tJAN\tFEB\tMAR\tTOT");
		System.out.println("-----------------------------------");

		for(Fruit_T f : arrFruit) {
			f.calcTotal();
			// arrFruit[0].sumJan 값 사용을 해야 함
			// sumJan, sumFeb, sumMar: class 내부 값이 아닌 static variable 값이 되어야 함
			f.disp();
		}
		
//		arrFruit[0].calcTotal();
//		arrFruit[0].disp();
//		arrFruit[1].calcTotal();
//		arrFruit[1].disp();
//		arrFruit[2].calcTotal();
//		arrFruit[2].disp();

		System.out.println("-----------------------------------");
		Fruit_T.output(); // static
		

	}
}

/*
과일 판매량 구하기
월별 매출합계도 구하시오

클래스 : Fruit
Field: pum, jan, feb, mar, tot
sumJan, sumFeb, sumMar

생성자(품명, 1월, 2월, 3월)
method: 
calcTot()
display()
public static void output()
** 객체 배열
** Scanner X, 직접 입력

클래스 : FruitMain

[실행결과]
객체 배열
---------------------------------
PUM      JAN   FEB   MAR     TOT
---------------------------------
사과    100    80    75      255
포도     30    25    10       65
딸기     25    30    90      145
---------------------------------
        155   135   175            output()로 처리
 */
