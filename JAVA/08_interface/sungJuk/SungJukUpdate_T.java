package sungJuk;

import java.util.Scanner;
import java.util.ArrayList;

public class SungJukUpdate_T implements SungJuk {
	String name_rev;
	int kor_rev;
	int eng_rev;
	int math_rev;

	@Override
	public void execute(ArrayList<SungJukDTO> arrayList) {
		System.out.println();
		Scanner scan = new Scanner(System.in);
		
		System.out.print("번호 입력: ");
		int num = scan.nextInt();
		
//		int i;
//		for(i = 0; i<arrayList.size(); i++) {
//			if(num==arrayList.get(i).getNum()) {break;}
//		}
//		if(i==arrayList.size()) {System.out.println("없는 번호입니다.");}
//		else {
//			System.out.println(arrayList.get(i));
//
//			System.out.print("수정할 이름 입력: ");
//			name_rev = scan.next();
//			System.out.print("수정할 국어 입력: ");
//			kor_rev = scan.nextInt();
//			System.out.print("수정할 영어 입력: ");
//			eng_rev = scan.nextInt();
//			System.out.print("수정할 수학 입력: ");
//			math_rev = scan.nextInt();
//
//		} // 배열이 아니므로 get() 사용
		
		int sw=0;
		for(SungJukDTO sungJukDTO : arrayList) {
			if(sungJukDTO.getNum()==num) {sw=1; break;}

			else {
				System.out.println(sungJukDTO);

				System.out.print("수정할 이름 입력: ");
				name_rev = scan.next();
				System.out.print("수정할 국어 입력: ");
				kor_rev = scan.nextInt();
				System.out.print("수정할 영어 입력: ");
				eng_rev = scan.nextInt();
				System.out.print("수정할 수학 입력: ");
				math_rev = scan.nextInt();
				
				sungJukDTO.setName(name_rev);
				sungJukDTO.setKor(kor_rev);
				sungJukDTO.setEng(eng_rev);
				sungJukDTO.setMath(math_rev);
				
				sungJukDTO.calc();
				
				System.out.println("수정하였습니다.");
			}
		}
		// sw=0일 경우, !=0인 상태
		// sw=1인 경우, ==0인 상태
		
		if(sw==0) System.out.println("없는 번호입니다.");

		scan.close();		
		
	} // execute
	
}
