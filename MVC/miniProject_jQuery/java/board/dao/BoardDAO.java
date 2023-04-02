package board.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import board.bean.BoardDTO;


public class BoardDAO {
	private SqlSessionFactory sqlSessionFactory;
	// SQL Session을 만들기 위해 SQL Session Factory 선언


	private static BoardDAO boardDAO = new BoardDAO();
	public static BoardDAO getInstance() {return boardDAO;}

	
	public BoardDAO() {
		InputStream inputStream;

		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml"); // 바이트 단위 읽어오기 
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} // Default Constructor
	

	public int boardWrite(BoardDTO boardDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int row = sqlSession.insert("boardSQL.boardWrite", boardDTO);
		sqlSession.commit();
		sqlSession.close();

		return row;
	}
	

	public void boardWriteT(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("boardSQL.boardWriteT", map);
		sqlSession.commit();
		sqlSession.close();
		
	} // boardWriteT
	

	public List<BoardDTO> boardList(Map<String, Integer> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<BoardDTO> list = sqlSession.selectList("boardSQL.boardList", map);
		sqlSession.close();
		return list;

	} // boardList
	
	
	public List<BoardDTO> boardListT(Map<String, Integer> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<BoardDTO> list = sqlSession.selectList("boardSQL.boardListT", map);
		sqlSession.close();
		return list;

	} // boardListT

	
	public int getTotalA() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<BoardDTO> list = sqlSession.selectList("boardSQL.getTotalA");
		sqlSession.close();
		
		int list_size = list.size();
		
		return list_size;
		
		/* SQL 집계함수 사용 시, resultType: int 가능
		int totalB =  sqlSession.selectOne("boardSQL.getTotalB");
		*/

	}
	
	
	public BoardDTO boardCall(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BoardDTO boardDTO = sqlSession.selectOne("boardSQL.boardCall", seq);
		sqlSession.close();
		
		return boardDTO;
		
	}


	public void boardReply(Map<String, String> map) {
		// 답글: map, 원글: boardDTO
		
		// 원글에 대한 정보 추가(원글번호(PSEQ) 활용)
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BoardDTO boardDTO = sqlSession.selectOne("boardSQL.boardCall", Integer.parseInt(map.get("PSEQ")));
		// map.get("pseq"): String -> Integer.parseInt로 변환
		
		// UPDATE STEP
		// UPDATE BOARD SET STEP=STEP+1 WHERE REF=원글_REF AND SETP>원글_STEP
		sqlSession.update("boardSQL.boardReply1", boardDTO);
		
		// BOARD INSERT
		// boardReply에 전달된 map에는 이미 replyForm으로부터
		// id, name, email, subject, content, pseq가 담겨져 있는 상태
		
		// insert를 위해서 추가적인 작업을 boardReply에서 진행
		// 답글 REF = 원글 REF
		// 답글 LEV = 원글 LEV + 1
		// 답글 STEP = 원글 STEP + 1
		map.put("REF", boardDTO.getRef()+""); // Generics 제약에 따른 String화
		map.put("LEV", boardDTO.getLev()+1+"");
		map.put("STEP", boardDTO.getStep()+1+"");
		
		sqlSession.insert("boardSQL.boardReply2", map);
		
		// REPLY UPDATE
		// UPDATE BOARD SET REPLY=REPLY+1 WHERE SEQ=원글_SEQ
		sqlSession.update("boardSQL.boardReply3", boardDTO.getSeq());
		
		sqlSession.commit(); // 수동 commit
		sqlSession.close();
		
	}


	public void boardUpdate(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("boardSQL.boardUpdate", map);
		sqlSession.commit();
		sqlSession.close();
		
	}


	public void boardDelete(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BoardDTO boardDTO = sqlSession.selectOne("boardSQL.boardCall", Integer.parseInt(map.get("PSEQ")));
		// map.get("pseq"): String -> Integer.parseInt로 변환
		
		// REPLY UPDATE
		// UPDATE BOARD SET REPLY=REPLY+1 WHERE SEQ=원글_SEQ
		sqlSession.update("boardSQL.boardDelete1", boardDTO.getSeq());

		// UPDATE BOARD SET SUBJECT=('[원글이 삭제된 답글] ' || SUBJECT) WHERE PSEQ=원글_SEQ;
		sqlSession.update("boardSQL.boardDelete2", boardDTO.getSeq());

		// DELETE FROM BOARD WHERE SEQ=원글_SEQ;		
		sqlSession.delete("boardSQL.boardDelete3", boardDTO.getSeq());
		
		sqlSession.commit();
		sqlSession.close();

	}
	
}
