package board.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import board.bean.BoardDTO;


@RestController
// return 타입이 객체일 경우, json으로 변환해서 넘겨줌 > WebBrowser로 바로 뿌려줌
public class BoardController {
	public BoardController() {
		System.out.println("BoardController Default Controller");
	}
	
	@GetMapping(value="/board/hello")
	// mainClass 안에서 다른 controller더라도 url이 겹치면 error 발생
	// namespace(/board) 활용
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
		boardDTO.setSubject("검정고무신 하이");
		boardDTO.setContent("주인공 기영이의 생활");
		boardDTO.setLogtime(new Date());

		list.add(boardDTO);
		
		return list;
		
	}

}



/*
@RestController
JSP 같은 뷰를 별도로 만들지 않는 대신에 컨트롤러 메소드가 리턴하는 데이터 자체를 클라이언트로 보낸다.
클라이언트에 전달되는 데이터는 문자열, DTO, 컬렉션(list, arrayList 등) 형태의 자바 객체인데, 자바 객체가 전달되는 경우에는 자동으로 JSON으로 변환하여 처리하게 된다.

*.jsp을 로드하고자 할 경우, @RestController가 아닌 @Controller를 사용해서 String으로 return을 받아야 함
*/