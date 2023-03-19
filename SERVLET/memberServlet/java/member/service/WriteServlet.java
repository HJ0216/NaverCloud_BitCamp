package member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.bean.MemberDTO;
import member.dao.MemberDAO;


// Servlet file 생성 시, web.xml 등록 or Annotation 활용

//@WebServlet("/WriteServlet")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void init() {}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// get 방식인 경우에는 param이 한글인 경우에 대해 인코딩이 따로 필요없지만, post인 경우 인코딩 필요
		
		
		// html query data: name attribute (id attribute X), String
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
//		String repwd = request.getParameter("repwd"); pwd 확인용으로 파라미터로 받아올 필요 X
		String gender = request.getParameter("gender");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String zipcode = request.getParameter("zipcode");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		
		
		// DB: Servlet 과부하 방지 -> memberDAO(일반 java)에서 처리
		MemberDTO memberDTO = new MemberDTO();
		// html query value가 다수: 직접 DAO의 인자로 받기보다는 1인분 단위 class인 MemberDTO로 객체화하여 전달
		memberDTO.setName(name);
		memberDTO.setId(id);
		memberDTO.setPwd(pwd);
		memberDTO.setGender(gender);
		memberDTO.setEmail1(email1);
		memberDTO.setEmail2(email2);
		memberDTO.setTel1(tel1);
		memberDTO.setTel3(tel3);
		memberDTO.setZipcode(zipcode);
		memberDTO.setAddr1(addr1);
		memberDTO.setAddr2(addr2);

//		MemberDAO memberDAO = new MemberDAO(); new memory loading -> 과부하 문제
		// 회원가입 시마다 DB와의 연결을 새롭게 해줄 경우, 과부하 문제가 발생할 수 있음
		// new 연산자를 통한 memory 할당보다는 static을 활용하여 기존재 할당된 메모리를 재사용
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		int row = memberDAO.memberWrite(memberDTO);
		// memberDAO.memgerWrite()에 param으로 memberDTO를 넘겨서 코드를 간결화
		// memberDAO.memberWrite: sql DTO 개수(n개의 행이 삽입 -> n개)를 반환

		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		if(row==0) {
			out.println("<h3>회원가입 실패</h3>");
			out.println("<input type='button' value='뒤로' onclick='history.go(-1)'>");
		}
		else {
			out.println("<h3>회원가입 성공</h3>");
			out.println("<input type='button' value='로그인' onclick=location.href='http://localhost:8080/memberServlet/member/login.html'>");}
			out.println("</body>");
			out.println("</html>");
		}
	

	@Override
	public void destroy() {}

}
