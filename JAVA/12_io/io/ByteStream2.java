package io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

public class ByteStream2 {

	public static void main(String[] args) {
		try {
			File file = new File("data.txt");
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			System.out.println("File.length(): " + (int) file.length());
			// array.length <- ()없음 유일, String.length(), file.length()
			
			int size = (int) file.length();
			byte[] b = new byte[size];
			
			bis.read(b, 0, size);
			// b[](data가 있는 배열)로 0번째부터 size만큼 read
			System.out.println(new String(b));
			// b: Array@ref_address
			// new String(b): byte[] -> String
			
			bis.close();
			
			
		
		} catch(IOException e) {e.printStackTrace();}

	}
}
