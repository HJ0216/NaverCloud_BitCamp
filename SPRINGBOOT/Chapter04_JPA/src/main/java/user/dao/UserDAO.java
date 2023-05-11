package user.dao;
// @EnableJpaRepositories("user.dao")
// JpaRepository에 대한 설정정보를 자동적으로 로딩하고 이 정보를 토대로 Repository 빈을 등록하는 역할


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import user.bean.UserDTO;

@Repository // JpaRepository 상속을 통한 객체 생성
public interface UserDAO extends JpaRepository<UserDTO, String> {
// public interface UserDAO extends CrudRepository<UserDTO, String> {
// UserDTO: Entity class, PK type: String
	
	
	// Query Method
	// 쿼리 메소드는 메소드의 이름으로 필요한 쿼리를 만들어주는 기능
	// 스프링 JPA에서는 복잡한 JPQL을 대신해서 처리할 수 있는 쿼리 메소드라는 기능을 제공
	
	// 쿼리 메소드를 작성할 때 엔티티 이름은 생략 가능
	// 현재 사용하는 Repository 인터페이스에서 선언된 타입 정보를 기준으로 자동 엔티티 이름이 적용

	
	// 쿼리 메소드 리턴 타입: Collection<T> 타입(Page<T>, Slice<T>, List<T>)
	// 이 중에서 가장 많이 사용하는 것은 Page<T>, List<T>
	// 단순 목록 검색: List<T>, 페이징 처리: Page<T> 사용

	
	// findByName: SELECT * FROM USERTABLE WHERE NAME=?
	// findByNameContaining: SELECT * FROM USERTABLE WHERE NAME LIKE '%' || ? || '%'
	
	// SELECT * FROM USERTABLE WHERE NAME LIKE '%' || #{keyword} || '%'
	public List<UserDTO> findByNameContaining(String keyword); // ? ▶ keyword
	
	// SELECT * FROM USERTABLE WHERE ID LIKE '%' || #{keyword} || '%'
	public List<UserDTO> findByIdContaining(String keyword);

	
	// 추상메서드를 선언하더라도 구현체는 JpaRepository<UserDTO, String>가 생성
	

	/*
		검색 쿼리문 생성 예시
		List<Board> list = boardRepository.findByTitleContainingOrContentContaining("17", "17");
		=> select * from board where title like '%' || #{title} || '%' or content like '%' || #{content} || '%' 
	*/

	/* 
	 * JPA METHOD
		findById:
		 SELECT * FROM TABLENAME WHERE ID = ?
		deleteById:
		 SELECT * FROM TABLENAME WHERE ID = ?
		 DELETE FROM TABLENAME WHERE ID = ?	 
	*/
	
	
	
	
	
	
	
	
	
	
	// @Query, JPQL(Java Persistence Query Language) 사용
	// 복잡한 쿼리를 사용하거나 연관관계에 기반한 조인 검색을 처리해야하는 경우
	// 특정 데이터베이스에 종속적인 네이티브 쿼리를 사용해야하는 경우

	// JPQL: 검색 대상이 테이블이 아니라 영속성 컨텍스트(DTO)에 등록된 엔티티
	// FROM 절에 엔티티 이름과 컬럼 대신 엔티티(DTO)가 가지고 있는 변수를 조회하기 때문에 사용하는 변수 이름은 대소문자를 구분하여 정확하게 지정해야 한다.


/*	
	@Query("SELECT userDTO FROM UserDTO userDTO where userDTO.name LIKE '%' || ?1 || '%'")
	// 변수명이 2개 이상일 경우, LIKE '%' || ?1 || '%' || 2? || ...로 연결
 	public List<UserDTO> getUserSearchName(String keyword);
	// QUERY(): SELECT * FROM TABLENAME WHERE ID LIKE '%'||#{keyword}||'%'
	// * 대신 DTO의 변수명 기재
	// ?1: 숫자 1 생략 불가, 1번째 parameter이므로 ?2 ?3 ?1로 기재 시, 1번째 인자값은 ?1에 배정됨
	// ?1: 대신 :parameter 작성 가능

	@Query("SELECT userDTO FROM UserDTO userDTO where userDTO.id LIKE '%' || ?1 || '%'")
	public List<UserDTO> getUserSearchId(String keyword);
*/
	@Query("SELECT userDTO FROM UserDTO userDTO where userDTO.name LIKE '%' || :keyword || '%'")
 	public List<UserDTO> getUserSearchName(String keyword);
	// ?1 대신 :keyword 사용 가능

	@Query("SELECT userDTO FROM UserDTO userDTO where userDTO.id LIKE '%' || :keyword || '%'")
	public List<UserDTO> getUserSearchId(String keyword);
	
}
