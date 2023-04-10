package user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import lombok.Setter;
import user.bean.UserDTO;

@Repository // DB와 연동하는 객체 생성
// implements UserDAO는 @Repository에 따라 연결
// @Component
// ClassName과 다른 객체 생성 시, "이름 지정" 필요
// 3. NamedParameterJdbcDaoSupport
public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements UserDAO {
	// NamedParameterJdbcDaoSupport
	// SQL 구문 작성 시, ?를 입력할 경우, col을 알 수 없는 불편함을 해소
	// NamedParameterJdbcDaoSupport 사용 시, getJdbcTemplate() 사용 가능
	
	// private DataSource dataSource;
	// setter()를 통한 dataSource value injection 필요

	/*
	final method override 불가
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	*/

	@Autowired // 부모 method를 통해서 setter injection 가능
	// setter injection을 위해서 이름을 변경하여 내용만 override
	public void setDS(DataSource dataSource){
		setDataSource(dataSource);
		// JdbcDaoSupport method: setDataSource 호출하여 dataSource value 공유
	}	

	
	// 2. Constructor 사용(Autowired 불요)
	/*
	public UserDAOImple(DataSource dataSource) {
		setDataSource(dataSource);		
	}
	*/
	
	@Override
	public void userInsert(UserDTO userDTO) {
		String sql = "INSERT INTO USERTABLE VALUES(:name, :id, :pwd)";

		// getJdbcTemplate().update(sql, userDTO.getName(), userDTO.getId(), userDTO.getPwd());

		// NamedParameterJdbcDaoSupport 사용 전제 조건: 데이터 입력을 Map으로만 선언할 수 있음
		Map<String, String> map = new HashMap<>();
		map.put("name", userDTO.getName());
		map.put("id", userDTO.getId());
		map.put("pwd", userDTO.getPwd());
		
		getNamedParameterJdbcTemplate().update(sql, map);
		// 같은 이름을 갖은 인자끼리 자동 mapping
	}
	

	@Override
	public List<UserDTO> getUserList() {
		String sql = "SELECT * FROM USERTABLE";
		return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<UserDTO>(UserDTO.class));
		// resultSet의 데이터를 꺼내서, DTO와 mapping 시키는 과정은 Spring이 자동으로 실행함
		// 1줄당 userDTO에 데이터 mapping: UserDTO.class=mappedClass
	}
	
	
	// UserUpdateSevice ver.1 (getUser() + update)
	@Override
	public UserDTO getUser(String id){
		String sql = "SELECT  * FROM USERTABLE WHERE ID=?";
		try{
		return getJdbcTemplate().queryForObject(sql,
		 				  						new BeanPropertyRowMapper<UserDTO>(UserDTO.class), 
		 				  						// 1row는 <userDTO> 타입이며, userDTO.class와 Mapping 할 것임을 안내
		 				  						id);
		// queryForObject();
		// rowMapper<T>: 행단위(NAME, ID, PWD) Mapping userDTO: NAME, ID, PWD
		// resultSet과 DTO의 col_name이 동일할 경우, 모든 col에 대한 Mapping 진행

		} catch(EmptyResultDataAccessException e){
			return null;
		}
		
		// cf. public void sub(int...ar){}
		// 매개변수 개수 가변
		
		// Generics<> 종류: class T element E Key K Value V
	}

	@Override
	public void update(Map<String, String> map) {
		String sql = "UPDATE USERTABLE SET NAME=:name, PWD=:pwd WHERE ID=:id";		
		getNamedParameterJdbcTemplate().update(sql, map);
	}
	
	
	// UserUpdateSevice ver.2 (getUserList() + userUpdate)
	@Override
	public void userUpdate(UserDTO userDTO) {
		String sql = "UPDATE USERTABLE SET NAME=:name, PWD=:pwd WHERE ID=:id";
		Map<String, String> map = new HashMap<>();
		map.put("name", userDTO.getName());
		map.put("id", userDTO.getId());
		map.put("pwd", userDTO.getPwd());
		
		getNamedParameterJdbcTemplate().update(sql, map);
	}


	@Override
	public void userDelete(String id) {
		String sql = "DELETE FROM USERTABLE WHERE ID=:id";
		getJdbcTemplate().update(sql, id);
		// insert(), update(), delete() 모두 update() 사용
		// getJdbcTemplate().query(sql, new BeanPropertyRowMapper<UserDTO>(UserDTO.class));
		// java.sql.SQLException: ORA-01008: 일부 변수가 바인드되지 않았습니다.
	} 


}


/*

2. JdbcDaoSupport

public class UserDAOImpl extends JdbcDaoSupport implements UserDAO {
	// @Setter
	// private JdbcTemplate jdbcTemplate = null;
	// DAO를 만들 때마다, JdbcTemplate의 중복 생성
	// JdbcDaoSupport: JdbcTemplate() 제공
	// JdbcTemplate bean 생성 필요 없이, 메서드 호출로 JdbcTemplate 생성 가능
	// 상속: extends JdbcDaoSupport, 구현: implements UserDAO

	@Override
	public void insert(UserDTO userDTO) {
		String sql = "INSERT INTO USERTABLE VALUES(?, ?, ?)";
		getJdbcTemplate().update(sql, userDTO.getName(), userDTO.getId(), userDTO.getPwd());
	}

	@Override
	public List<UserDTO> getUserList() {
		String sql = "SELECT * FROM USERTABLE";
		return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<UserDTO>(UserDTO.class));
		// resultSet의 데이터를 꺼내서, DTO와 mapping 시키는 과정은 Spring이 자동으로 실행함
		// 1줄당 userDTO에 데이터 mapping: (UserDTO.class) 기재
	}
}
*/


/*

1. JdbcTemplate

public class UserDAOImpl implements UserDAO {
	@Setter
	private JdbcTemplate jdbcTemplate = null;
	// JdbcTemplate: getConnection, prepareStatement, close(conn, psmt) 중복 제거 가능

	@Override
	public void insert(UserDTO userDTO) {
		String sql = "INSERT INTO USERTABLE VALUES(?, ?, ?)";
		jdbcTemplate.update(sql, userDTO.getName(), userDTO.getId(), userDTO.getPwd());
	}

	@Override
	public List<UserDTO> getUserList() {
		String sql = "SELECT * FROM USERTABLE";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserDTO>(UserDTO.class));
		//resultSet의 데이터를 꺼내서, DTO와 mapping 시키는 과정은 Spring이 자동으로 실행함
		// 1줄당 userDTO에 데이터 mapping: (UserDTO.class) 기재
	}
}
 */
