package board.bean;

public class BoardDTO {
	// private: 외부에서 직접 접근 불가
	private String name;
	private String email;
	private String subject;
	private String content;
	
	public void setName(String name) {this.name = name;}
	public void setEmail(String email) {this.email = email;}
	public void setSubject(String subject) {this.subject = subject;}
	public void setContent(String content) {this.content = content;}
	
	public String getName() {return name;}
	public String getEmail() {return email;}
	public String getSubject() {return subject;}
	public String getContent() {return content;}
	
}
