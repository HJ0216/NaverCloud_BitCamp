package sungJuk;

import java.util.ArrayList;

public class SungJukList implements SungJuk {

	@Override
	public void execute(ArrayList<SungJukDTO> arrayList) {
		System.out.println("번호\t이름\t국어\t영어\t수학\t총점\t평균");
		
		for(int i=0; i<arrayList.size(); i++) {
			System.out.print(arrayList.get(i).getNum() + "\t");
			System.out.print(arrayList.get(i).getName() + "\t");
			System.out.print(arrayList.get(i).getKor() + "\t");
			System.out.print(arrayList.get(i).getEng() + "\t");
			System.out.print(arrayList.get(i).getMath() + "\t");
			System.out.print(arrayList.get(i).getTotal() + "\t");
			System.out.print(String.format("%.2f", (arrayList.get(i).getAvg())) + "\n");
			
//			System.out.println(arrayList.get(i)); // sungJukDTO ref_address return
		}
		
		
/*		for(SungJukDTO sjDTO : arrayList) {
			System.out.print(sjDTO.getNum() + "\t"
							+sjDTO.getName() + "\t"
							+sjDTO.getKor() + "\t"
							+sjDTO.getEng() + "\t"
							+sjDTO.getMath() + "\t"
							+sjDTO.getTotal() + "\t"
							+String.format("%.2d", (sjDTO.getAvg())) + "\n");
		}
*/
		
/*		for(SungJukDTO sungJukDTO : arrayList) {
			System.out.println(sungJukDTO);
		} // toString Override를 통한 결과값 return(원래대로라면 ref_address return)
*/			
			
	}

}
