package array;

import java.util.Scanner;

public class Array03 {

	public static void main(String[] args) {
		int size;
		int[] ar;
		int sum = 0;
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the size of Array: ");
		size = scan.nextInt();
		
		ar = new int[size];
		
		for(int i=0; i<size; i++) {
			System.out.print("Enter the ar[" + i + "] : ");
			ar[i] = scan.nextInt();
			sum += ar[i];
		} // for: Sum: ar[0...size]
		
		for(int data : ar) {
			System.out.print(data + " ");
		}
				
		System.out.println("\nSum: " + sum);

		int max = ar[0];
		int min = ar[0];
//		int max, min;
//		max = min = ar[0];
//		int max, min = ar[0];
		
		for(int i = 0; i<ar.length; i++) {
			if(ar[i] > max) {max = ar[i];}
		} // for
		System.out.println("Max: " + max);

		for(int i = 0; i<ar.length; i++) {
			if(ar[i] < min) {min = ar[i];}
		} // for
		System.out.println("Min: " + min);

		// int[] 미활용 시
//		for(int i=0; i<3; i++) {
//			System.out.print("Enter the ar[" + i + "] : ");
//			int input = scan.nextInt();
//			System.out.println("ar[" + i + "] : " + input);
//			sum += input;
//		} // for
		
		scan.close();
		
	}
}

/*
[문제] 배열의 크기를 입력받아서 배열을 생성한다.

[실행결과]
배열 크기 입력 : 3

ar[0] 입력 : 25
ar[1] 입력 : 13
ar[2] 입력 : 57

25 13 57
합 = xxx

 */
