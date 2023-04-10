package user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import user.bean.UserDTO;

@Transactional
@Repository
public class UserDAOMyBatis implements UserDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void userInsert(UserDTO userDTO) {
		sqlSession.insert("userSQL.insert", userDTO);
		// .xml: SqlSession: sqlSession, transactionManager: commit, close
	}

	@Override
	public List<UserDTO> getUserList() {
		return sqlSession.selectList("userSQL.getUserList");
	}

	@Override
	public UserDTO getUser(String id) {
		return sqlSession.selectOne("userSQL.getUser", id);
	}

	@Override
	public void userUpdate(Map<String, String> map) {
		sqlSession.update("userSQL.update", map);
	}

	@Override
	public void userDelete(String id) {
		sqlSession.update("userSQL.delete", id);
	}

	@Override
	public void update(Map<String, String> map) {

	}

}
