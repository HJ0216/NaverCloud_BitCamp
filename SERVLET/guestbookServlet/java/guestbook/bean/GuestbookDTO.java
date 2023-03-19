package guestbook.bean;

public class GuestbookDTO {
	private int seq; // 나중에 글목록 출력 시, SEQ: 글번호 사용
	private String name;
	private String email;
	private String homepage;
	private String subject;
	private String content;
	private String logtime; // 나중에 글 목록 출력 시, LOGTIME: 작성 시간
	// 계산이 필요한 경우, Data type: Date
	
	
	
	public void setSeq(int seq) {this.seq = seq;}
	public void setName(String name) {this.name = name;}
	public void setEmail(String email) {this.email = email;}
	public void setHomepage(String homepage) {this.homepage = homepage;}
	public void setSubject(String subject) {this.subject = subject;}
	public void setContent(String content) {this.content = content;}
	public void setLogtime(String logtime) {this.logtime = logtime;}

	
	
	public int getSeq() {return seq;}
	public String getName() {return name;}
	public String getEmail() {return email;}
	public String getHomepage() {return homepage;}
	public String getSubject() {return subject;}
	public String getContent() {return content;}
	public String getLogtime() {return logtime;}
	
}
