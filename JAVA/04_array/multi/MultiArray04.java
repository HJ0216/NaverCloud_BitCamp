package multi;

public class MultiArray04 {

	public static void main(String[] args) {
		int[][] ar = new int[3][]; // 가변 다차원 array
		
		ar[0] = new int[2]; // 0행 = 방 2개
		ar[1] = new int[3]; // 0행 = 방 3개
		ar[2] = new int[4]; // 0행 = 방 4개
		
		ar[0][0] = 10;
		ar[0][1] = 20;
		
		ar[1][0] = 30;
		ar[1][1] = 40;
		ar[1][2] = 50;
		
		ar[2][0] = 60;
		ar[2][1] = 70;
		ar[2][2] = 80;
		ar[2][3] = 90;
		
		System.out.println("Array_name(address): " + ar + "\n");
		// [[I@3d012ddd: [[ 2차원 배열, I: Integer, @3d012ddd: @ref_address
		
		for(int i=0; i<ar.length; i++) {
			System.out.println("Row ar[" + i + "](address): " + ar[i]);
			// ar[0]: [I@626b2d4a: [ 1차원 배열, I: Integer, @626b2d4a: @ref_address
			
			for(int j=0; j<ar[i].length; j++) {
				System.out.println("ar[" + i + "][" + j + "] = " + ar[i][j]);
			} System.out.println();
		}
		
	}
	
}
