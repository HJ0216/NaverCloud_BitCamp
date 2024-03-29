package person.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import person.bean.PersonDTO;

@Repository
public class PersonDAOMyBatis implements PersonDAO {

	@Autowired
	private SqlSession sqlSession;
	// mybatis-spring-boot-starter에서 자동으로 sqlSession과 관련된 빈 생성
	// 따로 SqlSession 객체를 생성하거나 설정 파일에 등록할 필요가 없음
	
	@Override
	public void write(PersonDTO personDTO) {
		sqlSession.insert("personSQL.write", personDTO);
	}

	@Override
	public List<PersonDTO> getPersonList() {
		return sqlSession.selectList("personSQL.getPersonList");
	}

}
