package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Service Bean 생성
public class BoardServiceImpl implements BoardService {	
	
	@Autowired
	private BoardDAO boardDAO;
	// JpaRepository를 통해서 interface 내부에서 구현체 생성 ▶ 객체 생성 ▶ @Autowirted 진행
	
	@Override
	public void write() {
		BoardDTO boardDTO = new BoardDTO();
		// Bean 객체 Default는 Standalone 타입이므로 새로운 객체 생성이 아닌 주소 참조 형태가 됨
		// new 연산자를 통해서 주소 참조 형태가 아닌 새로운 객체 형성(덮어쓰기 방지)
		boardDTO.setId("Monday");
		boardDTO.setName("화요일");
		boardDTO.setSubject("Wed");
		boardDTO.setContent("목열");
		// boardDTO.setLogtime(); 설정 필요 X ▶ 자동 생성
		
		boardDAO.save(boardDTO);
		// JpaRepository method: save() = SQL: insert

		
		boardDTO = new BoardDTO();
		boardDTO.setId("January");
		boardDTO.setName("2월");
		boardDTO.setSubject("Mar");
		boardDTO.setContent("사월");
		// boardDTO.setLogtime(); 설정 필요 X
		
		boardDAO.save(boardDTO);
		// JpaRepository method: save() = SQL: insert

	}

	
	@Override
	public List<BoardDTO> getBoardList() {
		return boardDAO.findAll();
		// JpaRepository에 있는 method 사용
	}

}
