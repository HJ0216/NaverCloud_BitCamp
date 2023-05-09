package person.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data // Getter, Setter, Constructor
@Component
// Bean Configuration 파일에 Bean을 따로 등록하지 않아도 사용할 수 있음
// 빈 등록자체를 빈 클래스 자체에서 할 수 있음
public class PersonDTO {
	private String name;
	private int age;
	private String photo;
	
}
