package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import member.bean.MemberDTO;


// servlet 과부하 방지를 위해 MemberDAO 생성
public class MemberDAO {
	// MemberDAO 역할: 전달받은 data를 java를 통해서 DB와 연결
	// 1. /lib: .jar 넣기 (/lib이 기존에 만들어져 있으므로 add to build path 필요 X)
	// 2. singleton 방식: 매번 새로운 객체 생성이 아닌 static처럼 memey allocation 후 reuse
	// 3. 저장 시마다 re-compile되므로 변경된 MemberDAO 확인 필요 시, tomcat server 재시작

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// context.xml을 통한 driver loading
	// context.xml 접근을 위한 DataSource 선언
	private DataSource ds;
	
	
	private String nameDB="";
	public String getNameDB() {return nameDB;}


	// DB 접근 설정: 변수를 통한 환경 설정
	// private String driver = "oracle.jdbc.driver.OracleDriver";
	// Class.forName("Full Query Name 기재: pkg_name.class_name"); .class 생략

	// private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	// "API:sql_:driver:@ip_address(localhost: user local pc):port_num:DB_name"

	// private String userName = "C##JAVA";
	// private String passWord = "1234";


	private static MemberDAO memberDAO = new MemberDAO();
	// static object
	// new 연산자를 통해서 객체를 static으로 생성하여 memory reuse
	
