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
import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class BoardDAO {
	private SqlSessionFactory sqlSessionFactory;
	// SQL Session을 만들기 위해 SQL Session Factory 선언


	private static BoardDAO boardDAO = new BoardDAO();
	public static BoardDAO getInstance() {return boardDAO;}

	
	public BoardDAO() {
		InputStream inputStream;

		try {
			inputStream = Resources.getResourceAsStream("/conf/mybatis-config.xml"); // 바이트 단위 읽어오기 
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

	}
	
	
	public BoardDTO boardCall(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BoardDTO boardDTO = sqlSession.selectOne("boardSQL.boardCall", seq);
		sqlSession.close();
		
		return boardDTO;
		
	}
	
	public List<BoardDTO> boardSearch(Map<String, String> searchMap) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<BoardDTO> list = null;
		
		if(searchMap.get("search_title").equals("작성자")) {
			list = sqlSession.selectList("boardSQL.boardSearch_name", searchMap);			
		} else {
			list = sqlSession.selectList("boardSQL.boardSearch_subject", searchMap);						
		}
		
		sqlSession.close();		
		
		return list;
	}
	
	
	

}

