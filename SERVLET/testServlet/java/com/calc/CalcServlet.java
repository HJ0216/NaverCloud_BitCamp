package com.calc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void init() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Server로서 Client의 query를 통한 요청 받기
		// method: get_parameter: http://localhost:8080/testServlet/CalcServlet?x=25&y=10
		int x = Integer.parseInt(request.getParameter("x")); // "name", id_value 받기
		int y = Integer.parseInt(request.getParameter("y")); // "name", id_value 받기
		
		
		// 2. Server로서 Client에 응답 하기
		response.setContentType("text/html; charset=UTF-8");
		// download 창 발생 시, setContentType() <- 오타
		PrintWriter out = response.getWriter();
		// response type: text/html; charset=UTF-8
		// getWriter: 웹으로 출력
		// html이 java에서 실행되는게 아니라 웹으로 <html>을 선언한걸 보내고 웹에서 구동
		// 해당 java code에서는 java language가 실행

		// *.html - js 호출 : 서로 연동되어있음
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h3>");
		
		out.println("X: " + x + ", Y: " + y);
		out.println("<br/>");
		out.println(x + " + " + y + " = " + (x+y));
		out.println("<br/>");
		out.println(x + " - " + y + " = " + (x-y));
		out.println("<br/>");
		out.println(x + " * " + y + " = " + (x*y));
		out.println("<br/>");
		out.println(x + " / " + y + " = " + String.format("%.2f",x*1.0/y));
		out.println("<br/>");


		out.println("</h3>");
		
		out.println("<input type='button' value='back' onclick='javascript:history.go(-1)'>");
		// input type='button' onclick -> 무조건 js호출이 되므로 생략가능
		// 데이터값이 남아있음
		out.println("<input type='button' value='back' onclick=location.href='http://localhost:8080/testServlet/calc/input.html'>");
		out.println("<input type='button' value='연령제한' onclick=location.href='http://localhost:8080/testServlet/param.html'>");
		// location.href='url' <-원하는 location 안내
		// 새로 고침을 통해서 데이터 값이 초기화 됨
		// ""내 문자 ''활용
		// history.go(-1): 1페이지 뒤로가기
		
		// *.html <- 공백 발생 시, url 연결 or 발생이 안됨

		
		out.println("</body>");
		out.println("</html>");
		
		
	}

	public void destroy() {
		
	}

}
