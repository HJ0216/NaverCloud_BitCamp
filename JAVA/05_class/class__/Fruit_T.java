package class__;

public class Fruit_T {
	private String pum;
	private int jan, feb, mar, total;
	private static int sumJan, sumFeb, sumMar;
	
	public Fruit_T(String pum, int jan, int feb, int mar) { // variable_name 맞추기
		this.pum = pum;
		this.jan = jan;
		this.feb = feb;
		this.mar = mar;
	}
	
	public void calcTotal() {
		total = (jan + feb + mar);
		sumJan += jan;
		sumFeb += feb;
		sumMar += mar;
	}
		
	public void disp() {
		System.out.println(pum + "\t" + jan + "\t" + feb + "\t" + mar + "\t" + total);
	}
	
	public static void output() {
		System.out.println("\t" + sumJan + "\t" + sumFeb + "\t" + sumMar);
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
