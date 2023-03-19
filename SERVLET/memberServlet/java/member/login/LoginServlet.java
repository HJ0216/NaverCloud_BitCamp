package member.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

// web.xml or annotation을 통한 servlet file web과 연결
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// data (param data type: String)
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
				

		// DB
		MemberDTO memberDTO = new MemberDTO();
		// 회원 각각의 로그인 데이터 -> 1인분 class: memberDTO 사용
		memberDTO.setId(id);
		memberDTO.setPwd(pwd);		
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		// memberDAO 역할: DB 연결
		// new: 사용 시마다 DB 재연결, 과부하 문제 발생 가능성
		// static을 활용한 memory reuse
		int result_login = memberDAO.loginTry(memberDTO);
		// param의 객체화를 통한 전달 활용: 코드 간결화
		
		
		// response
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		
		if(result_login==1) {out.println("<h3>" + memberDAO.getNameDB() + " 성공</h3>");}
		// getter를 통한 외부 class variable에 접근
		else {out.println("<h3>아이디 또는 비밀번호 불일치</h3>");}
		
		out.println("</body>");
		out.println("</html>");



	}

}
