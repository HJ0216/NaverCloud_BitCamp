package io; // InputOutput

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
//import java.io.FileNotFoundException;


public class DataStream {

	public static void main(String[] args) {
		try {
//			DataOutputStream dos = new DataOutputStream(new FileOutputStream("result.txt"));
			// 1. result.txt
			// 2. new FileOutputStream("result.txt")
			// 3. new DataOutputStream(new fos)
			
			FileOutputStream fos = new FileOutputStream("result.txt");
			// result.txt가 없을 경우에 대한 대비 필요
			// Unhandled exception type FileNotFoundException
			DataOutputStream dos = new DataOutputStream(fos);
			
			dos.writeUTF("홍길동");
			// Unhandled exception type IOException
			// dos에 대한 입출력 문제에 대한 try-catch 처리 필요
			dos.writeInt(25);
			dos.writeDouble(185.3);
			
			dos.close(); // avoid resource leakage
			
			
			FileInputStream fis = new FileInputStream("result.txt");
			DataInputStream dis = new DataInputStream(fis);
			String name = dis.readUTF();
			int age = dis.readInt();
			double height = dis.readDouble();
			
			System.out.println(name + "\t" + age + "\t" + height);
						
		} catch(IOException e) {e.printStackTrace();}
		// parent: IOExcepiton, child: FileNotFoundException
	}
}

// result.txt byte