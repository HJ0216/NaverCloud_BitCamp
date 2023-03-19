package member.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.dao.MemberDAO;

@WebServlet("/LoginServlet2")
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// data (param data type: String)
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		
		// DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		String name = memberDAO.memberLogin(id, pwd);
		// DAO.memberLogin에 id, pwd 전달
		// DAO.memberLogin에서 name return
		
		
		// response
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		
		if(name==null) {out.println("<h3> 아이디 또는 비밀번호 불일치 </h3>");}
		else {out.println("<h3>" + name + "님 로그인</h3>");}
		
		out.println("</body>");
		out.println("</html>");
		
	}

}
