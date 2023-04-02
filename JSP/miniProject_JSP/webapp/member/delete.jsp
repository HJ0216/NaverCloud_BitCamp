<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="member.dao.MemberDAO"
		 import="member.bean.MemberDTO" %>    

<%
// Data
String name = null;
String id   = null;

id = (String) session.getAttribute("memId");
//session.getAttribute("memId"): return Object -> (casting) String 


// DB
MemberDAO memberDAO = MemberDAO.getInstance();
int rowDelete = memberDAO.memberDelete(id);

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% if(rowDelete==0){ %>
<h3>회원 탈퇴 실패</h3>
<input type='button' value='뒤로' onclick='history.go(-1)'>
<%} else { %>
<script>
window.onload=function(){ // delete.jsp pg load시, function 실행
	alert("회원 탈퇴 완료!!");
	location.href = 'loginForm.jsp';
}

</script>
<%} %>

</body>
</html>