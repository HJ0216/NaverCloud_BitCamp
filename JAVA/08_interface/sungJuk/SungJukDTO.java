package sungJuk;

import java.util.Scanner;
import java.text.DecimalFormat;

public class SungJukDTO implements Comparable<SungJukDTO> {
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private double avg;
	
	Scanner scan = new Scanner(System.in);
	
	public SungJukDTO() {} // Default Constructor
	
	public SungJukDTO(int num, String name, int kor, int eng, int math) {
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		
	} // Constructor(num, name, kor, eng, math)

	public void calc() {
		total = kor + math + eng;
		avg = total/3.0;
	}
	
	public void setNum(int num) {this.num = num;}
	public void setName(String name) {this.name = name;}
	public void setKor(int kor) {this.kor = kor;}
	public void setEng(int eng) {this.eng = eng;}
	public void setMath(int math) {this.math = math;}
	public void setTotal(int total) {this.total = total;}
	public void setAvg(double avg) {this.avg = avg;}

	public int getNum() {return num;}
	public String getName() {return name;}
	public int getKor() {return kor;}
	public int getEng() {return eng;}
	public int getMath() {return math;}
	public int getTotal() {return total;}
	public double getAvg() {return avg;}

	DecimalFormat df = new DecimalFormat("0.00");
	
	@Override
	public String toString() {
		return num + "\t"
			+ name + "\t"
			+ kor + "\t"
			+ eng + "\t"
			+ math + "\t"
			+ total + "\t"
			+ df.format(avg);
	}
	
	@Override
	public int compareTo(SungJukDTO sjDTO) {
		if(this.total>sjDTO.total) {return -1;}
		else if(this.total<sjDTO.total) {return 1;}
		else {return 0;}
	} // compareTo
	
}