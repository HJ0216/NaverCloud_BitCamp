package guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import guestbook.bean.GuestbookDTO;


public class GuestbookDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	
	// lib: .jar 파일 저장(lib 자동 생성 시, Build to path 지정 X)

	
	// DB 접근 설정: 변수를 통한 환경 설정
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userName = "C##JAVA";
	private String passWord = "1234";


	private static GuestbookDAO guestbookDAO = new GuestbookDAO();
	// static object
	// new 연산자를 통해서 객체를 static으로 생성하여 memory reuse
	
	
	public static GuestbookDAO getInstance() {return guestbookDAO;}
	// getInstance()를 통해서 static obj인 memberDAO의 주소를 return해서
	// 기존에 할당된 memberDAO로 연결되도록 함
	// 새로운 생성이 아닌 주소 안내를 통한 memberDAO 사용
	
	
	// static, instance 의미 X
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
	
	
	// 생성자 호출(public memberDAO())을 통한 드라이버 로딩	
	public GuestbookDAO() {
		try {
			Class.forName(driver);
			// Class라는 Meta class를 활용하여 interface, class를 동일하게 class로 취급하여 JVM에 전달
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

	} // Default Constructor
	
	
	// Connection
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, userName, passWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 생성자로 driver loading을 하는 이유: 생성자 호출 시 한 번만 하면 됨
	
	
	public int guestbookWrite(GuestbookDTO guestbookDTO) {

		int row = 0;
		
		this.getConnection();		
		
		String sql = "INSERT INTO GUESTBOOK VALUES(SEQ_GUESTBOOK.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE)";
		// SQL문 작성 시, TABLE NAME 유의

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, guestbookDTO.getName());
			pstmt.setString(2, guestbookDTO.getEmail());
			pstmt.setString(3, guestbookDTO.getHomepage());
			pstmt.setString(4, guestbookDTO.getSubject());
			pstmt.setString(5, guestbookDTO.getContent());
			
			row = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			GuestbookDAO.close(conn, pstmt);
		}
		
		return row;
				
	} // guestbookWrite


	public GuestbookDTO guestbookSearch(int seq) {
		GuestbookDTO guestbookDTO = null;
		
		this.getConnection();
		
		String sql = "SELECT * FROM GUESTBOOK WHERE SEQ=?";
		// SQL Table_name 유의
		// SQL 구문을 활용한 형식 변환
		// SELECT SEQ, NAME, EMAIL, HOMEPAGE, SUBJECT, CONTENT, TO_CHAR(LOGTIME, 'YYYY.MM.DD') "LOGTIME"
		// FROM GUESTBOOK WHERE SEQ=?"
		// 문장이 길면 끊어주기
		
		// 
		
		try {
			pstmt = conn.prepareStatement(sql);
			// pstmt: java에 입력한 sql 구문을 oracleDB에 전달하기 위한 과정

			pstmt.setInt(1, seq);
			// DB의 seq는 number type이므로 pstmt에 설정해줘야하는 seq는 Int type이어야 함
			// 만일 java에서 String seq로 받았을 경우, Integer.parseInt(seq)로 형변환 필요
			
			rs = pstmt.executeQuery(); // return ResultSet

			// 검색 결과에 따라 return record가 있을수도 없을수도 있으므로 확인 후 data 반환받기
			if(rs.next()) {
				guestbookDTO = new GuestbookDTO();
				// rs의 값이 있을 경우에만 DTO 생성이 필요하므로 rs.next() 구문에 작성
				
				guestbookDTO.setSeq(rs.getInt("seq"));
				// setSeq(int seq): DB seq data type: Number(int), getString이 아닌 getInt로 받아오기
				guestbookDTO.setName(rs.getString("name"));
				// name: db col_name
				guestbookDTO.setLogtime(rs.getString("logtime"));
				guestbookDTO.setEmail(rs.getString("email"));
				guestbookDTO.setHomepage(rs.getString("homepage"));
				guestbookDTO.setSubject(rs.getString("subject"));
				guestbookDTO.setContent(rs.getString("content"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			GuestbookDAO.close(conn, pstmt, rs);
		}
		
		
		return guestbookDTO;
	} // guestbookSearch


	public ArrayList<GuestbookDTO> guestbookList(int startNum, int endNum) {
		ArrayList<GuestbookDTO> list = new ArrayList<GuestbookDTO>();
		

		String sql = "SELECT * "
				+ "FROM(SELECT ROWNUM RN, AA.*"
				+ "     FROM (SELECT SEQ, NAME, EMAIL, HOMEPAGE, SUBJECT, CONTENT, TO_CHAR(LOGTIME, 'YYYY.MM.DD') \"LOGTIME\""
				+ "           FROM GUESTBOOK ORDER BY SEQ DESC) AA)"
				+ "WHERE RN BETWEEN ? AND ?";
		
		
//		String sql = "SELECT SEQ, NAME, EMAIL, HOMEPAGE, SUBJECT, CONTENT,"
//				+ "TO_CHAR(LOGTIME, 'YYYY.MM.DD') \"LOGTIME\""
//				+ "FROM GUESTBOOK ORDER BY SEQ DESC";
		
		// ?가 없으므로 set 필요 X
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				GuestbookDTO guestbookDTO = new GuestbookDTO();
				// rs의 값이 있을 경우에만 DTO 생성이 필요하므로 rs.next() 구문에 작성
				guestbookDTO.setSeq(rs.getInt("seq"));
				// setSeq(int seq): DB seq data type: Number(int), getString이 아닌 getInt로 받아오기
				guestbookDTO.setName(rs.getString("name"));
				guestbookDTO.setLogtime(rs.getString("logtime"));
				guestbookDTO.setEmail(rs.getString("email"));
				guestbookDTO.setHomepage(rs.getString("homepage"));
				guestbookDTO.setSubject(rs.getString("subject"));
				guestbookDTO.setContent(rs.getString("content"));				
				list.add(guestbookDTO);
				
			} // while
		
		} catch (SQLException e) {
			e.printStackTrace();
			list=null;
			// ArrayList 이미 new를 통한 생성
			// try문에서 error가 발생 시, catch에 list=null로 설정하여
			// 생성된 ArrayList의 garbage value가 넘어가는 것을 방지
		} finally {
			GuestbookDAO.close(conn, pstmt, rs);
		}
		
		
		
		return list;
	}


	public int getTotalA() {
		getConnection();

		String sql = "SELECT COUNT(*) FROM GUESTBOOK";

		int totalA=0;		
		
		try {
			pstmt = conn.prepareStatement(sql);						
			rs = pstmt.executeQuery();
	
			rs.next();
			totalA = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			GuestbookDAO.close(conn, pstmt, rs);
		}
		
		
		return totalA;
	}

	
}
