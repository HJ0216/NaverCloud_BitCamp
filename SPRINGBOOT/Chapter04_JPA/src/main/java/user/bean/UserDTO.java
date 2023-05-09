package user.bean;
// jpaApplication.java와 다른 pkg에 DTO가 위치할 경우, @EntityScan 필요

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
// 해당 클래스가 데이터베이스의 테이블과 매핑되어, 해당 클래스의 객체를 이용하여 데이터베이스 조작 가능
// JPA-Entity: 테이블에 대응하는 하나의 클래스
// spring-boot-starter-data-jpa Dependency를 추가하고 @Entity를 붙이면 테이블과 자바 클래스가 매핑이 된다.
@Table(name="usertable") // DTO가 SQL상 table로 선언
@Data
public class UserDTO {

	@Column(name="name", nullable=false, length=30)
	private String name;

	@Id // DTO table 생성 시, PK 선언 필수(not null and unique)
	@Column(name="id", nullable=false, length=30)
	private String id;

	@Column(name="pwd", nullable=false, length=30)
	private String pwd;
	
	
}
