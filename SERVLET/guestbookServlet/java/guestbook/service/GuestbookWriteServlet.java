package guestbook.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.bean.GuestbookDTO;
import guestbook.dao.GuestbookDAO;

@WebServlet("/GuestbookWriteServlet")
public class GuestbookWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 데이터(param으로 넘어오는 data type: String)
		// servlet으로 넘어오는 데이터는 name attribute (id attribute가 아님)
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String homepage = request.getParameter("homepage");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		
		// DB: memberDAO(일반 java)에서 처리 -> Servlet 과부하 방지
		GuestbookDTO guestbookDTO = new GuestbookDTO();
		// html에서 입력받은 query value를 직접 DAO의 인자로 받기보다는 1인분 단위 class인 MemberDTO로 갹체화하여 전달
		guestbookDTO.setName(name);
		guestbookDTO.setEmail(email);
		guestbookDTO.setHomepage(homepage);
		guestbookDTO.setSubject(subject);
		guestbookDTO.setContent(content);
		
		
		GuestbookDAO guestbookDAO = GuestbookDAO.getInstance();
		// Singletone: 메모리에 한 번만 생성하여 주소값을 참조하도록 하는 방법
		int row = guestbookDAO.guestbookWrite(guestbookDTO);
		
		
		// response
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		if(row==0) {
			out.println("<h3>작성하신 글을 저장하는데 실패하였습니다.</h3>");
		}
		else {
			out.println("<h3>작성하신 글을 저장하였습니다.</h3>");
			out.println("<input type=\"button\" value=\"글목록\" onclick=\"location.href='/guestbookServlet/GuestbookListServlet'\">");
			out.println("</body>");
			out.println("</html>");
		}
		
	}

}
