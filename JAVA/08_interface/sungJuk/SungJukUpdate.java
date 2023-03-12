package sungJuk;

import java.util.ArrayList;
import java.util.Scanner;

public class SungJukUpdate implements SungJuk {
	int input;
	String name_rev;
	int kor_rev;
	int eng_rev;
	int math_rev;
	
	Scanner scan = new Scanner(System.in);
	
	@Override
	public void execute(ArrayList<SungJukDTO> arrayList) {
		System.out.print("번호 입력: ");
		input = scan.nextInt();
		
		int j;
		for(j=0; j<arrayList.size(); j++) {
			if(input == arrayList.get(j).getNum()) {break;}
		} // for: find number 
		
		if(j==arrayList.size()) {
			System.out.println("잘못된 번호입니다.");
		} // if: Not Found Number
		 
		int i;
		for(i=0; i<arrayList.size(); i++) {
			// 성적 출력
			if(input==arrayList.get(i).getNum()) {
				System.out.println("번호\t이름\t국어\t영어\t수학\t총점\t평균");
				System.out.print(arrayList.get(i).getNum() + "\t");
				System.out.print(arrayList.get(i).getName() + "\t");
				System.out.print(arrayList.get(i).getKor() + "\t");
				System.out.print(arrayList.get(i).getEng() + "\t");
				System.out.print(arrayList.get(i).getMath() + "\t");
				System.out.print(arrayList.get(i).getTotal() + "\t");
				System.out.print(String.format("%.2f", (arrayList.get(i).getAvg())) + "\n");

				System.out.print("수정할 이름 입력: ");
				name_rev = scan.next();
				System.out.print("수정할 국어 입력: ");
				kor_rev = scan.nextInt();
				System.out.print("수정할 영어 입력: ");
				eng_rev = scan.nextInt();
				System.out.print("수정할 수학 입력: ");
				math_rev = scan.nextInt();
				
				arrayList.get(i).setName(name_rev);
				arrayList.get(i).setKor(kor_rev);
				arrayList.get(i).setEng(eng_rev);
				arrayList.get(i).setMath(math_rev);	
				arrayList.get(i).calc();

				System.out.println("\n수정하였습니다.");
			}
		} // for: check for the update
		
	} // execute
}
