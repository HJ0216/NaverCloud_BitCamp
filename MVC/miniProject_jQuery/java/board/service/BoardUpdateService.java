package board.service;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

// GetBoardService.java 참조
public class BoardUpdateService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// 1. DATA
		// From boardUpdateForm.jsp > boardUpdate.js
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		
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
		
		
		// 3. Response: request.setAttribute()를 통해서 getBoardUpdate.jsp로 데이터 전달
		request.setAttribute("json", json); // for aJax
		
		
		return "/board/getBoardUpdate.jsp";
	}

}
