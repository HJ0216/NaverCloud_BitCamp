package member;

import java.util.List;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.EOFException;

import io.PersonDTO;

public class MemberFileInput implements Member {
	
	@Override
	public void execute(List<MemberDTO> list) {
		list.clear(); // 기존 출력되는 값과 저장된 값의 중복 방지
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("member.txt"));


			while(true) {
				try {
					MemberDTO mDTO = (MemberDTO) ois.readObject(); // mDTO에 저장
					list.add(mDTO); // list에 추가
				} catch(EOFException e) {
					break;
				}
			} // list의 mDTO가 oos를 통해서 파일 내용으로 쌓임
			
		ois.close();
		System.out.println("\nAdd list Complete\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	};

}
