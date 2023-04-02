package board.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardDTO {	
	private int seq;
	private String id;
	private String name;
	private String email;
	private String subject;
	private String content;

	private int ref;
	private int lev;
	private int step;
	private int pseq;
	private int reply;
	private int hit;
	private Date logtime;
	
	// private variable에 대한 setter와 getter를 통한 외부 class의 간접 접근 허용
	// lombok import 및 @Annotation을 통해서 setter getter 코드 입력 대신 사용

	// DTO 생성 시, SQL table Col name 순서와 동일하게 작성
	// * Oracle col_name = DTO variable_name = jsp name_attribute
}