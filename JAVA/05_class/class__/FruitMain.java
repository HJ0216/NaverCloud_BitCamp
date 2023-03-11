package class__;

public class FruitMain {

	public static void main(String[] args) {
		Fruit[] fruit = new Fruit[3]; // Object Arr

		fruit[0] = new Fruit("사과", 100, 80, 75);
		fruit[1] = new Fruit("포도", 30, 25, 10);
		fruit[2] = new Fruit("사과", 25, 30, 90);
		
		
		System.out.println("---------------------------------");
		System.out.println("PUM\tJAN\tFEB\tMAR\tTOT");
		System.out.println("---------------------------------");
		for(Fruit data: fruit) {
			if(data != null) {
				data.display();
			} // if
		} // for

		System.out.println("---------------------------------");
		for(Fruit data: fruit) {
			if(data != null) {
				data.output();
			} // if
		} // for

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
