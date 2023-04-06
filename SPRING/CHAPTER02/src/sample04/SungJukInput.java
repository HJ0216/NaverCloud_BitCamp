package sample04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import lombok.Setter;

public class SungJukInput implements SungJuk {
	@Setter
	private SungJukDTO2 sungJukDTO2 = null;
	@Setter
	private List<SungJukDTO2> list = null;

/*	
    // Using Constructor
	public SungJukInput(SungJukDTO2 sungJukDTO2, List<SungJukDTO2> list) {
		this.sungJukDTO2 = sungJukDTO2;
		this.list = list;
	}
*/	
	@Override
	public void execute() {
		
		// DATA
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try { // Override 때문에 throws보다 try-catch 사용
			System.out.print("이름 입력: ");
			sungJukDTO2.setName(br.readLine());
			System.out.print("국어 입력: ");
			sungJukDTO2.setKor(Integer.parseInt(br.readLine()));
			System.out.print("영어 입력: ");
			sungJukDTO2.setEng(Integer.parseInt(br.readLine()));
			System.out.print("수학 입력: ");
			sungJukDTO2.setMath(Integer.parseInt(br.readLine()));
			
			sungJukDTO2.setTot(sungJukDTO2.getKor()
							+ sungJukDTO2.getEng()
							+ sungJukDTO2.getMath());
			
			sungJukDTO2.setAvg(sungJukDTO2.getTot()/3.0);
			
			
			// ArrayList에 저장
			list.add(sungJukDTO2);			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(sungJukDTO2.getName() + " 성적 입력 완료\n");

	}

}