	public static MemberDAO getInstance() {return memberDAO;}
	// getInstance()를 통해서 static obj인 memberDAO의 주소를 return해서 기존에 생성된 obj memberDAO로 연결되도록 함
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
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
			// datapool에서 source를 받음
			// tomcat을 이용 할 경우에만 java:comp/env 사용
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		// try {
		//		Class.forName(driver);
		// 		driver load에 필요한 OracleDriver.class -> class인지 interface인지 알 수 없음
		// 		Class라는 Meta class를 활용하여 interface, class를 동일하게 class로 취급하여 JVM에 전달
		// } catch(ClassNotFoundException e) {
		//	e.printStackTrace();
		// }

		} // Default Constructor
	
	
	// Connection
	// public void getConnection() {
	// try {
	//		conn = DriverManager.getConnection(url, userName, passWord);
	//		conn = new Connection(); // Connection: interface - new를 통한 객체 생성 X
	//	} catch (SQLException e) {
	//		e.printStackTrace();
	//	}
	// }

	
	
	public int memberWrite(MemberDTO memberDTO) {
		int row = 0;
		
		String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		// 보안을 위해서 value에 들어갈 col_name을 ?로 작성
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			// ?에 대입
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getId());
			pstmt.setString(3, memberDTO.getPwd());
			pstmt.setString(4, memberDTO.getGender());
			pstmt.setString(5, memberDTO.getEmail1());
			pstmt.setString(6, memberDTO.getEmail2());
			pstmt.setString(7, memberDTO.getTel1());
			pstmt.setString(8, memberDTO.getTel2());
			pstmt.setString(9, memberDTO.getTel3());
			pstmt.setString(10, memberDTO.getZipcode());
			pstmt.setString(11, memberDTO.getAddr1());
			pstmt.setString(12, memberDTO.getAddr2());
			
			// execute
			row = pstmt.executeUpdate();
			// 실행 시, 몇 행이 삽입되었는지 개수 return
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt);
			
		}
				
		return row;
	}
	
	
	public int loginTry(MemberDTO memberDTO) {

		String sql = "SELECT NAME, ID, PWD FROM MEMBER WHERE ID=? AND PWD=?";
		// SQL에서 SELECT한 COL_NAME만 RS로 받을 수 있음
		
		
		String idDB="";
		String pwdDB="";
//		String nameDB="";
		// 전역변수 설정을 통한 getter 활용 -> 외부 class 접근 허용
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPwd());
			
			rs = pstmt.executeQuery(); // return table
			// sql 구문에서 setting한 table return
			
			while(rs.next()) {
				idDB =  rs.getString("ID");
				pwdDB =  rs.getString("PWD");
				nameDB =  rs.getString("NAME");	
			} // while()
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt, rs);
		}

		if(idDB.equals(memberDTO.getId()) && pwdDB.equals(memberDTO.getPwd())) {
			return 1;
		} else {return 0;}
		
		// java로 처리할 때랑 sql문으로 처리할 때랑 차이 없음
		
	} // loginTry
	
	
	
	// for LoginServlet2.java
	public String memberLogin(String id, String pwd) {
		String name = null;
		
		String sql = "SELECT NAME FROM MEMBER WHERE ID=? AND PWD=?";
		try {
			
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery(); // return ResultSet
			
			// id, pwd가 일치하는 data 1개뿐 -> next가 있을 경우(true일 경우), 일치하는 데이테 존재
			if(rs.next()) {
				name = rs.getString("name");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt, rs);
		}
		
		return name;
	} // memberLogin


	
	public MemberDTO memberCall(String id) {
//		this.getConnection();
		
		String sql = "SELECT * FROM MEMBER WHERE ID=?";
		// DB에서 조회하는 COL만 memberDTO에 set value 할 수 있음
		
		MemberDTO memberDTO = null;
		
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			// java code 내에서 sql 구문을 처리하기 위한 preparedStatement 선언
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			// sql 구문에서 select한 table return
			
			
			while(rs.next()) {
				memberDTO = new MemberDTO();
				// rs.next() = true일 때(조회되는 데이터가 있을 때), memberDTO를 생성해서 setting 하기
				// table이 return되지 않았을 경우에는 initialization한 null return
				
				memberDTO.setName(rs.getString("NAME"));
				memberDTO.setId(rs.getString("ID"));
				memberDTO.setPwd(rs.getString("PWD"));
				memberDTO.setGender(rs.getString("GENDER"));
				memberDTO.setEmail1(rs.getString("EMAIL1"));
				memberDTO.setEmail2(rs.getString("EMAIL2"));
				memberDTO.setTel1(rs.getString("TEL1"));
				memberDTO.setTel2(rs.getString("TEL2"));
				memberDTO.setTel3(rs.getString("TEL3"));
				memberDTO.setZipcode(rs.getString("ZIPCODE"));
				memberDTO.setAddr1(rs.getString("ADDR1"));
				memberDTO.setAddr2(rs.getString("ADDR2"));
			} // while()
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt, rs);
		}
		
		return memberDTO;
		// return 값은 1개만 가능하므로 여러개의 값을 받을 때는 memberDTO or map<key, value> 활용
	}

	
	public int memberUpdate(MemberDTO memberDTO) {
		int rowUpdate = 0;
		
//		this.getConnection();
		String sql = "UPDATE MEMBER SET NAME=?,"
									 + "PWD=?,"
									 + "GENDER=?,"
									 + "EMAIL1=?,"
									 + "EMAIL2=?,"
									 + "TEL1=?,"
									 + "TEL2=?,"
									 + "TEL3=?,"
									 + "ZIPCODE=?,"
									 + "ADDR1=?,"
									 + "ADDR2=?,"
									 + "LOGTIME=SYSDATE "
									 // sysdate와 where 사이 띄어쓰기 필요, 열 바꿀 때 유의
									 + "WHERE ID=?";
		// 보안을 위해서 value에 들어갈 col_name을 ?로 작성
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			// ?에 대입
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getPwd());
			pstmt.setString(3, memberDTO.getGender());
			pstmt.setString(4, memberDTO.getEmail1());
			pstmt.setString(5, memberDTO.getEmail2());
			pstmt.setString(6, memberDTO.getTel1());
			pstmt.setString(7, memberDTO.getTel2());
			pstmt.setString(8, memberDTO.getTel3());
			pstmt.setString(9, memberDTO.getZipcode());
			pstmt.setString(10, memberDTO.getAddr1());
			pstmt.setString(11, memberDTO.getAddr2());
			pstmt.setString(12, memberDTO.getId());
			
			// execute
			rowUpdate = pstmt.executeUpdate(); // 실행 시, 몇 행이 갱신되었는지 개수 return
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt);
			
		}
				
		return rowUpdate;
		
	} // memberUpdate
	
	
	public String memberDelTry(String id) {
		String pwd = null;
		
		String sql = "SELECT PWD FROM MEMBER WHERE ID=?";
//		getConnection(); // 접속
		try {
			
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); // return ResultSet
			
			if(rs.next()) {
				pwd = rs.getString("PWD");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt, rs);
		}
		
		return pwd;
	} // memberDelTry
	
	
	public int memberDelete(String id) {
		int rowDelete = 0;
		
		String sql = "DELETE FROM MEMBER WHERE ID=?";
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rowDelete = pstmt.executeUpdate();
			System.out.println(rowDelete);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt);
		}
		
		return rowDelete;
	} // memberDelete
	

	public boolean isExistPwd(String id, String pwd) {
		boolean exist = false;
		
		String sql = "SELECT * FROM MEMBER WHERE ID=? AND PWD=?";
//		getConnection(); // 접속
		
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {exist=true;}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt, rs);
		}

		return exist;
	}
	
	
	public int memberDeleteT(String id) {
		int rowDelete = 0;
	
		String sql = "DELETE MEMBER WHERE ID=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rowDelete = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt);
		}
		
		return rowDelete;
		
	} // memberDelete

	
	public boolean isExistId(String id) {
		boolean exist = false;
		
		String sql = "SELECT * FROM MEMBER WHERE ID=?";
//		getConnection(); // 접속
		
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {exist=true;}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt, rs);
		}
		return exist;
	}
	
	
}
