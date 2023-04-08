package sample03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SungJukImpl implements SungJuk {
	private SungJukDTO sungJukDTO = null;
	
	
	// new를 통한 객체 생성 시, 의존관계를 설정하지 못함
	public SungJukImpl(SungJukDTO sungJukDTO){
		this.sungJukDTO = sungJukDTO;
	}
	
	
	@Override
	public void calcTot() { 
		sungJukDTO.setTot(sungJukDTO.getKor()
						+ sungJukDTO.getEng()
						+ sungJukDTO.getMath());
	}

	@Override
	public void calcAvg() {
		sungJukDTO.setAvg(sungJukDTO.getTot()/3.0);
		
	}

	@Override
	public void display() {
		System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
		// toString override 후
		System.out.println(sungJukDTO);

		// toString override 전
/*		System.out.println(sungJukDTO.getName() + "\t"
						 + sungJukDTO.getKor() + "\t"
						 + sungJukDTO.getEng() + "\t" 
						 + sungJukDTO.getMath() + "\t"
						 + sungJukDTO.getTot() + "\t"
						 + String.format("%.3f", sungJukDTO.getAvg()));
*/		
	}

	@Override
	public void modify() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try { // Override 때문에 throws보다 try-catch 사용
			System.out.print("이름 입력: ");
			sungJukDTO.setName(br.readLine());
			System.out.print("국어 입력: ");
			sungJukDTO.setKor(Integer.parseInt(br.readLine()));
			System.out.print("영어 입력: ");
			sungJukDTO.setEng(Integer.parseInt(br.readLine()));
			System.out.print("수학 입력: ");
			sungJukDTO.setMath(Integer.parseInt(br.readLine()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
