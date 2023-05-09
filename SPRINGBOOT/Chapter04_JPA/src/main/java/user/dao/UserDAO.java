package user.dao;
// @EnableJpaRepositories("user.dao")
// JpaRepository에 대한 설정정보를 자동적으로 로딩하고 이 정보를 토대로 Repository 빈을 등록하는 역할


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import user.bean.UserDTO;

@Repository // JpaRepository 상속을 통한 객체 생성
public interface UserDAO extends JpaRepository<UserDTO, String> {
// UserDTO: Entity class, PK type: String

}
