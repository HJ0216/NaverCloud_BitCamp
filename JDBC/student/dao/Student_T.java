package student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Student_T {
	Scanner scan = new Scanner(System.in);
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userName = "C##JAVA";
	private String passWord = "1234";

	
	// Default Constructor: Driver install
	public Student_T() {
		try {
			Class.forName(driver);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	// Connection
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, userName, passWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void menu() {
		Student_T student = new Student_T();		

		while(true) {
			System.out.println();
			System.out.println("****************");
			System.out.println("   관리");
			System.out.println("****************");
			System.out.println("  1. 입력");
			System.out.println("  2. 검색");
			System.out.println("  3. 삭제");
			System.out.println("  4. 종료");
			System.out.println("****************");
			System.out.print("  번호선택 :");
			int num = scan.nextInt();
			
			if(num==4) {
				System.out.println("프로그램 종료합니다.");
				break;
			}
			
			if(num==1) {
				student.insertArticle();
			
			} else if(num==2) {
				student.selectArticle();
			
			} else if(num==3){
				student.deleteArticle();
			
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			
			}

			
		} // while

	} // menu

	
	public void insertArticle() {
		this.getConnection(); // DB Connect
		
		int num;
		
		while(true) {
			System.out.println("****************");
			System.out.println("  1. 학생");
			System.out.println("  2. 교수");
			System.out.println("  3. 관리자");
			System.out.println("  4. 이전메뉴");
			System.out.println("****************");
			System.out.print("  번호선택 :");
			num = scan.nextInt();
			
			if(num==4) break;
			
			String name, value=null;
			System.out.println();

			
			// 반복되는 구문은 공통 처리 진행
			System.out.print("  이름 입력 :  ");
			name = scan.next();

			if(num==1) {
				System.out.print("  학번 입력 :  ");

			} else if(num==2) {
				System.out.print("  과목 입력 :  ");
				
			} else if(num==3) {
				System.out.print("  부서 입력 :  ");
				
			} value = scan.next();

			
			String sql = "INSERT INTO STUDENT2 VALUES(?,?,?)";

			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, name);
				pstmt.setString(2, value);
				pstmt.setInt(3, num);
				
				int row = pstmt.executeUpdate();
				System.out.println(row + "행이 업데이트되었습니다.");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally { // error 발생 여부 상관없이 반드시 실행
				try {
					if(pstmt!=null) {pstmt.close();}
					if(conn!=null) {conn.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // finally
		}
	} // insertArticle
	
	
	public void selectArticle() {
		getConnection(); // DB Connect

		String name=null;
		int num;
		
		while(true) {
			System.out.println("****************");
			System.out.println("  1. 이름 검색");
			System.out.println("  2. 전체 검색");
			System.out.println("  3. 이전 메뉴");
			System.out.println("****************");
			System.out.println("  번호 입력: ");
			num = scan.nextInt();
			
			if(num==3) break;
			
			if(num==1) {
				System.out.print("검색할 이름: ");
				name = scan.next();
			}
			
			
			String sql = null;

			if(num==1) {sql = "SELECT * FROM STUDENT2 WHERE NAME LIKE ?";}
			else if(num==2) {sql = "SELECT * FROM STUDENT2";}
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				if(num==1) pstmt.setString(1, "%" + name + "%");
				rs = pstmt.executeQuery(); // return table
				
				while(rs.next()) {
					System.out.print("이름: " + rs.getString("name") + "\t");
					
					if(rs.getInt("code")==1) {System.out.println("학번: " + rs.getString("value"));}
					else if(rs.getInt("code")==2) {System.out.println("과목: " + rs.getString("value"));}
					else if(rs.getInt("code")==3) {System.out.println("부서: " + rs.getString("value"));}
					
				} // while
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally { // error 발생 여부 상관없이 반드시 실행
				try {
					if(rs!=null) {rs.close();}
					if(pstmt!=null) {pstmt.close();}
					if(conn!=null) {conn.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // finally
			
		} // while
		
	} // selectArticle()
	
	
	public void deleteArticle() {
		getConnection();

		System.out.print("삭제할 이름: ");
		String name = scan.next();
		
		String sql = "DELETE STUDENT2 WHERE NAME=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			int del = pstmt.executeUpdate();
			System.out.println(del + "개의 행이 삭제되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // error 발생 여부 상관없이 반드시 실행
			try {
				if(pstmt!=null) {pstmt.close();} // is not null, 제대로 실행되었더라면
				if(conn!=null) {conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
		
	public static void main(String[] args) {
		Student_T student = new Student_T();
		student.menu();
		
		// class에서 직접 Student_T student = new Student_T();을 생성하여 객체를 만들 경우
		// instance 변수가 되어 static method에서 불러올 수 없음
		
	}
}
