package sungJuk;

import java.util.Scanner;
import java.util.ArrayList;

public class SungJukInsert implements SungJuk {

	Scanner scan = new Scanner(System.in);
	SungJukService sungJukService = new SungJukService();
	
	@Override
	public void execute(ArrayList<SungJukDTO> arrayList) {
		// arrayList: SungJukDTO 활용
		
		System.out.print("Enter the Number: ");
		int num = scan.nextInt();
		
		System.out.print("Enter the Name: ");
		String name = scan.next();

		System.out.print("Enter the Score of kor: ");
		int kor = scan.nextInt();
		
		System.out.print("Enter the Score of eng: ");
		int eng = scan.nextInt();
		
		System.out.print("Enter the Score of math: ");
		int math = scan.nextInt();
		
		// SungJukDTO에 저장
		SungJukDTO sjDTO = new SungJukDTO(num, name, kor, eng, math);
		// 입력받은 num, name, kor, eng, math를 sjDTO에 저장
		sjDTO.calc();
		// total, avg 계산
		arrayList.add(sjDTO);
		// sjDTO obj: num ... math, total, avg 저장
		// sjDTO obj ref_address를 arrayList에 저장
		// new: sjDTO 당 memory allocation 후 arrayList에 저장
		System.out.println("Saved the data");

	} // execute()
	
}
