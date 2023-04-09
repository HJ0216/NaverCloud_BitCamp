package user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import lombok.Setter;
import user.bean.UserDTO;


// 3. NamedParameterJdbcDaoSupport
public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements UserDAO {
	// NamedParameterJdbcDaoSupport
	// SQL 구문 작성 시, ?를 입력할 경우, col을 알 수 없는 불편함을 해소
	// NamedParameterJdbcDaoSupport 사용 시, getJdbcTemplate() 사용 가능
	

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
