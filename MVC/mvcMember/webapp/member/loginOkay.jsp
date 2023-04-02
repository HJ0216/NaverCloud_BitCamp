<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
</head>
<body>
<%--String name = session.getAttribute("memName");--%>
<h3>${memName }님 로그인</h3>

<br/>
	 
	 <input type="button" value="로그아웃" onclick="location.href='/mvcMember/member/logout.do'">
	 <input type="button" value="회원정보 수정" onclick="location.href=''">
	 <input type="button" value="회원 탈퇴" onclick="location.href=''">

</body>
</html>