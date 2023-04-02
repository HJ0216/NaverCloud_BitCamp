<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="member.dao.MemberDAO" %>

<%
	//Data
	String id = (String) session.getAttribute("memId");
	
	session.invalidate(); // 세션 무효화
//	session.removeAttribute("memName");
//	session.removeAttribute("memName");

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteT page here</title>
</head>
<body>

<script>
window.onload=function(){
	alert("회원 탈퇴 완료!!");
	location.href = 'loginForm.jsp';
}

</script>

</body>
</html>