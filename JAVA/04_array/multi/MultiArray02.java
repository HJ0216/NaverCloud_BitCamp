package multi;

public class MultiArray02 {

	public static void main(String[] args) {
		int[][] ar = new int[10][10];
		int num = 0;
		
		for(int i=0; i<ar.length; i++) {
			for(int j=0; j<ar[i].length; j++) {
				ar[i][j] = num++; 
			}
		} // Result: 0 1 2 ...

		
//		for(int i=0; i<ar.length; i++) {
//			for(int j=0; j<ar[i].length; j++) {
//				ar[j][i] = ++num; 
//			}
//		} // Result: 1 2 3 ...

		
//		for(int i=0; i<ar.length; i++) {
//			for(int j=0; j<ar[i].length; j++) {
//				ar[i][j] = 100-(num++); 
//			}
//		} // Result: 100 99 98 ...

		
//		for(int i=ar.length-1; i>=0; i--) {
//			for(int j=ar[i].length-1; j>=0; j--) {
//				ar[i][j] = num++; 
//			}
//		} // Result: 99 98 97 ...


		for(int i=0; i<ar.length; i++) { // ar -> ar[0], ar[1] ... ar[9]
			for(int j=0; j<ar[i].length; j++) { // ar[0] -> ar[0][0], ar[0][1] ... ar[0][9]
				System.out.print(String.format("%2d  ", ar[i][j]));
			} System.out.println();
		}
			
	}
	
	
}


/*
ar:
ar[0] -> ar[0][0], ar[0][1], ar[0][2] ... , ar[0][9]
ar[1] -> ar[1][0], ar[1][1], ar[1][2] ... , ar[1][9]
ar[2] -> ar[2][0], ar[2][1], ar[2][2] ... , ar[2][9]
ar[3] -> ar[3][0], ar[3][1], ar[3][2] ... , ar[3][9]
ar[4] -> ar[4][0], ar[4][1], ar[4][2] ... , ar[4][9]
ar[5] -> ar[5][0], ar[5][1], ar[5][2] ... , ar[5][9]
ar[6] -> ar[6][0], ar[6][1], ar[6][2] ... , ar[6][9]
ar[7] -> ar[7][0], ar[7][1], ar[7][2] ... , ar[7][9]
ar[8] -> ar[8][0], ar[8][1], ar[8][2] ... , ar[8][9]
ar[9] -> ar[9][0], ar[9][1], ar[9][2] ... , ar[9][9]

ar: ar[0]의 주소값 return
ar[0]: ar[0][0]의 주소값 return

 */
