package user.dao;

import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import user.bean.UserDTO;

public class UserDAO {
	private SqlSessionFactory sqlSessionFactory;
	// SQL Session을 만들기 위해 SQL Session Factory 선언
	
	// SingleTon 방식
	private static UserDAO userDAO = new UserDAO();
	public static UserDAO getInstance() {return userDAO;}
	// static은 생성자보다 위쪽에 선언
	
	public UserDAO() {
		// Reader reader = new ?
		// Reader: 추상 클래스 -> 객체 생성 불가
		// Reader reader = Resources.getResourceAsReader("mybatis-config.xml"); // 문자 단위 읽어오기
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml"); // 바이트 단위 읽어오기 
			// 환경설정 파일을 읽어 -> sqlsession 만들기를 위한 sqlfactory 선언 -> DB 접근
			// SqlSessionFactory = new ?
			// SqlSessionFactory: 인터페이스 -> 객체 생성 불가
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void insert(UserDTO userDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// openSession를 통해 SqlSession을 return 시키면서 매서드를 통한 객체 생성
		sqlSession.insert("userSQL.write", userDTO);
		// sqlSession.insert("userMapper.xml 파일 내부의 tag id", 입력 할 데이터);
		// 다른 mapper.xml 일지라도 tag id value가 겹치면 문제 발생 (모든 Mapper file을 한 번에 읽음)
		// -> namespace를 이용해서 중복 문제 해결
		sqlSession.commit();
		// insert, delete, update: 수동 commit 필요
		sqlSession.close();
	}

	public List<UserDTO> getUserList() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// openSession를 통해 SqlSession을 return 시키면서 매서드를 통한 객체 생성
		List<UserDTO> list = sqlSession.selectList("userSQL.getUserList");
		// selectList: return List -> List 객체 생성 필요 X
		sqlSession.close();
		// commit: insert, select, delete만
		// rs으로 java에서 반환받지 않고 자체적으로 DB에서 DTO를 list에 담아서 반환
		return list;
	}

	public UserDTO getUser(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDTO userDTO = sqlSession.selectOne("userSQL.getUser", id);
		// return type의 default는 object로 선언되어있으나, 변수타입 선언에 따라 자동으로 변환됨
		sqlSession.close();
		return userDTO;
	}
	
	public void update(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("userSQL.update", map);
		sqlSession.commit();
		sqlSession.close();
	}

	public void getUserDelete(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("userSQL.delete", id); // return int
		sqlSession.commit();
		sqlSession.close();
	}

	public List<UserDTO> search(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<UserDTO> list = sqlSession.selectList("userSQL.search", map);
		// SQL로 넘길 수 있는 데이터 양은 1개뿐이므로 map or DTO에 담아서 넘기기
		sqlSession.close();
		return list;
	}
	
	public List<UserDTO> searchT(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<UserDTO> list = sqlSession.selectList("userSQL.searchT", map);
		// SQL로 넘길 수 있는 데이터 양은 1개뿐이므로 map or DTO에 담아서 넘기기
		// userSQL.searchT은 UserDTO를 return하고 DTO가 여러개일 경우를 대비해서 List로 DTO를 받음
		sqlSession.close();
		return list;
	}

}

/*
IO Stream
byte 단위: 속도는 빠르나 한글 깨짐 현상 발생
InputStream: 환경설정 파일 읽어오기
OutputStream

문자 단위: 속도는 느리나 한글이 안깨짐
Reader: 환경설정 파일 읽어오기
Writer
*/