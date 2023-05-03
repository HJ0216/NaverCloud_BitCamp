package user.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// @Data: Setter, Getter, Constructor 설정
public class UserDTO {
	private String name;
	private String id;
	private String pwd;
	
	// DTO 설정 시, writeForm.jsp의 variable name과 동일하게 기재
}
