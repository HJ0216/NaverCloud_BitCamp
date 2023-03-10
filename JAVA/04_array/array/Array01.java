package array;

public class Array01 {

	public static void main(String[] args) {
		int[] ar;
//		int ar[];	
		ar = new int[5];
		
		System.out.println("Array_name ar: " + ar); // [I@3d012ddd
		// ar: ar[0] ref_address return
		// Integer@ref_address(HexaDecimal)

		ar[0] = 25;
		ar[1] = 37;
		ar[2] = 55;
		ar[3] = 42;
		ar[4] = 30;

		System.out.println("Array_len: " + ar.length);
		
		for(int i=0; i<ar.length; i++) { // 배열 길이에 맞춰서 i값 제한
			System.out.println("ar[" + i + "] = " + ar[i]);
		}
		
		System.out.println();
		System.out.println("=====Deascending=====");
		
		for(int i=ar.length; i>0; i--) { // 배열 길이에 맞춰서 i값 제한
			System.out.println("ar[" + (i-1) + "] = " + ar[(i-1)]);
		}
		
		System.out.println();
		System.out.println("=====Odd_Number=====");
		
		for(int i=0; i<5; i++) {
			if(ar[i]%2==1) {
				System.out.println("Odd Number: ar[" + i + "]=" + ar[i]);
			}
			
		System.out.println();
		System.out.println("=====Extension_For=====");
		
		for(int data : ar) { 
		// 확장형 for: ar의 args를 data로 순서대로 반환
			System.out.println("data: " + data);
			}	

		}
		
	}
	
}
