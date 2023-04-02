<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 사용자 생성 객체를 사용할 경우, 호환성이 문제되므로 범용 객체 사용 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Test here</title>
</head>
<body>

<h3>*** 변수 설정 ***</h3>
<c:set var="name" value="홍길동"></c:set>
<!-- <c:set var="name" value="홍길동"/> -->
<!-- <c:set var="name">홍길동</c:set> -->

<!-- var="변수명 입력" value="변수 값 입력" -->
<c:set var="age">25</c:set>

나의 이름은 <c:out value="${name }"/> 입니다.<br>
내 나이는 ${age }살 입니다.<br>
<!-- age라는 변수를 선언한 적이 없을 경우: error가 아닌 공란 출력 -->


<h3>*** for Each ***</h3>
<c:forEach var="i" begin="1" end="10" step="1">
	${i }&nbsp;&emsp;
	<!-- nbsp: 한 칸 띄우기 emsp: 네 칸 띄우기 -->

<c:set var="sum" value="${sum+i }"/>
<!-- 지역 변수i를 사용하기 위해서 내부에서 변수 선언 -->
</c:forEach>
<hr/>

1~10까지의 합: ${sum }
<hr/>

<c:forEach var="i" begin="1" end="10" step="1"> <!-- step: 양수만 사용 가능 -->
	${11-i }&nbsp;&emsp;
</c:forEach>
<hr/>


<h3>*** forToken ***</h3>
<!-- 
문자열 분리
1. StringTokenizer
2. String class: split()
-->

<c:forTokens var="car" items="소나타, 아우디, 링컨, 페라리, 벤츠" delims=",">
<!-- delims를 기준으로 구분 -->
	${car }<br/>
</c:forTokens>

 
</body>
</html>