package com.el;

public class Compute {
	public static int sum(String x, String y) { // static method로 설정해야 사용 가능
		return Integer.parseInt(x) + Integer.parseInt(y);
	}

	public static double mul(String x, String y) {
		return Double.parseDouble(x) * Double.parseDouble(y);
	}

}
