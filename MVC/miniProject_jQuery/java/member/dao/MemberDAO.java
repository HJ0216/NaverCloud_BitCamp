package member.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.bean.MemberDTO;


public class MemberDAO {
	private static MemberDAO memberDAO = new MemberDAO();
	public static MemberDAO getInstance() {return memberDAO;}

	private SqlSessionFactory sqlSessionFactory;
	// SQL Session을 만들기 위해 SQL Session Factory 선언
		
	
	public MemberDAO() {
		// 환경설정 파일을 읽어오기 위해 IO Stream 사용
		// 1. byte: InputStream
		// 2. 문자: Reader
		
		Reader reader;
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml"); // 환경설정 파일 읽어오기
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		} 


//		InputStream inputStream;
//		try {
//			inputStream = Resources.getResourceAsStream("/conf/mybatis-config.xml"); // 바이트 단위 읽어오기 
//			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	} // Default Constructor
	
	
	public int memberWrite(MemberDTO memberDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int row = sqlSession.insert("memberSQL.memberWrite", memberDTO);
		sqlSession.commit();
		sqlSession.close();
		
		return row;
		
	} // memberWrite
	
	
	public MemberDTO loginTry(MemberDTO memberDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO_return = sqlSession.selectOne("memberSQL.loginTry", memberDTO);
		sqlSession.close();
		
		return memberDTO_return;
		
	} // loginTry
	
	
	// for Login.java
	public MemberDTO memberLogin(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO_return = sqlSession.selectOne("memberSQL.memberLogin", map);
		// SQL 구문에 변수를 1개밖에 전달하지 못하므로 DTO class or Map에 전달
		sqlSession.close();

		return memberDTO_return;
		
	} // memberLogin
	


	// checkId.jsp
	public boolean isExistId(String id) {
		boolean exist= false;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.isExistId", id);
		// selectOne는 boolean을 return하지 않으므로 MemberDTO로 return 받고 java code로 처리
		
		if(memberDTO!=null) {
			exist=true;
		} else {
			exist=false;
		}
		
		sqlSession.close();
		
		return exist;
		
	} // isExistId

	
	public MemberDTO memberCall(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO_return = sqlSession.selectOne("memberSQL.memberCall", id);
		sqlSession.close();
	
		return memberDTO_return;
		
	} // memberCall

	
	public int memberUpdate(MemberDTO memberDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int rowUpdate = sqlSession.update("memberSQL.memberUpdate", memberDTO);
		sqlSession.commit();
		sqlSession.close();

		return rowUpdate;

	} // memberUpdate
				
	
	public String memberDelTry(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO_return = sqlSession.selectOne("memberSQL.memberDelTry", id);
		sqlSession.close();
		
		return memberDTO_return.getPwd();
		
	} // memberDelTry

	
	public int memberDelete(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int rowDelete = sqlSession.delete("memberSQL.memberDelete", id); // return int
		sqlSession.commit();
		sqlSession.close();

		return rowDelete;
		
	} // memberDelete
	

	public boolean isExistPwd(Map<String, String> map) {
		// 또는 메서드 인수를 ID, PWD를 받고 isExistPwd함수에서 Map으로 처리 후,
		// selectOne()에 인자를 1개로 넘기는 방법을 사용할 수 있음
		// jsp 파일에서 추가적인 작업을 줄일 수 있음
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO_return = sqlSession.selectOne("memberSQL.isExistPwd", map);

		boolean exist = false;

		if(memberDTO_return!=null) {
			exist=true;
		} else {
			exist=false;
		}
	
		sqlSession.close();
	
		return exist;
	}
	

	
	public int memberDeleteT(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int rowDelete = sqlSession.delete("memberSQL.memberDeleteT", id); // return int
		sqlSession.commit();
		sqlSession.close();

		return rowDelete;
		
	} // memberDelete
	
	
	
	
	
	
	
	
	
	
//	// checkId.jsp
//	public boolean isExistId(String id) {
//		boolean exist= false;
//		
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		Boolean boolean = sqlSession.selectOne("memberSQL.isExistId", id);
//		// selectOne는 boolean을 return하지 않으므로 MemberDTO로 return 받고 java code로 처리
//		
//		
//		if(memberDTO!=null) {
//			exist=true;
//		} else {
//			exist=false;
//		}
//		
//		sqlSession.close();
//		
//		return exist;
//		
//	} // isExistId
	
	
	
	
	
	
	
	
	
	
	
	
	
}
