package sample04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import lombok.Setter;

public class SungJukDelete implements SungJuk {
	@Setter
	private List<SungJukDTO2> list = null;

	@Override
	public void execute() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("찾고자하는 이름: ");
		try {
			String name= br.readLine();
			
			for(int i=0; i<list.size();i++) {
				if(name.equals(list.get(i).getName())) {
					System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
					System.out.println(list.get(i));
					
					System.out.println("\n" + name + " 데이터 삭제 완료");
					
					list.remove(i);
					
					return;
					
				} // if
				
			} // for
						
			System.out.println("대상 없음");	
						
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
