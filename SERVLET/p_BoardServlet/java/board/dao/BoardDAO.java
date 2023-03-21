package board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import board.bean.BoardDTO;

public class BoardDAO {

	private static BoardDAO boardDAO = new BoardDAO();
	public static BoardDAO getInstance() {return boardDAO;}
	// Singleton 방식: static obj 생성 후 getInstance를 통해서 주소값 재참조
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// OverLoading
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}
			if(rs!=null) {rs.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	// DB 접근 설정1: 환경 설정
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userName = "C##JAVA";
	private String passWord = "1234";
	
	// DB 접근 설정2: 변수 선언
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	// 생성자 호출을 통한 드라이버 로딩
	public BoardDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} // Default Constructor
	
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, userName, passWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // Oracle DB와의 연결은 getConn()를 통해 필요할 때마다 하면 좋음


	public int boardWrite(BoardDTO boardDTO) {
		this.getConnection();
		// this 생략 가능
		
		int insert_num = 0;
		
		String sql = "INSERT INTO PBOARD VALUES(SEQ_PBOARD.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardDTO.getName());
			pstmt.setString(2, boardDTO.getEmail());
			pstmt.setString(3, boardDTO.getSubject());
			pstmt.setString(4, boardDTO.getContent());
			
			insert_num = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BoardDAO.close(conn, pstmt);
		}
		
		return insert_num;
		
	} // boardWrite
	

}
