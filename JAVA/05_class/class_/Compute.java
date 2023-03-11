package class_;

public class Compute {
		private int x, y, sum, sub, mul;
		private double div;
		
		public void setData(int x, int y) {
			this.x = x;
			this.y = y;
		} // field variable을 나타내는 this는 생략 가능하나 lv와 구분이 필요할 때 작성
		
		public int getX() {return x;}
		public int getY() {return y;}

		// 사칙연산과 return 문 나누기
		public void calc() {
			sum = x + y;
			sub = x - y;
			mul = x * y;
			div = (double) x / y;
			// 값에 1.0 곱해서 안되면 강제 형변환 진행			
		}
		
		
		// return
		public int getSum() {return sum;}		
		public int getSub() {return sub;}		
		public int getMul() {return mul;}
		public double getDiv() {return div;}
		
	
}

/*
[문제] obj array를 활용하여 사칙연산
x, y를 입력하여 합, 차, 곱, 몫을 구하시오.
(단, 소수 이하 2자리까지)

[실행 결과]
횟수 입력: 2

[1번째]
x 입력: 25
y 입력: 36

[2번째]
x2 입력: 35
y2 입력: 12

x  y  sum  sub mul div
25 36
35 12
 */
