package board.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import board.bean.BoardDTO;


@RestController
public class BoardController {
	public BoardController() {
		System.out.println("BoardController Default Controller");
	}
	
	@GetMapping(value="/board/hello")
	public String hello(String name) {
		return "안녕하세요, " + name + "님";
	}
	
	@GetMapping(value="/board/getBoard")
	public BoardDTO getBoard() {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setSeq(1);
		boardDTO.setName("허균");
		boardDTO.setSubject("홍길동전");
		boardDTO.setContent("의로운 의적");
		boardDTO.setLogtime(new Date());
		return boardDTO;
		
	}
	
	@GetMapping(value="/board/getBoardList")
	public List<BoardDTO> getBoardList() {
		
		List<BoardDTO> list = new ArrayList<>();
		
		BoardDTO boardDTO = new BoardDTO();
		
		boardDTO.setSeq(1);
		boardDTO.setName("허균");
		boardDTO.setSubject("홍길동전");
		boardDTO.setContent("의로운 의적");
		boardDTO.setLogtime(new Date());
		
		list.add(boardDTO);

		
		boardDTO = new BoardDTO();
		boardDTO.setSeq(2);
		boardDTO.setName("이우영");
		boardDTO.setSubject("검정고무신");
		boardDTO.setContent("주인공 기영이의 생활");
		boardDTO.setLogtime(new Date());

		list.add(boardDTO);
		
		return list;
		
	}

}
