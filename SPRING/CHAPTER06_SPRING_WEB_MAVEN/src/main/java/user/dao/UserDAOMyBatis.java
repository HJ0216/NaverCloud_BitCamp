package user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import user.bean.UserDTO;

@Repository
@Transactional
public class UserDAOMyBatis implements UserDAO {
	@Autowired
	private SqlSession sqlSession;
	// myBatis 설정
	// 1. pkg: spring / mybatis-config.xml
	
	@Override
	public void write(UserDTO userDTO) {
		sqlSession.insert("userSQL.write", userDTO);
	}
	
	// 페이징
	@Override
	public List<UserDTO> getUserList(Map<String, Integer> map) {
		return sqlSession.selectList("userSQL.getUserList", map);
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("userSQL.getTotalA");
	}

	
	
	@Override
	public UserDTO getUser(String id) {
		return sqlSession.selectOne("userSQL.getUser", id);
	}

	@Override
	public void update(Map<String, String> map) {
		sqlSession.update("userSQL.update", map);
		
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("userSQL.delete", id);
	}
	
	
}
