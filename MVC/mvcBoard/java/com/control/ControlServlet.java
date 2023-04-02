package com.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// tomcat은 web파일이 아닌 서블릿 파일을 인식하지 못하므로
// 반드시 서블릿 파일은 web.xml에 등록하거나 어노테이션으로 등록해야함

@WebServlet(
	urlPatterns= {"*.do"}, // .do로 끝나는 request(url)에 대해서
	initParams= {
			@WebInitParam(name="propertyConfig", value="command.properties") // 해당 properties를 참고
			// value: properties 이름, name: 경로의 이름
	}
)
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> map = new HashMap<>();
     
	@Override
	public void init(ServletConfig config) throws ServletException {
		// 환경설정 내용을 map에 저장하고 꺼내쓰게되므로 환경설정 변경 시, 서버 재로딩 필요
		String propertyConfig = config.getInitParameter("propertyConfig");
		// WebInitParam의 value 반환(command.properties)
		System.out.println("propertyConfig: " + propertyConfig);
		
		String realFolder = config.getServletContext().getRealPath("/WEB-INF");
		String realPath = realFolder + "/" + propertyConfig;
		// realPath: /WEB-INF 내부의 .properties 실제 위치
		System.out.println("realPath: " + realPath);
		
		
		FileInputStream fin = null;
	      Properties properties = new Properties();
	      
	      try {
	         fin = new FileInputStream(realPath);
	         // realPath 파일(command.properties) 내용 읽기
	                     
	            properties.load(fin);
	            System.out.println("properties = "+properties);
	            
	         } catch (IOException e) {
	            e.printStackTrace();
	         }finally{
	            try {
	               fin.close();
	            } catch (IOException e) {
	               e.printStackTrace();
	            }
	         }
	      
	         System.out.println();
	         
	         // properties 파일을 읽을 때, map type으로
	         // key, value로 처리
	         
	         Iterator it = properties.keySet().iterator();
	         while(it.hasNext()) {
	            String key = (String)it.next();
	            System.out.println("key = "+key);
	            
	            String className = properties.getProperty(key);
	            System.out.println("className = "+className);
	            
	            try {
	               Class<?> classType = Class.forName(className);
	               Object ob = classType.newInstance();
	               
	               System.out.println("ob = "+ob);
	               
	               map.put(key, ob);
	               
	            } catch (ClassNotFoundException e) {
	               e.printStackTrace();
	            } catch (InstantiationException e) {
	               e.printStackTrace();
	            } catch (IllegalAccessException e) {
	               e.printStackTrace();
	            } catch (IllegalArgumentException e) {
	               e.printStackTrace();
	            } 
	            
	            System.out.println();
	         }//while
		
	}
	// init() overriding
	// 환경설정은 한 번만 읽으면 되므로 한번만 실행되는 init()에서 환경설정 파일을 읽도록 구현
	
    // 모든 요청을 servlet에서 받아야하므로 doGet, doPost모두 생성해야 함
	// Get, Post: 데이터가 url에 보이는지 유무, request에서 추가적으로 encoding이 필요한지 여부만 차이남
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
		// doGet, doPost 인코딩, url 제외하고 역할이 동일하므로 execute()로 메서드를 통일하여 구현
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
		// doGet, doPost 인코딩, url 제외하고 역할이 동일하므로 execute()로 메서드를 통일하여 구현
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println();
		
		// doPost를 위한 한글 처리
		if(request.getMethod().equals("POST")) {request.setCharacterEncoding("UTF8");}
		// request.getMethod(): GET or POST return (함수X)
		// 반드시 "POST" 대문자로 작성
		
		String category = request.getServletPath();
		// getServletPath(/member/writeForm.do): url에서 ""만 추출하는 역할
		// servlet에서 잘라진 /member/writeForm.do를 들고 command.properties로 이동해서 연관된 java 파일 탐색
	    System.out.println("category = " + category);
	    // request.getServletPath(): client의 server 접근(request) url(/member/writeForm.do) 반환
		// http://localhost:8080/mvcMember/member/writeForm.do
	    
	    CommandProcess com = (CommandProcess)map.get(category);
	    // properties에서 읽어들인 값을 map에 저장
	    // map.get("key") -> value return
	    // map.get("/member/write.do") -> member.service.WriteServlet
	    String view = null;
	    
	    try {
	       view = com.requestPro(request, response);
	       // 해당 java 파일의 매서드 호출
	       // 1. client가 웹페이지에 접근
	       // 2. request, response가 담긴 requestPro 변수 다형성에 따라 모든 자바 파일의 parent 파일인 interface에 전달
	       // 3. 부모 interface를 기능에 따라 구현한 자식 class에서 *.jsp return하고 해당 *.jsp가 view 변수에 저장
	       System.out.println("view: " + view); // servlet이 java 파일로부터 return 받은 jsp 파일 return
	    } catch (Throwable e) {
	       e.printStackTrace();
	    }
	      
	    //forward(servlet과 jsp를 병합)
	    RequestDispatcher dispatcher = request.getRequestDispatcher(view); // getRequestDispatcher("합병되어질 jsp 파일의 상대번지")
	    // view: 자식 class가 return한 jsp 파일
	    // forward를 통해서 servlet과 return된 jsp를 병합했기 때문에 url: servlet, 결과: jsp 반환
	    // 합병을 통해서 request, response value 공유
	    dispatcher.forward(request, response);
	    // dispatcher(합병되어질 jsp파일)의 제어권을 합병 주체(Servlet 파일)에게 넘김
	}

}
