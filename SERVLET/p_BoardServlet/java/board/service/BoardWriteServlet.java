package board.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

@WebServlet("/BoardWriteServlet")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post: UTF-8 명시적 encoding
		request.setCharacterEncoding("UTF-8");
		
		// Data(String param)
		// Servlet으로 넘어오는 form data = name attribute
		String name = request.getParameter("name_Name");
		String email = request.getParameter("name_Email");
		String subject = request.getParameter("name_Subject");
		String content = request.getParameter("name_Content");
		
		// DB: Servlet 과부하 방지: memberDAT(일반 java)에서 처리

		// param이 많을 경우, DTO class를 활용해 하나의 객체로 받기
		BoardDTO boardDTO = new BoardDTO();
		
		boardDTO.setName(name);
		boardDTO.setEmail(email);
		boardDTO.setSubject(subject);
		boardDTO.setContent(content);
		// setter, getter 함수 이름 확인
		
		// DB connect
		BoardDAO boardDAO = BoardDAO.getInstance();
		int insert_num = boardDAO.boardWrite(boardDTO);
		
		
		// response
		response.setContentType("text/html; charset=UTF-8");
		// 이하 source code는 html
		PrintWriter out = response.getWriter();
		// 출력값: Web Browser
		
		out.println("<html>");
		out.println("<body>");
		if(insert_num==0) {
			out.println("<h3>작성하신 글을 저장하는데 실패하였습니다.</h3>");
		}
		else {
			out.println("<h3>작성하신 글을 저장하였습니다.</h3>");
			out.println("<input type=\"button\" value=\"글목록\" onclick=\"location.href='/p_BoardServlet/BoardListServlet'\">");
			out.println("</body>");
			out.println("</html>");
		}

		
	}

}
