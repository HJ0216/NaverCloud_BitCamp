package com.example.demo;
//동일한 pkg에 위치할 경우, @Component-scan없이 객체 인식 가능



import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
// JPA-Entity: 테이블에 대응하는 하나의 클래스
// spring-boot-starter-data-jpa Dependency를 추가하고 @Entity를 붙이면 테이블과 자바 클래스가 매핑이 된다.
@Table(name="board") // DTO가 하나의 table로 인식 ▶ MySQL에서 create table을 하지 않아도 됨, name을 통해 class 이름과 다른 table 이름 설정
@Data // Lombok: Setter, Getter, Constructor
@SequenceGenerator(name="BOARD_SEQ_GENERATOR", // Sequence Generator Name
				   // 제너레이터(Generator)는 일반적으로 컴퓨터에서 임의의 값을 생성하는 프로그램 또는 함수
				   sequenceName = "BOARD_SEQ", // Sequence Name
				   // 시퀀스(Sequence)는 데이터베이스에서 제공하는 자동 증가 값을 생성하는 객체
				   // BOARD_SEQ_GENERATOR를 사용하면 BOARD_SEQ 시퀀스를 참조하여 ID 값을 생성
				   initialValue = 1,
				   allocationSize = 1)
// initialValue: 초기값
// allocationSize: 1씩 증가
// MySQL은 OracleSQL과 달리 Sequence 객체가 없으므로 seq가 테이블로 생성됨
public class BoardDTO {
	
	@Id // PK 설정
	@Column(name="seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
	// GenerationType.IDENTITY: 데이터베이스에서 자동 증가 값 사용
	// GenerationType.SEQUENCE: 시퀀스 사용, @SequenceGenerator: 시퀀스의 이름 등을 설정
	private int seq;
	
	@Column(name="id", nullable=false, unique=true, length=30)
	private String id;

	@Column(name="name", nullable=false, length=30)
	private String name;

	@Column(name="subject")
	private String subject;

	@Column(name="content")
	private String content;
	
	@CreationTimestamp
	private Timestamp logtime;
	
	/*
		create table board(
			id varchar(30) unique not null PRIMARY KEY,
			name varchar(30) not null,
			subject varchar,
			content varchar,
			logtime timestamp
		);
		
		JPA-Entity: 테이블에 대응하는 하나의 클래스
		boardDTO는 table과 연결되어있는 class임을 안내하기 위해 @Entity 기재
		@Entity 선언 시, PK 선언 필수(@Id)

	*/
	
}
