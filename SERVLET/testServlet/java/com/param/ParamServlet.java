package com.param;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// life cycle function
	public void init() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Server로서 Client의 query를 통한 요청 받기
		String name = request.getParameter("name"); // "name" : name_value 받기
		int age = Integer.parseInt(request.getParameter("age"));
		
		
		// 2. Server로서 Client에 응답 하기
		response.setContentType("text/html; charset=UTF-8");
		// response type: 응답을 text/html로 return하고, charset=UTF-8로 encoding 방식 지정
		// html이 java에서 실행되는게 아니라 웹으로 <html>을 선언한걸 보내고 웹에서 구동
		PrintWriter out = response.getWriter();
		// getWriter(): 웹으로 출력하는 메서드
		out.println("<html>");
		out.println("<body>");
		out.println("<h3>");
		
		out.println("이름: " + name + ", 나이: " + age);
		if(age>=20) {out.println("성인");}
		else {out.println("청소년");}

		out.println("</h3>");		
		out.println("</body>");
		out.println("</html>");
		
		
	}
	
	public void destroy() {} // re-compile

}
