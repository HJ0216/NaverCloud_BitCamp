package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // return type을 JSON으로 반환
public class BoardController {

	@Autowired
	private BoardService boardService;
	// BoardService boardService = new BoardServiceImpl();
	
	@GetMapping("/board/write") // 'value=' 생략 가능
	public String write() {
		boardService.write();
		return "게시판 등록 성공";
		// .jsp로 이동하지 않기 위해서 @ResponseBody를 선언
		// 또는 @Controller 대신 @RestController 선언(단, .jsp 파일과 연동하기 위해서는 @Controller + @ResponseBody 사용)
	}
	
	@GetMapping("/board/getBoardList")
	public List<BoardDTO> getBoardList(){
		return boardService.getBoardList();
	}
	
	
}
