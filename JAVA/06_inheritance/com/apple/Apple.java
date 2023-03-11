package com.apple; // pkg . 단위로 dir 생성

import com.zoo.*; // pkg가 java.lang이 아닌 경우, import 필요

public class Apple { // 현관문

	public static void main(String[] args) { // 강의실 문
		System.out.println("Red Apple");
		
		Zoo zoo  = new Zoo();
//		System.out.println(zoo.tiger(););
		// The method println(boolean) in the type PrintStream is not applicable for the arguments (void)
		zoo.tiger(); // public
//		zoo.giraffe(); // protected
//		zoo.elephant(); // default
//		zoo.lion(); // private
		
	}
	
}
