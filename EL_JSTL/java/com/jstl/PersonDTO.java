package com.jstl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PersonDTO {
	private String name;
	private int age;
}

// @NoArgsConstructor: 기본 생성자 생성
// @AllArgsConstructor: 모든 필드 값을 파라미터로 받는 생성자 생성
// @RequiredArgsConstructor: final/@NonNull인 필드 값만 파라미터로 받는 생성자 생성
