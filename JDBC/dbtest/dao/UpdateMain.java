package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateMain {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userName = "C##JAVA";
	private String passWord = "1234";
	
	public UpdateMain() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	} // default Constructor;
	
	
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, userName, passWord);
			System.out.println("conn Success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // getConnection();
	
	
	
	public void updateArticle() {
		this.getConnection();
		
		Scanner scan = new Scanner(System.in);
		System.out.print("검색할 이름 입력: ");
		String search_name = scan.next();
		
		String sql = "UPDATE DBTEST SET AGE=AGE+1, HEIGHT=HEIGHT+1 WHERE NAME LIKE ?";
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + search_name + "%");
			
			int num_update = pstmt.executeUpdate();
			System.out.println(num_update + "개의 행이 업데이트되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		UpdateMain um = new UpdateMain();
		um.updateArticle();
	}
}



/*
검색할 이름 입력 : 홍 -> %홍%
홍이 들어간 레코드의 나이와 키를 1씩 증가
*/
