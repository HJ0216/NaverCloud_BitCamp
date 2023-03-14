package member;

import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MemberFileOutput implements Member {

	@Override
	public void execute(List<MemberDTO> list) {
	// Override된 method의 throws 선언 시, interface 수정 및 subClass 수정 필요
	// 연쇄반응으로 인하여 throws보다는 try-catch 활용
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("member.txt"));
			
			for(MemberDTO mDTO : list) {
				oos.writeObject(mDTO);
			} // list의 mDTO가 oos를 통해서 파일 내용으로 쌓임
			
			oos.close();
			System.out.println("\nSaved data\n");
			
		} catch(IOException e) {e.printStackTrace();}
		
		
	};

}

/*
MemberFileInput.java -/OjectOutputStream/-> Buffer -/FileOutputStream/-> File(member.txt)
Obj: MemberDTO를 파일로 저장하기 위함이므로
MemberDTO를 serialize 해야 함
*/
