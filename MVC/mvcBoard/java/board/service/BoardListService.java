package board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.dao.BoardDAO;

public class BoardListService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// 1. DATA
		String id = "11";
		// Httpsession session = request.getSession();
		// String memId = (String)session.getAttribute("memId");
		

		// 2. DB
		int pg = Integer.valueOf(request.getParameter("pg"));
		int endNum = pg*5;
		int startNum = endNum-4;
		
		Map<String, Integer> map = new HashMap<>();
		map.put("startNum", startNum); // key, value
		map.put("endNum", endNum);

		BoardDAO boardDAO = BoardDAO.getInstance();
		List<BoardDTO> listT = boardDAO.boardListT(map);
		// pg당 5개의 글만 return
		

		// 페이징 처리(Using Class)
		// 총 글수, 총 페이지수
		int totalA = boardDAO.getTotalA();
		int totalP = (int) Math.ceil(totalA/5.0);
		
		BoardPaging boardPaging = new BoardPaging();
		boardPaging.setCurrentPage(pg);
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);

		boardPaging.makePagingHTML(); // HTML code create
		
				
		// 3. Response
		// request.setAttribute("memId", memId);
		request.setAttribute("listT", listT);
		// boardList로 넘어갈 때, boardDTO가 담긴 list 전달
		request.setAttribute("pg", pg);
		
		request.setAttribute("boardPaging", boardPaging);
		request.setAttribute("totalP", totalP);
		
		return "/board/boardListT.jsp";	
		
	}

}
