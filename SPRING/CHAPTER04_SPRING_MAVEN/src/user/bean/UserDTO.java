package user.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
// 다른 객체에서 UserDTO를 ref로 참고할 경우, UserDTO에 대한 Component 선언 필요
public class UserDTO {
	private String name;
	private String id;
	private String pwd;
	
	public String getName() {
		return name;
	}

	
	// ?????????????????????????????????????????? setName에 @Autowired 사용하면 안되는 이유?????????????????????????????
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
