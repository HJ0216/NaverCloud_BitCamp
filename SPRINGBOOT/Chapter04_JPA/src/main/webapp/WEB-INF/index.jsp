<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index.jsp here</title>
</head>
<body>
<h3>*** 메인화면 ***</h3>
<h4>
	<a href="/user/writeForm">등록</a><br/>
	<a href="/user/listForm">목록</a><br/>
	<a href="/user/updateForm">수정</a><br/>
	<a href="/user/deleteForm">삭제</a><br/>
</h4>

<%--
.jsp 사용 시, 필요한 기본 폴더 구조(/webapp/WEB-INF)
: springboot에서는 jsp를 기본으로 제공해주지 않으므로 jsp 사용 시, 폴더 생성을 수동으로 필수적으로 구축해놓아야 함
 --%>
</body>
</html>