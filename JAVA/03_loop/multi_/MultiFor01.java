package multi_;

public class MultiFor01 {

	public static void main(String[] args) {
		for(int i=2; i<=4; i+=2) {
			for(int j=1; j<=3; j++) {
				System.out.println("i = " + i + " j = " + j);
			} // for: j
			System.out.println();			
		} // for: i
	}
	
}

/*
i = 2 j = 1
i = 2 j = 2
i = 2 j = 3

i = 4 j = 1
i = 4 j = 2
i = 4 j = 3
 */
