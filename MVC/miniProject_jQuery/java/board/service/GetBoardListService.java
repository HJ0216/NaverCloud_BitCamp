package board.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject; // from 'json-simple-1.1.1.jar'

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.dao.BoardDAO;

public class GetBoardListService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// 1. Data
		int pg = Integer.parseInt(request.getParameter("pg"));
		// From boardListT.jsp ▶ boardList.js
		
		// 2. DB
		BoardDAO boardDAO = BoardDAO.getInstance();

		int endNum = pg*5;
		int startNum = endNum-4;
		
		Map<String, Integer> map = new HashMap<>();
		map.put("startNum", startNum); // key, value
		map.put("endNum", endNum);

		// pg당 5개의 글만 return
		List<BoardDTO> listT = boardDAO.boardListT(map);
		// listT에 담기는 BoardDTO는 최대 5개(pg 당 글 개수를 return 받는 함수)
		
		// From boardList.js: ajax에서 객체를 받을 수 있는 return type이 json이므로 List 객체를 json으로 변환시켜서 return해야 함
		// 1. 전체 리스트를 담아 줄 JSON 객체 {}
		// 2. list에 들어있던 BoardDTO를 담아줄 JSON 배열 []
		// 3. BordDTO JSON 객체 {}
		
		// Result
		// {[{'seq': 5, 'id': 'hong', 'name': 'gildong'},
		//	 {'seq': 4, 'id': 'ddochi', 'name': 'chichi'},
		//   {'seq': 2, 'id': 'dooli', 'name': 'lili'}]}
		
		
		// *페이징 처리
		// 1. 총 글수(totalA)
		int totalA = boardDAO.getTotalA();

		BoardPaging boardPaging = new BoardPaging();
		boardPaging.setCurrentPage(pg); // 현재 페이지
		boardPaging.setPageBlock(3); // 페이지 묶음
		boardPaging.setPageSize(5); // 페이지당 글 개수
		boardPaging.setTotalA(totalA); // 총 글수
		
		boardPaging.makePagingHTML();
		// 총 글수 및 페이지 수에 따른 1, 2, 3 이전 다음 출력

		System.out.println(boardPaging);
		/* BoardPaging(currentPage=1,
						 pageBlock=3,
						  pageSize=5,
						    totalA=57,
						pagingHTML=<span id='currentPaging' onclick='boardPaging(1)'>1</span>
							       <span id='paging' onclick='boardPaging(2)'>2</span>
							       <span id='paging' onclick='boardPaging(3)'>3</span>
							       <span id='paging' onclick='boardPaging(4)'>다음</span>)

		boardListT.jsp js boardPaging(pg) 실행

		객체 > JSON으로 변환
		{
			'currentPage' : 1,
			'pageBlock' : 3,
			'pageSize' : 5,
			'totalA' : 57,
			'pagingHTML' : <span id='currentPaging' onclick='boardPaging(1)'>1</span>
						   <span id='paging' onclick='boardPaging(2)'>2</span>
						   <span id='paging' onclick='boardPaging(3)'>3</span>
						   <span id='paging' onclick='boardPaging(4)'>다음</span>)
		}
		
		*/
		
		
		// Session
		HttpSession session = request.getSession();
		String memId = (String) session.getAttribute("memId");
		// session에 로그인 정보 유무를 판단하여 boardList.js에서 사용
		// 데이터 관련 처리는 java에서 진행

		JSONObject json = new JSONObject();
		// 1. 전체 리스트를 담아줄 JSON 객체 생성
		
		if(listT != null) {
			JSONArray array = new JSONArray();
			// 2. JSON 배열 생성
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			
			for(BoardDTO boardDTO : listT) { // 3. 개별 DTO를 담아줄 JSON 객체 생성
				JSONObject tmp = new JSONObject();
				tmp.put("seq", boardDTO.getSeq());
				tmp.put("id", boardDTO.getId());
				tmp.put("name", boardDTO.getName());
				tmp.put("email", boardDTO.getEmail());
				tmp.put("subject", boardDTO.getSubject());
				tmp.put("content", boardDTO.getContent());
				tmp.put("ref", boardDTO.getRef());
				tmp.put("lev", boardDTO.getLev());
				tmp.put("step", boardDTO.getStep());
				tmp.put("pseq", boardDTO.getPseq());
				tmp.put("reply", boardDTO.getReply());
				tmp.put("hit", boardDTO.getHit());
				tmp.put("logtime", sdf.format(boardDTO.getLogtime())); // sdf.formet return String;
				// 입력 가능한 JSON value 형식: string, number, object, array, boolean, null
				// 입력 불가능한 JSON value 형식: function, date, undefined
				// tmp.put("logtime", boardDTO.getLogtime()); > parseError occured
				
				array.add(tmp); // 4. 배열 구성요소가 완성되면 배열에 넣어주기
			} // for
			
			json.put("listT", array); // 5. JSON 배열을 JSON 객체에 넣어주기
		
		} // if
			
		
		json.put("pagingHTML", boardPaging.getPagingHTML()+"");
		// BoardPaging의 pagingHTML만(화면에 반환하는데 필요한 값만) > JSON으로 변환
		// getPagingHTML: StringBuffer
		// 입력 가능한 JSON value 형식: string, number, object, array, boolean, null
		// stringBuffer가 아닌 String으로 변환해준 후 JSON처리 가능

		
		// 3. Response
		request.setAttribute("json", json);
		// JSON: list, pagingHTML
		
		// request.setAttribute("pg", pg);
		// boardListT.jsp(▶boardList.js)에서 다루는 pg값이 있으므로 전달 필요 X
		
		request.setAttribute("memId", memId);		

		return "/board/getBoardList.jsp";
		// servlet과의 forward를 위해 반드시 jsp를 return해야 함
		
	}

}
