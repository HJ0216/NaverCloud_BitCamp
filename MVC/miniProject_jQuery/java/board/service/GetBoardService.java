package board.service;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

// GetBoardListService.java 참조
public class GetBoardService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// 1. Data
		// From boardView.js
		int seq = Integer.parseInt(request.getParameter("seq"));
		// Integer.valueOf(request.getParameter("seq"));
		// 글 조회에 pg는 필요 없음
		// int pg = Integer.valueOf(request.getParameter("pg"));
				

		// 2. DB: 넘겨받은 seq에 해당하는 정보 조회
		BoardDAO boardDAO = BoardDAO.getInstance();
		BoardDTO boardDTO = boardDAO.boardCall(seq);

		// boardDTO
		JSONObject json = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

		if(boardDTO != null) {
			json.put("seq", boardDTO.getSeq());
			json.put("id", boardDTO.getId());
			json.put("name", boardDTO.getName());
			json.put("email", boardDTO.getEmail());
			json.put("subject", boardDTO.getSubject());
			json.put("content", boardDTO.getContent());
			json.put("ref", boardDTO.getRef());
			json.put("lev", boardDTO.getLev());
			json.put("step", boardDTO.getStep());
			json.put("pseq", boardDTO.getPseq());
			json.put("reply", boardDTO.getReply());
			json.put("hit", boardDTO.getHit());
			json.put("logtime", sdf.format(boardDTO.getLogtime()));
		} // if
		
		// Session(수정, 삭제 버튼 표시 활용)
		HttpSession session = request.getSession();
		String memId = (String) session.getAttribute("memId");

		// 3. Response: request.setAttribute()를 통해서 getBoardList.jsp로 데이터 전달
		request.setAttribute("json", json); // for aJax
		request.setAttribute("memId", memId);
		
		
		return "/board/getBoardList.jsp";
	}

}
