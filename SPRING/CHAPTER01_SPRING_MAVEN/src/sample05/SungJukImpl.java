package sample05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

@Component
public class SungJukImpl implements SungJuk {
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private double avg;
	
	
	public SungJukImpl() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("이름 입력: ");
		this.name = br.readLine();
		// this: 전역변수, field
		System.out.print("국어 입력: ");
		kor = Integer.parseInt(br.readLine());
		System.out.print("영어 입력: ");
		eng = Integer.parseInt(br.readLine());
		System.out.print("수학 입력: ");
		math = Integer.parseInt(br.readLine());
	}

	
	@Override
	public void calc() {
		total = kor + eng + math;
		avg = (total/3.0);
	}
	

	@Override
	public void display() {
		DecimalFormat df = new DecimalFormat("00.00");

		System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
		System.out.println(name 	+ "\t" 
						  + kor 	+ "\t"
						  + eng 	+ "\t"
						  + math 	+ "\t"
						  + total 	+ "\t"
						  + String.format("%.2f", avg));
						 // df.format(avg)
		
	}
}
