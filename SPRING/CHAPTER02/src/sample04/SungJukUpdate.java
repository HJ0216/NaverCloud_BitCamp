package sample04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import lombok.Setter;

public class SungJukUpdate implements SungJuk {
	@Setter
	private List<SungJukDTO2> list = null;

/*	
	@Override
	public void execute() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("찾고자하는 이름: ");
		try {
			String name= br.readLine();
			
			for(SungJukDTO2 sungJukDTO2 : list) {
				if(name.equals(sungJukDTO2.getName())){
					System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
					System.out.println(sungJukDTO2);
					
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
					
					System.out.println(sungJukDTO2.getName() + "수정 완료");
					
					return;
				} // if
				
			} // for
			System.out.println("대상 없음");	
						
		} catch (IOException e) {
			e.printStackTrace();
		}
*/

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("수정 할 이름:");
		String name = scan.next();
		
		// sw변수를 활용한 else 구문 출력
		int sw=0;
		for(SungJukDTO2 sungJukDTO2 : list){
			if(sungJukDTO2.getName().equals(name)){
				sw=1;
				
				System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
				System.out.print(sungJukDTO2 + "\n\n");
				
				System.out.print("국어 입력: ");
				sungJukDTO2.setKor(scan.nextInt());
				System.out.print("영어 입력: ");
				sungJukDTO2.setEng(scan.nextInt());
				System.out.print("수학 입력: ");
				sungJukDTO2.setMath(scan.nextInt());					
				sungJukDTO2.setTot(sungJukDTO2.getKor()
						+ sungJukDTO2.getEng()
						+ sungJukDTO2.getMath());
		
				sungJukDTO2.setAvg(sungJukDTO2.getTot()/3.0);
				
				System.out.println("\n" + sungJukDTO2.getName() + " 수정 완료\n");
								
				break;
			} // if
		} // for
		
		if(sw==0) {System.out.print("검색 대상 없음\n\n");}

		
	}

}
