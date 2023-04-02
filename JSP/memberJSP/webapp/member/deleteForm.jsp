<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="member.dao.MemberDAO" %>

<%
	request.setCharacterEncoding("UTF-8");

// Data

String name = null;
String id = null;

name = (String) session.getAttribute("memName");
// session.getAttribute("memName"): return Object -> String으로 형변환
id = (String) session.getAttribute("memId");

//DB
MemberDAO memberDAO = MemberDAO.getInstance();
String pwd = memberDAO.memberDelTry(id);
	
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">	
<title>Delete Member</title>

<style type="text/css">

div {
	color: red;
	font-size: 8pt;
	font-weight: bold;
}

</style>

</head>
<body>


<form name="deleteForm" method="post" action="delete.jsp">

<table border="1" cellpadding="5" cellspacing="0">

	<tr>
		<th width="150">비밀번호 입력</th>
		<td><input type="password" name="pwd" id="pwd" placeholder="비밀번호 입력">
		<div id="pwdDiv"></div>
		<td><input type="button" value="검색" onclick="checkDelete()">
		</td>
	</tr>
	
	
</table>

</form>

<!-- 
< % if(inputPwd.equals(pwd)) {
	// 페이지 이동
	response.sendRedirect("delete.jsp");
	
} else {
	
}
% >
 -->
<script type="text/javascript">

	var pwd = "<%=pwd %>"
	// js에서 java에서 선언한 pwd를 사용하기 위해서는 변수 새로 선언 필요

	function checkDelete() {
		document.getElementById("pwdDiv").innerText = "";
		
		if(document.deleteForm.pwd.value=="") {
			document.getElementById("pwdDiv").innerText="비밀번호 입력";
			return;
		}
		else if(document.deleteForm.pwd.value!=pwd.toString()){
			alert("비밀번호 불일치");
		}
	 	
	 	else {document.deleteForm.submit();} // submit -> action
	} // check()
	
</script>


</body>
</html>