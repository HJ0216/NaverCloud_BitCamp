package com.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("/HelloServlet")
// web.xml: <servlet></servlet> <servlet-mapping></servlet-mapping>
public class HelloServlet extends HttpServlet {
	// HttpServlet를 상속해야 servlet 역할을 함
	// (최고 조상) Servlet - GenericServlet - HttpServlet - HelloServlet
	//						 init()			  doPost()		@Override init(), doPost()
	
	private static final long serialVersionUID = 1L;
	// 직렬화: 컴퓨터의 메모리 상에 존재하는 데이터를 파일로써 저장하거나,
	// 통신하는 다른 컴퓨터에게 알맞은 형식에 맞추어 전달하기 위해 바이트 스트림 형태로 만드는 것을 의미
	// SUID: 직렬화 버전 고유의 값으로 직렬화 버전 정보를 확인
	// 

	// init(), doPost(), destroy(): 시스템이 일정한 시점이 되면 스스로 메소드 호출(CallBack method)
	// java에서의 JVM이 main()을 스스로 호출한 것과 동일
	
	@Override
	public void init() {
		System.out.println("init()");
		// System.out 이므로 consol상에서 출력
	}
	
	
	@Override	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");
		// web상에서 보여지는 구간
		
//		response.setContentType("text/html");
		// 지금부터 입력되는 txt를 html로 인식(sql 구문과 동일)
		
//		1.
//		System.out.println("<html>");
		// system 객체 이용, consol 상에서 print 내용 출력
		
//		2.
//		Printwriter out = new PrintWriter(new FileWriter("Result.txt"));
		// FileWriter: 파일로 출력
		// IO Stream: byte 단위-OutputStream, 문자 단위-Writer
//		out.println("<html>");
		
//		3.
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		// getWriter: 웹으로 출력
		out.println("<html>");
		// println: 소스코드의 줄바꿈

		out.println("<body>");
		out.println("Hello Servlet!<br/>");
		// <br/> 화면상 줄바꿈
		out.println("안녕하세요!");		

		out.println("</body>");
		
		out.println("</html>");
		
		
	}
	
	
	@Override
	public void destroy() {
		System.out.println("destroy()");	
	}

}
