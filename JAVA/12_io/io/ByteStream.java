package io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

public class ByteStream {

	public static void main(String[] args) throws IOException {
	// 발생할 수 있는 Exception JVM에 전달
		
		FileInputStream fis = new FileInputStream(new File("data.txt"));
		// FileInputStream fis = new FileInputStream("data.txt");
		// new File("data.txt"): File 객체로 가져와서 Stream에 넘기기
		BufferedInputStream bis = new BufferedInputStream(fis);
		// A -(FileInputStream)-> Buffer: A -(BufferedInputStream)-> (int)A

		int data;
//		data = bis.read(); // 더이상 반환할 data가 없을 때: 객체(return null), int(return -1)
		
		while((data = bis.read()) != -1) {
			System.out.print((char)data);
			// Enter(커서를 맨 앞으로 이동 + 아래줄로 이동): 13(Carriage Return-처음으로) 10(Line Feed-아래로)
			
		}
		
	}
	
}
