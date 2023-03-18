package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SelectMain {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs; // return table
	
	
	// DB 변경 시, 변수 활용(환경 설정)
	private String driver = "oracle.jdbc.driver.OracleDriver"; // path 설정
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	// url = "api:sql:driver:@ip_address(localhost: user local pc):port_num:DB_name"
	private String userName = "C##JAVA";
	private String passWord = "1234";
	
	
	// Driver Loading
	public SelectMain() {
		try {
			Class.forName(driver);
			System.out.println("Driver Loading Success!");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	} // Default Constructor


	// Connection
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, userName, passWord);
			System.out.println("conn Success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // getConnection()
	
	
	public void selectArticle() {
		this.getConnection(); // Connection
				
		String sql = "SELECT * FROM DBTEST";
		
		try {
//			pstmt = conn.prepareStatement("INSERT INTO DBTEST VALUES('" + name + "', " +  age + ", " +  height + ", " + "SYSDATE)");
			// 보안에 취약 -> ?에 set을 하는 방식으로 수정
			pstmt = conn.prepareStatement(sql);// pstmt: SQL 구문을 인식해줄 수 있는 가이드 역할
			rs = pstmt.executeQuery(); // return ResultSet(SQL: table)


			// ResultSet의 경우, length or size같은 메서드 X
			// 해당 위치에 record가 있는지 확인하고 있으면 true 반환
			// rs.next(): 위치를 맞추고, record가 있으면 true 없으면 false 반환 -> 다음 레코드로 이동
			while(rs.next()) {
				System.out.print(rs.getString("name") + "\t"
							   + rs.getInt("age") + "\t"
							   + rs.getDouble("Height") + "\t"
							   + rs.getString("logtime") + "\n");
			}
//			rs.getString(1);
//			rs.getInt(2);
//			rs.getDouble(3);
//			rs.getDate(4); // +1: 다음 날 반환 가능
//			rs.getString("logtime"); // +1: 문자 결합(다음 날로 계산 X)
			// col_location으로 불러올 경우, 어떤 col인지 알 수 없는 문제가 있음
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // error 발생 여부 상관없이 반드시 실행
			try {
				if(rs!=null) {rs.close();} // Avoid Overload
				if(pstmt!=null) {pstmt.close();} // is not null = 제대로 실행되었다면
				if(conn!=null) {conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // finally
	} // selectArticle()


	
	public static void main(String[] args) {
		SelectMain sm = new SelectMain();
		sm.selectArticle();
	}
}
