package board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

// BoardListService.java 참조
public class BoardViewService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// 1. DATA
		// boardList.js에서 seq, pg를 넘겨받음
		int seq = Integer.valueOf(request.getParameter("seq"));
		int pg = Integer.valueOf(request.getParameter("pg"));
		// DB에서는 숫자로 선언되어 있으므로 param에서는 String, DB 저장 시 형변환 가능
		
		// boardViewT.jsp로 seq, pg를 넘김
		request.setAttribute("seq", seq);
		request.setAttribute("pg", pg);
		
		request.setAttribute("display", "/board/boardViewT.jsp");
		
		return "/index.jsp";			
	}

}
