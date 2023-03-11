package class__;

public class Fruit {
	private String pum;
	private int jan, feb, mar, total;
	private static int sumJan;
	private static int sumFeb;
	private static int sumMar;

	public Fruit() {} // Default Constructor
	
	public Fruit(String pum, int jan, int feb, int mar) {
		this.pum = pum;
		this.jan = jan;
		this.feb = feb;
		this.mar = mar;
	} // Constructor	
	

	static Fruit fruit = new Fruit();

	
	public String getPum()  {return pum;}
	public int getJan() 	{return jan;}
	public int getFeb() 	{return feb;}
	public int getMar() 	{return mar;}
	
	int i;
	public void calcTot() {
		total = jan + feb + mar;
	}

	public void display() {
		System.out.print(fruit.getPum() + "\t" + fruit.getJan() + "\t" + fruit.getFeb() + "\t" + fruit.getMar() + "\t");
		fruit.calcTot();
		System.out.println(fruit.total);
	}

	
	public static void output() {
		sumJan += fruit.getJan();
		sumFeb += fruit.getFeb();
		sumMar += fruit.getMar();
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
