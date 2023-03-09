package operator;

public class Dice {

	public static void main(String[] args) {
		int dice1, dice2;

		dice1 = (int)(Math.random()*6+1);
		dice2 = (int)(Math.random()*6+1);

		String result = dice1 > dice2 ? "Dice1 win" : dice1 < dice2 ? "Dice2 win" : "Draw";
		System.out.println("Dice1: " + dice1 + ", Dice2: " + dice2 + ", Result: " + result);
		
//		dice1 = Math.random();
		// int <- Double : Type mismatch: cannot convert from double to int
		// Math.random(): static
		
	}
	
}



/*
[문제] 주사위 게임 - 난수
2개의 주사위를 던져서 큰값을 가진 주사위가 승이다.
주사위 값의 합도 출력하시오

[실행결과]
주사위1 : 3   주사위2 : 1
주사위1 승
-----------------------------
주사위1 : 4   주사위2 : 6
주사위2 승
-----------------------------
주사위1 : 3   주사위2 :3
무승부

 */
