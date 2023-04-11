package com.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SungJukDTO {
	private String name;
	private int kor, eng, math, tot;
	private double avg;
	
	// *.jsp, DTO.java DB_col을 동일하게 선언해야 자동 Mapping 가능

/*	
	public String getName() {
		return name;
	}
	public int getKor() {
		return kor;
	}
	public int getEng() {
		return eng;
	}
	public int getMath() {
		return math;
	}
	public int getTot() {
		return tot;
	}
	public double getAvg() {
		return avg;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public void setTot(int tot) {
		this.tot = tot;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
*/

}
