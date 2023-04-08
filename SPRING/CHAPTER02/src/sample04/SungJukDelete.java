package sample04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
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

/*
			list.size()=3:
			index로 접근하여 제거할 경우, list[1] 제거 시 다음 반복문에서는 list[2]를 찾아감
			list는 자체적으로 인덱스를 재배열하므로 list[2]가 list[1]이 되면서 error 발생할 수 있음 
			▶ iterator 사용 
			
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
*/
			
			// Using Iterator			
			Iterator<SungJukDTO2> it = list.iterator();
			int sw=0;
			while(it.hasNext()) {
			// it.hasNext(): list에서 다음 항목이 있을 경우, ture 반환
			// it가 가리키는 곳(list[0])의 항목을 꺼내서 저장소에 저장 후, it는 다음 항목(list[1])을 가리킴
				SungJukDTO2 sungJukDTO2 = it.next();
				// it.next(): list[0]
				
				if(sungJukDTO2.getName().equals(name)) {
					sw=1;
					it.remove();
					// it 저장소 항목 제거
					System.out.println("데이터 삭제 완료\n");
				} // if
				
			} // while
			
			if(sw==0) {System.out.println("검색 일치 대상 없음\n");}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
