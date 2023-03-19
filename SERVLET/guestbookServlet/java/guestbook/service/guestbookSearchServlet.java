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

@WebServlet("/guestbookSearchServlet")
public class guestbookSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Data(query param data type: String)
		// html에서 넘어온 String param: name attribute (id attribute X)
		int seq = Integer.valueOf(request.getParameter("seq"));


		// DB: Servlet 과부하 방지: memberDAO(일반 java)에서 처리
//		GuestbookDTO guestbookDTO = new GuestbookDTO();
//		guestbookDTO.setSeq(seq);
		// guestbookSearch에서 직접 seq를 넘겨주므로 set 필요 X

		GuestbookDAO guestbookDAO = GuestbookDAO.getInstance();
		// Singleton: 메모리에 한 번만 생성하여 주소값을 참조하도록 하는 방법
		GuestbookDTO gbDTO = guestbookDAO.guestbookSearch(seq);
		
		// Using variable
//		String name = gbDTO.getName();
//		String logtime = gbDTO.getLogtime().getLogtime().substring(0, 10);
//		String email = gbDTO.getEmail();
//		String homepage = gbDTO.getHomepage();
//		String subject = gbDTO.getSubject();
//		String content = gbDTO.getContent();
		

		// response
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		// new가 아닌 method를 통한 out 객체 생성
		// out: web browser로 result return
		// web browser에서 html source code를 인식하여 출력

		// System.out: Consol로 result return

		// PrintWriter out = new PrintWriter(new FileWriter("result.txt"));
		// out.println("<html>")
		// *.txt file로 result return

		out.println("<html>");
		out.println("<body>");

//		if (gbDTO.getLogtime() == null) {
		if (gbDTO == null) {			
			// memberDTO를 기본적으로 null로 초기화하였음
			// 조회된 데이터가 있을 때, 즉 rs.next()가 있을 때
			// guestbookDTO 객체를 new를 통해서 생성하였으므로
			// get을 통해서 값을 따로 받아오지 않고 객체의 null값을 통해서 검색결과가 있는지 없는지 알 수 있음
			// 객체 생성 위치 및 Iinitialization을 통해서 코드 간결하게 만들기
			out.println("<h3> 검색결과 없음 </h3>");
		}

		else {
			out.println("<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\">");

			out.println("<tr>");
			out.println("<th width=\"150\"> 작성자 </th>");
			out.println("<th width=\"150\"> " + gbDTO.getName() + " </th>");
			out.println("<th width=\"150\"> 작성일 </th>");
			out.println("<th width=\"150\"> " + gbDTO.getLogtime().substring(0, 10) + " </th>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<th width=\"150\"> 이메일 </th>");
			out.println("<td colspan=\"3\">" + gbDTO.getEmail() + "</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<th width=\"150\"> 홈페이지 </th>");
			out.println("<td colspan=\"3\"> " +  gbDTO.getHomepage() + " </td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<th width=\"150\"> 제목 </th>");
			out.println("<td colspan=\"3\"> " + gbDTO.getSubject() + " </td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td height=\"200\" colspan=\"4\"><pre>" + gbDTO.getContent() + "</pre></td>");
			// java: \: " String 인식 X
			// 문제1: 개행 Enter 인식 X -> <pre></pre>: 입력한 모양 그대로 출력
			// 문제2: <pre></pre> 사용 시, 개행없이 글자를 나열할 경우, 줄바뀜없이 나열됨
			// 
			out.println("</tr>");

			out.println("</table>");
		}

		out.println("</body>");
		out.println("</html>");

	}

}
