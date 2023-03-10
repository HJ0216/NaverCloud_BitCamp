package array;

import java.util.Scanner;

public class Lotto {

	public static void main(String[] args) {
		int[] lotto = new int[6];
		// 1000원당 크기가 6개인 lottoArr 생성
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("현금 입력: ");
		int input = scan.nextInt();
				
		for(int l=1; l<=input/1_000; l++) {
//			input -= 1_000; // 1_000원씩 차감
//			if((l+1)%5==0) {System.out.println();}
			
			for(int i=0; i<lotto.length; i++) {
				lotto[i] = (int)(Math.random()*45+1);
								
				for(int j=0; j<i; j++) {
					if(lotto[i]==lotto[j]) {
						i--;
						break;}
				} // for_j: 중복 제거
				
			} // for_i: 난수 생성

		
			for(int j=0; j<lotto.length-1; j++) {
				for(int k=j+1; k<lotto.length; k++) {
					if(lotto[j]>lotto[k]) {
						int tmp = lotto[k];
						lotto[k] = lotto[j];
						lotto[j] = tmp;
					} // if: Bubble Sort: 가장 큰 숫자가 오른쪽에 위치
				} // for_k
			} // for_j

			for(int lotto_data : lotto) {
				System.out.print(String.format("%5d", lotto_data));
			} System.out.println();

			if(l%5==0) {System.out.println();} // if: 5개 출력 후 줄 바꾸기
			
		} // final
		
		scan.close();
		
	}
}

/*
[문제] 자동 Lotto
: 크기가 6개인 배열 생성
: 1-45 사이의 난수 발생(중복 제거)
: 오름차순 출력(Selection Sort 사용)
: 출력 시 자리 수 5자리
 */