<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="member.dao.MemberDAO" %>
    
<%

	//Data
	String id = (String) session.getAttribute("memId");
	
//	session.removeAttribute("memName");
//	session.removeAttribute("memName");

	session.invalidate(); // 세션 무효화

	MemberDAO memberDAO = MemberDAO.getInstance();
	memberDAO.memberDeleteT(id);
	// return 값이 true or false일 경우 함수명이 is로 시작
	
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
window.onload=function(){
	alert("회원 탈퇴 완료!!");
	location.href = 'loginForm.jsp';
}

// <body onload="pick()"> -> js on load function 이용 
</script>

</body>
</html>