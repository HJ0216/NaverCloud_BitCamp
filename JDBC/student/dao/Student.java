package student.dao;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Student {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	Scanner scan = new Scanner(System.in);
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userName = "C##JAVA";
	private String passWord = "1234";

	
	// Driver Setting
	public Student() {
		try {
			Class.forName(driver);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
//		this.getConnection();
		// 생성자와 함께 선언 시, conn.close(); 시 접속 종료 Exception 발생
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
		this.getConnection();
		
		Student std = new Student();		

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
//					try {
//						if(pstmt!=null) {pstmt.close();}
//						if(conn!=null) {conn.close();}
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
				// 종료를 입력해야만 close()되는 비효율적인 문제 발생
				break;
			}
			
			
			if(num==1) {
				std.insertArticle();
			
			} else if(num==2) {
				std.selectArticle();
			
			} else if(num==3){
				std.deleteArticle();
			
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			
			}
		
		} // while
		
	} // menu
	
	
	public void insertArticle() {

		while(true) {
			this.getConnection();
			
			System.out.println("****************");
			System.out.println("  1. 학생");
			System.out.println("  2. 교수");
			System.out.println("  3. 관리자");
			System.out.println("  4. 이전메뉴");
			System.out.println("****************");
			System.out.print("  번호선택 :");
			int num = scan.nextInt();
			
			String name;
			String value;

			System.out.print("이름 입력: ");
			name = scan.next();

			if(num==1) {
				System.out.print("학번 입력: ");
				value = scan.next();
				
				String sql = "INSERT INTO STUDENT2 VALUES(?,?,?)";

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, name);
					pstmt.setString(2, value);
					pstmt.setInt(3, num);
					
					int exe_num = pstmt.executeUpdate(); // n개의 행이 만들어졌습니다. -> 생성 개수 return
					System.out.println(exe_num + "개의 행이 만들어졌습니다.");
					
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

			} else if(num==2) {
				System.out.print("과목 입력: ");
				value = scan.next();
				
				String sql = "INSERT INTO STUDENT2 VALUES(?,?,?)";

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, name);
					pstmt.setString(2, value);
					pstmt.setInt(3, num);
					
					int exe_num = pstmt.executeUpdate();
					System.out.println(exe_num + "개의 행이 만들어졌습니다.");
					
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
				
			} else if(num==3) {
				System.out.print("부서 입력: ");
				value = scan.next();

				String sql = "INSERT INTO STUDENT2 VALUES(?,?,?)";

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, name);
					pstmt.setString(2, value);
					pstmt.setInt(3, num);
					
					int exe_num = pstmt.executeUpdate();
					System.out.println(exe_num + "개의 행이 만들어졌습니다.");
					
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
				
			} else if(num==4) {
				break;
			} else {
				System.out.println("잘못된 번호 입력");
			} // else
			
		} // while
		
	} // insertArticle
	  // 입력이 반복되는 부분은 if문 밖에 작성하기
	
	
	
	public void selectArticle() {
		int num;
		String search_name=null;
		
		while(true) {
			this.getConnection(); // 접속
			
			System.out.println("****************");
			System.out.println("  1. 이름 검색");
			System.out.println("  2. 전체 검색");
			System.out.println("  3. 이전 메뉴");
			System.out.println("****************");
			System.out.println("  번호 입력: ");
			num = scan.nextInt();

			if(num==3) {break;}
			if(num==1) {
				System.out.println("검색할 이름: ");
				search_name = scan.next();
			}
			
			String sql=null;
			if(num==1) {sql = "SELECT * FROM STUDENT2 WHERE NAME LIKE ?";}
			if(num==2) {sql = "SELECT * FROM STUDENT2";}
			
			try {
				pstmt = conn.prepareStatement(sql);
				if(num==1) {pstmt.setString(1, "%" + search_name + "%");}
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					System.out.println("이름: " + rs.getString("name") + "\t");
					
					if(rs.getInt("code")==1) {System.out.println("학번: " + rs.getString("value"));}
					else if(rs.getInt("code")==2) {System.out.println("과목: " + rs.getString("value"));}
					else if(rs.getInt("code")==3) {System.out.println("부서: " + rs.getString("value"));}
				} // while: return table

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs!=null) {rs.close();}
					if(pstmt!=null) {pstmt.close();}
					if(conn!=null) {conn.close();}
				} catch(SQLException e) {
					e.printStackTrace();
				}
			} // finally
			
		} // while
		
	} // selectArticle()		
		


	public void deleteArticle() {
		this.getConnection(); // 접속
		
		System.out.print("검색할 이름 입력: ");
		String del_name = scan.next();
		
		String sql = "DELETE STUDENT2 WHERE NAME = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, del_name);
			
			int num_update = pstmt.executeUpdate();
			System.out.println(num_update + "개의 행이 삭제되었습니다.");

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
		
	} // deleteArticle();
	
	
	public static void main(String[] args) {
		Student std = new Student();		
		std.menu();
	}
	
}
