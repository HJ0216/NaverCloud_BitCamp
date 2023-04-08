package sample05;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutputter implements Outputter {
	private String filePath, fileName;
	
	public FileOutputter() {
		System.out.println("FileOutputter Default Constructor");
	}
	
	public void setFilePath(String filePath) {
		System.out.println("setFilePath(String filePath)");
		this.filePath = filePath;
	}

	public void setFileName(String fileName) {
		System.out.println("setFileName(String fileName)");
		this.fileName = fileName;
	}

	@Override
	public void output(String msg) {
		System.out.println("output(String msg)");
		
		try {
			FileWriter fw = new FileWriter(new File(filePath, fileName));
			fw.write(msg);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}


/*
IO Stream
1. byte 단위
InputStream
OutputStream

 2. 문자(2byte) 단위
 FileWriter
 FileReader
*/
