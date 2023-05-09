package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardDAO extends JpaRepository<BoardDTO, Integer>{
	// JpaRepository<T, ID> : T-Entity Class, ID: PK JAVA type
	// Interface BoardDAO는 구현체를 따로 생성하지 않음(JpaRepository가 내부적으로 생성)

/*
	Spring Boot + MySQL + JPA
	스프링 부트가 내부적으로 인터페이스에 대한 구현 객체를 자동으로 생성 ▶
	스프링 데이터 JPA를 사용하는 경우는 별도의 구현 클래스를 만들지 않고 인터페이스만 정의함으로써 기능을 사용할 수 있음
	또한 JPA를 이용해서 데이터베이스를 연동하기 위해서 사용했었던 EntityManagerFactory, EntityManager, EntityTransaction(자동 Commit, Close) 같은 객체도 필요 없음
*/	
	
}
