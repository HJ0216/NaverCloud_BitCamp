package io;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class ObjectMain {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// ClassNotFoundException: avoid, if there is no class
		PersonDTO pDTO = new PersonDTO("홍길동", 25, 185.3);
		
		
		// 파일로 출력
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("result2.txt"));
		oos.writeObject(pDTO);
		oos.close();
		
		
		// 파일 내용 읽어오기
		File file = new File("result2.txt");
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		PersonDTO data = (PersonDTO) ois.readObject();
		// readObject: data type=Obj
		// 단순 obj가 아니라 PersonDTO class를 통해서 get()을 사용하기 위해 obj type 명확화
		// 다형성: child = (child) Parent; casting
		System.out.println("Name: " + data.getName());
		System.out.println("Age: " + data.getAge());
		System.out.println("Height: " + data.getHeight());
		
		ois.close();
	}
}
