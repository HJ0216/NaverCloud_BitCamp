package com.person;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void init() {}
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Server로서 Client의 query를 통한 요청 받기
		String name = request.getParameter("name"); // request.getParameter("name_parameter")
		// request: id_attribute가 아닌 name_attribue 기재
		String gender = request.getParameter("gender");
		String color = request.getParameter("color");
		String[] hobby = request.getParameterValues("hobby"); 
		String[] subject = request.getParameterValues("subject");
		
		// 2. Server로서 Client에 응답 하기
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<body style='background: yellow;'>");
		out.println("<ul style='border: 1px solid red; color : white; background: " + color + ";'>"); // ul: unordered(순서없는 글머리 기호)
		// 구역에 색깔 또는 표시를 통해 작업 중 환경 보기
		
		// * 이름: 
		out.println("<li>");
		out.println("이름: " + name);
		out.println("</li>");		
		
		
		// * 성별:
		out.println("<li>");
		out.println("성별: ");
		if(gender.equals("0")) {out.println("남자");}
		// String: equals
		else {out.println("여자");}
		out.println("</li>");		

		
		// * 색깔:
		out.println("<li>");
		out.println("색깔: ");
		switch(color) {
			case "red": out.println("<span style='color:red'>빨강</span>"); break;
			case "green": out.println("<span style='color:green'>초록</span>"); break;
			case "blue": out.println("<span style='color:blue'>파랑</span>"); break;
			case "magenta": out.println("<span style='color:magenta'>보라</span>"); break;
			case "cyan": out.println("<span style='color:cyan'>하늘</span>"); break;
		}
		out.println("</li>");		

		
		// * 취미: (다중 선택)
		out.println("<li>");
		out.println("취미: ");
			for(String element : hobby) {
				out.println(element);
			}
		out.println("</li>");		

		
		// * 과목:
		out.println("<li>");
		out.println("과목: ");
		for(int i=0; i<subject.length; i++) {
			out.println(subject[i]);
		}
		out.println("</li>");		


		out.println("</ul>");		

		out.println("</body>");
		out.println("</html>");		

	}

	public void destroy() {}
	
}
