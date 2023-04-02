package board.dao;

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

import board.bean.BoardDTO;
import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class BoardDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private DataSource ds;
	
	/*
	Server: Connection Pool
	Client에서 connection pool에 직접 접근할 수 없으며, DataSource를 통해서 접근

	Context context = new InitialContext();
	ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	lookup()을 통해서 ""라는 이름을 가진 connection을 가져와서 ds 변수에 저장
	DataSource는 Connection pool로부터 Connection을 받아오고, Client는 DS로부터 받은 Connection을 사용 후 반환
	(기존 생성자를 통해 Conn 연결한 것과 달리 소멸되지 않음) 
	*/
	

	// DB 접근 설정: 변수 지정
	// private String driver = "oracle.jdbc.driver.OracleDriver";
	// private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	// private String userName = "C##JAVA";
	//	private String passWord = "1234";


	private static BoardDAO boardDAO = new BoardDAO();
	
	public static BoardDAO getInstance() {return boardDAO;}
	
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// Overloading
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}
			if(rs!=null) {rs.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	// Connection
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
			// DataSource는 Connection pool로부터 Connection을 받아오고, Client는 DS로부터 받은 Connection을 사용 후 반환
			// tomcat을 이용 할 경우에만 java:comp/env 사용
		} catch (NamingException e) {
			e.printStackTrace();
		}
	} // Default Constructor
	
	
	
	public int boardWrite(BoardDTO boardDTO) {
		int row = 0;
		
		String sql = "INSERT INTO BOARD(SEQ, ID, NAME, EMAIL, SUBJECT, CONTENT, REF, LOGTIME) "
				+ "VALUES(SEQ_BOARD.NEXTVAL, ?, ?, ?, ?, ?, SEQ_BOARD.NEXTVAL, SYSDATE)";
		// 보안을 위해서 value에 들어갈 col_name을 ?로 작성
		// 생성한 TABLE에서 NOT NULL COLUMN인 대상들은 VALUE 필수
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			// ?에 대입
			pstmt.setString(1, boardDTO.getId());
			pstmt.setString(2, boardDTO.getName());
			pstmt.setString(3, boardDTO.getEmail());
			pstmt.setString(4, boardDTO.getSubject());
			pstmt.setString(5, boardDTO.getContent());
			
			// execute
			row = pstmt.executeUpdate();
			// 실행 시, 몇 행이 삽입되었는지 개수 return
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BoardDAO.close(conn, pstmt);
			
		}
				
		return row;
	}
	
	
	// For using Map
	public void boardWriteT(Map<String, String> map) {
		// insert return value 받지 않음

		// this.getConnection();
		String sql = "INSERT INTO BOARD(SEQ, ID, NAME, EMAIL, SUBJECT, CONTENT, REF, LOGTIME) VALUES(SEQ_BOARD.NEXTVAL, ?, ?, ?, ?, ?, SEQ_BOARD.NEXTVAL, SYSDATE)";
		// SQL 구문 작성 시, TABLE에 COL을 지정하지 않으면 모든 COL의 VALUE를 입력해줘야하는 문제 발생
		// 입력이 필요한 COL만 INSERT 후 나머지는 DB TABLE에 설정해놓은 DEFAULT값 이용
		// SQL 구문 개행 시, 띄어쓰기가 되지 않아서 구문이 실행되지 않는 경유 유의
		// SEQ_BOARD.NEXTVAL이 두 번 사용되지만 동시에 실행되므로 같은 값이 RETURN 됨
		// 또는 REF에 SEQ_BOARD.CURRVAL 사용 가능

		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, map.get("id"));
			pstmt.setString(2, map.get("name"));
			pstmt.setString(3, map.get("email"));
			pstmt.setString(4, map.get("subject"));
			pstmt.setString(5, map.get("content"));
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BoardDAO.close(conn, pstmt);
		}
		
	} // boardWriteT
	
	
	public List<BoardDTO> boardList(int startNum, int endNum) {

		List<BoardDTO> list = new ArrayList<>();
		// 다형성 활용

		String sql = "SELECT * "
				+ "FROM(SELECT ROWNUM RN, A.* "
				+ "FROM(SELECT * FROM BOARD ORDER BY REF DESC, STEP) A) "
				+ "WHERE RN BETWEEN ? AND ?";
		/*
		추후, 답글 기능을 활용하기 위해 ORDER BY 는 REF, STEP 활용 
		*/
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO boardDTO = new BoardDTO();
				// rs.next() = true일 때(조회되는 데이터가 있을 때), boardDTO를 생성해서 setting 하기
				// table이 return되지 않았을 경우에는 initialization한 null return
				
				boardDTO.setSeq(rs.getInt("SEQ"));
				boardDTO.setId(rs.getString("ID"));
				boardDTO.setName(rs.getString("NAME"));
				boardDTO.setEmail(rs.getString("EMAIL"));
				boardDTO.setSubject(rs.getString("SUBJECT"));
				boardDTO.setContent(rs.getString("CONTENT"));
				boardDTO.setRef(rs.getInt("REF"));
				boardDTO.setLev(rs.getInt("LEV"));
				boardDTO.setStep(rs.getInt("STEP"));
				boardDTO.setPseq(rs.getInt("PSEQ"));
				boardDTO.setReply(rs.getInt("REPLY"));
				boardDTO.setHit(rs.getInt("HIT"));
				boardDTO.setLogtime(rs.getDate("LOGTIME"));
				list.add(boardDTO);
				
			} // while()
			
		} catch (SQLException e) {
			e.printStackTrace();
			list=null;
			// List가 try 구문 전에 이미 생성되었기 때문에
			// try 부분에서 error 발생 시, local variable인 list에 garbage value가 return 될 수 있음
			// 그러므로 try 구문에서 error 발생 시, catch에서 list=null을 만들어서 null값을 return 시킴
		} finally {
			BoardDAO.close(conn, pstmt, rs);
		}
		
		return list;
				
	}
	
	
	public List<BoardDTO> boardListT(Map<String, Integer> map) {

		List<BoardDTO> list = new ArrayList<>();
		// 다형성 활용

		String sql = "SELECT * "
				+ "FROM(SELECT ROWNUM RN, A.* "
				+ "FROM(SELECT * FROM BOARD ORDER BY REF DESC, STEP) A) "
				+ "WHERE RN BETWEEN ? AND ?";
		/*
		추후, 답글 기능을 활용하기 위해 ORDER BY 는 REF, STEP 활용
		개행 시, SQL 구문 띄어쓰기 유의
		*/
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			// value 대신 key값을 넣어 value를 return 받음
			pstmt.setInt(1, map.get("startNum"));
			pstmt.setInt(2, map.get("endNum"));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO boardDTO = new BoardDTO();
				// rs.next() = true일 때(조회되는 데이터가 있을 때), boardDTO를 생성해서 setting 하기
				// table이 return되지 않았을 경우에는 initialization한 null return
				
				boardDTO.setSeq(rs.getInt("SEQ"));
				boardDTO.setId(rs.getString("ID"));
				boardDTO.setName(rs.getString("NAME"));
				boardDTO.setEmail(rs.getString("EMAIL"));
				boardDTO.setSubject(rs.getString("SUBJECT"));
				boardDTO.setContent(rs.getString("CONTENT"));
				boardDTO.setRef(rs.getInt("REF"));
				boardDTO.setLev(rs.getInt("LEV"));
				boardDTO.setStep(rs.getInt("STEP"));
				boardDTO.setPseq(rs.getInt("PSEQ"));
				boardDTO.setReply(rs.getInt("REPLY"));
				boardDTO.setHit(rs.getInt("HIT"));
				boardDTO.setLogtime(rs.getDate("LOGTIME"));
				list.add(boardDTO);
				
			} // while()
			
		} catch (SQLException e) {
			e.printStackTrace();
			list=null;
			// List가 try 구문 전에 이미 생성되었기 때문에
			// try 부분에서 error 발생 시, local variable인 list에 garbage value가 return 될 수 있음
			// 그러므로 try 구문에서 error 발생 시, catch에서 list=null을 만들어서 null값을 return 시킴
		} finally {
			BoardDAO.close(conn, pstmt, rs);
		}
		
		return list;
				
	}

	
	
	public int getTotalA() {
		String sql = "SELECT COUNT(*) FROM BOARD";

		int totalA=0;
		
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			totalA = rs.getInt(1);
			// It retrieves the value of the first column of the current row in the ResultSet object as an integer
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BoardDAO.close(conn, pstmt, rs);
		}
		
		return totalA;
	}
	
	
	public BoardDTO boardCall(int seq) {
		String sql = "SELECT * FROM BOARD WHERE SEQ=?";
		BoardDTO boardDTO = null;
		// 자바 변수 초기값: null
		// 자바스크립트 변수 초기값: ""
		
		try {
			conn = ds.getConnection();
	
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // seq: pk, 1개 record return, while 대신 if 사용 가능
				boardDTO = new BoardDTO();
				// rs.next() = true일 때(조회되는 데이터가 있을 때), memberDTO를 생성해서 setting 하기
				// table이 return되지 않았을 경우에는 initialization한 null return
				
				boardDTO.setSeq(rs.getInt("SEQ"));
				boardDTO.setId(rs.getString("ID"));
				boardDTO.setName(rs.getString("NAME"));
				boardDTO.setEmail(rs.getString("EMAIL"));
				boardDTO.setSubject(rs.getString("SUBJECT"));
				boardDTO.setContent(rs.getString("CONTENT"));
				boardDTO.setRef(rs.getInt("REF"));
				boardDTO.setLev(rs.getInt("LEV"));
				boardDTO.setStep(rs.getInt("STEP"));
				boardDTO.setPseq(rs.getInt("PSEQ"));
				boardDTO.setReply(rs.getInt("REPLY"));
				boardDTO.setHit(rs.getInt("HIT"));
				boardDTO.setLogtime(rs.getDate("LOGTIME"));
			} // while()
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt, rs);
		}
		
		return boardDTO;
		
	}
	
}

