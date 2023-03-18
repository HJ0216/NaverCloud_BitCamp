package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertMain {
	private Connection conn;
	private PreparedStatement pstmt;
	
	// DB 변경 시, 변수 활용(환경 설정)
	private String driver = "oracle.jdbc.driver.OracleDriver"; // path 설정
	// Class.forName("Full Query Name 기재: pkg_name.class_name"); .class 생략

	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	// "API:sql_:driver:@ip_address(localhost: user local pc):port_num:DB_name"

	private String userName = "C##JAVA";
	private String passWord = "1234";

	
	// 1. DB 외부에서 입력된 Data를 저장하기위해 Driver Loading
	// 2. Driver Loading을 위해 Java project 별로 lib 폴더에 jar 파일 저장 후 start to build path
	// 3. jar 파일 안 OracleDriver 불러오기
	public InsertMain() {
		try {
			Class.forName(driver);
			// driver load에 필요한 OracleDriver.class -> class인지 interface인지 알 수 없음
			// Class라는 Meta class를 활용하여 interface, class를 동일하게 class로 취급하여 JVM에 전달
			System.out.println("Driver Loading Success!");
		} catch(ClassNotFoundException e) {
			// Class에 넘겨줄 class file이 NotFound일 경우를 대비
			e.printStackTrace();
		}
	} // Default Constructor

	
	// 생성자에서 driver 설치만 하고 접속까지 하지 않은 이유:
	// 생성자 호출은 new를 통해서 한 번 밖에 호출되지 않으므로
	// 내부에서 접속하지 않고 따로 method 생성


	
	// Connection
	public void getConnection() {
//		conn = new Connection(); // Connection: interface - new를 통한 객체 생성 X
		try {
			conn = DriverManager.getConnection(url, userName, passWord);
			System.out.println("conn Success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertArticle() {
		this.getConnection(); // 접속
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the name: ");
		String name = scan.next();
		System.out.print("Enter the age: ");
		int age = scan.nextInt();
		System.out.print("Enter the height: ");
		double height = scan.nextDouble();	
				
		
		String sql = "INSERT INTO DBTEST VALUES(?,?,?,SYSDATE)";
		
		
		try {
//			pstmt = conn.prepareStatement("INSERT INTO DBTEST VALUES('" + name + "', " +  age + ", " +  height + ", " + "SYSDATE)");
			// 보안에 취약
			// ?에 set을 하는 방식으로 수정

			pstmt = conn.prepareStatement(sql);
			// Connection interface 내부에 PreparedStatement()
			// pstmt: SQL 구문을 인식하는 Method
			
			// setString(위치, 인자)
			// 위치: oracle: 1부터, java: 0부터
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setDouble(3, height);
			
			
			int num = pstmt.executeUpdate();
			// n개의 행이 만들어졌습니다. -> 생성 개수 return
			System.out.println(num + "개의 행이 만들어졌습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally { // error 발생 여부 상관없이 반드시 실행
			try {
				if(pstmt!=null) {pstmt.close();} // !=null, 제대로 실행되었더라면
				if(conn!=null) {conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // finally
	}
	
	public static void main(String[] args) {
		InsertMain im = new InsertMain();
		im.insertArticle();
		// sql developer와 달리 insert가 자동 커밋되므로 바로 확인 가능
		
		
	}
	
}
