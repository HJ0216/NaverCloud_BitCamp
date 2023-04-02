package member.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberDTO {	
	private String name;
	private String id;
	private String pwd;
	private String gender;
	private String email1;
	private String email2;
	private String tel1;
	private String tel2;
	private String tel3;
	private String zipcode;
	private String addr1;
	private String addr2;

	
	// private variable에 대한 setter와 getter를 통한 외부 class의 간접 접근 허용
	// lombok import 및 @Annotation을 통해서 setter getter 코드 입력 대신 사용

}
