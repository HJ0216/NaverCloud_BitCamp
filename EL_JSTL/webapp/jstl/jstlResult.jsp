<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Result here</title>
</head>
<body>

<fmt:requestEncoding value="UTF-8"/>
<!-- request.setCharterEncoding("UTF-8")과 post method 사용 시, 인코딩 방법 동일 -->

<ul>
	<li>이름: ${param['name'] }</li><br/> <!-- name attribute -->


	<li>
		나이: ${param['age'] }
		<c:if test="${param.age>=19 }">, <strong>성인</strong></c:if>
		<c:if test="${param.age<19 }">, <strong>청소년</strong></c:if>
	</li>
	<br/>	


	<li>색깔: ${param.color }
	
	<c:if test="${param.color == 'red' }">, 빨강</c:if>
	<c:if test="${param.color == 'green'}">, 초록</c:if>
	<c:if test="${param.color eq 'blue'}">, 파랑</c:if>
	<c:if test="${param.color eq 'magenta'}">, 보라</c:if>
	<c:if test="${param.color eq 'cyan'})">, 하늘</c:if>


	<c:choose>
		<c:when test="${param.color == 'red' }">, 빨강</c:when>
		<c:when test="${param.color == 'green'}">, 초록</c:when>
		<c:when test="${param.color eq 'blue'}">, 파랑</c:when>
		<c:when test="${param.color eq 'magenta'}">, 보라</c:when>
		<c:otherwise>, 하늘</c:otherwise>
	</c:choose>	
	
	</li>
	<br/>


	<li>취미: ${paramValues['hobby'][0] } <!-- 빈값은 출력이 되지 않으므로 모든 배열 출력 가능 -->
			  ${paramValues.hobby[1] }
			  ${paramValues['hobby'][2] }
			  ${paramValues.hobby[3] }
			  ${paramValues['hobby'][4] }
	<!-- name속성, 배열로 넘어옴 -->
	<!-- getParameterValues: parameter가 여러 요소일 때 -->
	<br/>
	
	<c:forEach var="data" items="${paramValues.hobby }">
	<!-- 확장형 for문: 데이터 타입 기재 필요 X, 배열 길이만큼 자동적으로 data 반환  -->
		${data }
	</c:forEach>
	
	</li>
	<br/>
</ul>

</body>
</html>