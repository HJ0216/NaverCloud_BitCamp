<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forwardResult here</title>
</head>
<body>

list 결과: ${requestScope.list }<br/><br/>
<!-- forwardProc: request == forwardResult: requestScope -->

세번째 항목: ${requestScope.list[2] }<br/><br/>
<!-- 
arrayList 주소: 클래스@16진수
overriding을 통해서 list의 주소가 아닌 list가 갖고있는 value return
return된 값은 value가 아닌 주소값
 -->

list2 결과: ${requestScope.list2 }<br/><br/>
<!-- toString이 Overriding되어 list2 주소 값 대신 배열의 각 값 return -->


<c:forEach var="personDTO" items="${list2 }">
<!--
pageScope > requestScode > sessionScope > applicationScope 순으로 list2에 해당하는 변수 찾음
그러므로 requestScope 생략 가능
 -->
	이름: ${personDTO.getName() } &emsp; 나이: ${personDTO.getAge() }<br/>
	이름: ${personDTO.name } &emsp; 나이: ${personDTO.age }<br/><br/>
	<!--
	메소드명을 변수명처럼 사용할 수 있음
	personDTO.getName()
	personDTO.getName
	personDTO.name // class 제외 소문자 시작
	DTO상 private variable(private String name, private int age)이 아닌 getName, getAge라는 함수를 의미
	 -->
</c:forEach>
<!-- 자바와 달리 DataType 존재 X, var에 변수명만 지정
 -->

</body>
</html>