package sample03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // class를 bean으로 생성, 객체로 생성
public class SungJukImpl implements SungJuk { // Constructor Injection
	@Autowired
	private SungJukDTO sungJukDTO = null;
	// new를 통한 객체 생성 시, 의존관계를 설정하지 못함
/*
	@Autowired
	Impl() -> DTO 객체 참조: @Autowired 사용하여 .xml에 DTO bean을 확인 // 1. 객체를 참고하고자 할 때
	.xml -> DTO 객체 참조: DTO에서 공유할 메서드/변수에 @Autowired를 선언 // 2. 객체에서 공유하고자 하는 변수에 선언
 
*/

	
/*	
	생성된 beans(1, 2, 3, 4 방법 모두 포함) 중에서 SungJukDTO를 찾아서 값을 얻어오기(자동 mapping)
	> bean에 대한 호출 필요 X
	
	public SungJukImpl(SungJukDTO sungJukDTO){
		this.sungJukDTO = sungJukDTO;
		
	
	* Bean 생성
	1. @Component
	2. @Bean
	3. @Service: miniProject_jQuery의 Service bean 생성시
	4. @Repository: DB와 연동하는 java 파일
	
	}
*/	
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
