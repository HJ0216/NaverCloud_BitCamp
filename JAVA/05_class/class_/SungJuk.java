package class_;

public class SungJuk {

	private String name;
	private int kor, eng, math, total; // 동일한 type: 같은 행에 선언
	private double avg;
	private char grade;
	
	public void setData(String name, int i, int i2, int i3) {
		this.name = name;
		// lv와 field를 구분하기 위해서 this. 사용
		kor = i;
		eng = i2;
		math = i3;
	}
	
	public String getName() {return name;}
	public int getKor() {return kor;}
	public int getEng() {return eng;}
	public int getMath() {return math;}

	// 1. calc()를 통해서 계산 후에 return만 반환하는 method() 만들기
	// 2. calc와 return method를 합쳐서 하나로 만들기
	public void calc() { // return 없이 계산만
		total = (kor + eng + math);
		
		avg = total/3.;

		if(avg>=90) {grade='A';}
		else if(avg>=80) {grade='B';}
		else if(avg>=70) {grade='C';}
		else if(avg>=60) {grade='D';}
		else {grade='F';}

		}
	// 산식은 method 내에서만 정의 가능
	
	public int getTotal()  {return total;}
	public String getAvg() {return String.format("%.2f", avg);}
	public char getGrade() {return grade;}
	// return은 void or 1개만 가능하므로 data type의 섞여서는 안됨
	
}
