<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="prac1" uri="tld" %>
<%@ taglib prefix="mul" uri="tld" %>
<!--
	 taglib: tag에 대한 정보 안내
	 prefix: tag alias(web.xml에 지정),
	 uri: /WEB-INF/elFunc.tld(prefix에 대한 정보가 담겨있는 elFinc.tld 파일 위치 안내)
	 * uri에 있는 파일에서 prac1의 method name과 동일한 함수 확인
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>elResult2 here</title>
</head>
<body>

<h3>자바 클래스의 메소드 이용</h3>
${param['x'] } + ${param['y'] } = ${ prac1:sum(param['x'], param['y']) } <br/>
<!-- alias 부여 -->
${param.x } * ${param.y } = ${ mul:mul(param.x, param.y) } <br/>


</body>
</html>