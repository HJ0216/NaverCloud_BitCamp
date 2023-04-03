package board.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.dao.BoardDAO;

// WriteService.java 참조
public class BoardReWriteService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// 1. Data
		String seq = request.getParameter("seq");
		// int seq = Integer.parseInt(request.getParameter("seq"));
		// Map Generics에 따라서 String 처리해야 하므로 integer로 형변환 X
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		
		// 2. DB
		Map<String, String> map = new HashMap<>();
		map.put("seq", seq);
		map.put("subject", subject);
		map.put("content", content);
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.boardUpdate(map);
		
		return "/board/boardReWrite.jsp";
	}

}
