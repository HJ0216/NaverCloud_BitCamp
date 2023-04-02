package board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

// BoardViewService.java 참조
public class BoardUpdateFormService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// 1. DATA
		// pg, seq from boardViewT.jsp
		int seq = Integer.valueOf(request.getParameter("seq"));
		int pg = Integer.valueOf(request.getParameter("pg"));
		
		// data 전달을 위한 settings
		request.setAttribute("seq", seq);
		request.setAttribute("pg", pg);
		request.setAttribute("display", "/board/boardUpdateForm.jsp");
		
		return "/index.jsp";
	}

}
