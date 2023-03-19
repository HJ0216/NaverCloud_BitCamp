package guestbook.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.bean.GuestbookDTO;
import guestbook.dao.GuestbookDAO;

@WebServlet("/GuestbookListServlet")
public class GuestbookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Data X -> PAGING 처리
		int pg = Integer.valueOf(request.getParameter("pg"));
		// 직접 입력해주는 param은 param name을 기재
		
		/* Paging 처리: 1 pg당 2 게시글
		        startNum endNum
		PG=1 BETWEEN 1 AND 2
		PG=2 BETWEEN 3 AND 4
		PG=3 BETWEEN 5 AND 6
		 */

		int endNum = pg*2;
		int startNum = endNum-1;
		
		// DB
		GuestbookDAO guestbookDAO = GuestbookDAO.getInstance();
		ArrayList<GuestbookDTO> list = guestbookDAO.guestbookList(startNum, endNum);
		// 들고갈 data를 param X		
		
		// 총 글수, 총 페이지수
		int totalA = guestbookDAO.getTotalA();
		System.out.println(totalA);
		
//		int totalP = (totalA+1)/2;
		int totalP = (int) Math.ceil(totalA/2);
		
		
		// Response
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		
		out.println("<style>");
		out.println("#currentPagingDiv {float: left; border: 1px red solid; color: black; width: 20px; height: 20px; margin-left: 5px; text-align: center;}");
		out.println("#PagingDiv {float: left; border: 1px red solid; color: yellow; width: 20px; height: 20px; margin-left: 5px; text-align: center;}");
		out.println("#currentPaging {color: red; text-decoration: none;}");
		out.println("#paging {color: black; text-decoration: none;}");
		// float: left를 통해서 div 줄 단위 입력을 한 줄로 만들어 줌
		out.println("</style>");
		
		out.println("<body>");
		
		// page number print
		for(int i=1; i<=totalP; i++) {
			if(i==pg) {
				out.println("<div id='currentPagingDiv'><a id='currentPaging' href='/guestbookServlet/GuestbookListServlet?pg=" + i + "'>" + i + "</a></div>");
			} else {
				out.println("<div id='PagingDiv'><a id='paging' href='/guestbookServlet/GuestbookListServlet?pg=" + i + "'>" + i + "</a></div>");
			}
		}
		// <span>: width, height 기능이 없어서 네모 박스를 만들 수 없음
		// <div>: 한 줄 단위라서 width, height를 사용하더라도 줄 바뀜이 생김
		out.println("<br><br>");
		
		if(list!=null) {
			for(GuestbookDTO guestbookDTO : list) {
				out.println("<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\">");

				out.println("<tr>");
				out.println("<th width=\"150\"> 작성자 </th>");
				out.println("<th width=\"150\"> " + guestbookDTO.getName() + " </th>");
				out.println("<th width=\"150\"> 작성일 </th>");
				out.println("<th width=\"150\"> " + guestbookDTO.getLogtime().substring(0, 10) + " </th>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<th width=\"150\"> 이메일 </th>");
				out.println("<td colspan=\"3\">" + guestbookDTO.getEmail() + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<th width=\"150\"> 홈페이지 </th>");
				out.println("<td colspan=\"3\"> " +  guestbookDTO.getHomepage() + " </td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<th width=\"150\"> 제목 </th>");
				out.println("<td colspan=\"3\"> " + guestbookDTO.getSubject() + " </td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td height=\"200\" colspan=\"4\"><pre>" + guestbookDTO.getContent() + "</pre></td>");
				// java: \: " String 인식 X
				// 문제1: 개행 Enter 인식 X -> <pre></pre>: 입력한 모양 그대로 출력
				// 문제2: <pre></pre> 사용 시, 개행없이 글자를 나열할 경우, 줄바뀜없이 나열됨
				// 
				out.println("</tr>");

				out.println("</table>");
				out.println("<hr style='border-color: red; width: 648px; margin: 10px 0;'>");
				// margin: 시계 방향, top, right, bottom, left
				// 10px 0: top/bottom=10px, right/left=0
			}

		} // if
		
		out.println("</body>");
		out.println("</html>");

	
	}

}
