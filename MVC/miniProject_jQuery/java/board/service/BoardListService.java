package board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;


public class BoardListService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// 1. DATA
		int pg = Integer.valueOf(request.getParameter("pg"));
		// pg: From menu.jsp(query string) -> requestGetParameter로 받음
		
		request.setAttribute("pg", pg);
		// request 객체에 pg 속성값을 저장(key, value : pg, pg)
		request.setAttribute("display", "/board/boardListT.jsp");
		// request 객체에 "display"라는 이름으로 "/board/boardListT.jsp" 경로 지정
		// BoardListService.java 파일이 실행될 때, display 변수에 값이 저장됨
		
		return "/index.jsp";
		
	}
}
