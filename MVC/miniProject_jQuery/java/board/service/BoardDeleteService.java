package board.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.dao.BoardDAO;

// BoardReplyService.java 참조
public class BoardDeleteService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// 1. Data
		// From boardViewT.jsp + boardView.js
		int pseq = Integer.parseInt(request.getParameter("seq"));
		// 답글이 달리는 원글번호를 seq에서 pseq로 재 명칭
		String pg = request.getParameter("pg");

		// Session
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("memId");
		String name = (String) session.getAttribute("memName");
		String email = (String) session.getAttribute("memEmail");
		
		Map<String, String> map = new HashMap<>();
		map.put("PSEQ", pseq+""); // 원글번호
		// Map<String, String> > pseq를 String화: "" or toString()
		// or Map<String, Object>로 Generics 변경
		map.put("ID", id);
		map.put("NAME", name);
		map.put("EMAIL", email);
		

		// 2. DB
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.boardDelete(map);

		return "/board/boardDelete.jsp";
	}

}
