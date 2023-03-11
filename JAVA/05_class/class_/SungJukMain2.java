package class_;

public class SungJukMain2 {

	public static void main(String[] args) {
		SungJuk[] ar = new SungJuk[3]; // object array
		// new : array 생성(obj 생성X)
		// ar: ar[0] ar[1] ar[2]
		// Class type variable: Obj
		
//		ar[0] = new SungJuk();
//		ar[1] = new SungJuk();
//		ar[2] = new SungJuk();		
		// ar[0](배열 자리)만 생성된 상태이므로 obj에 대한 memory allocation 필요

		ar[0] = new SungJuk();
		ar[0].setData("홍길동", 91, 95, 100);
		// ar[i]마다 입력 데이터가 달라지므로 따로 입력
		// interpreter방식으로 ar[0]에 대한 메모리 할당이 먼저 이뤄져야 함
		// 객체 생성을 for문에 돌릴 경우, setData없이 데이터가 계산되므로 상단에 먼저 기재되어야 함
		
		ar[1] = new SungJuk();
		ar[1].setData("프로도", 100, 89, 75);
		
		ar[2] = new SungJuk();
		ar[2].setData("죠르디", 75, 80, 48);
		
		
		for(int i=0; i<ar.length; i++) {
//			ar[i] = new SungJuk();
			ar[i].calc();
			System.out.println(ar[i].getName() + "\t"
					 + ar[i].getKor() + "\t"
					 + ar[i].getEng() + "\t"
					 + ar[i].getMath() + "\t"
					 + ar[i].getTotal() + "\t"
//					 + String.format("%.2f", ar[i].getAvg()) + "\t"
					 + ar[i].getAvg() + "\t"
					 + ar[i].getGrade());

			
		}		

	}
}
