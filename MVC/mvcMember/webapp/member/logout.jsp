<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout here</title>
</head>
<body>

<h3>LOGOUT</h3>

<script>
window.onload=function(){
	alert("Log Out");
	location.href = "/mvcMember/member/loginForm.do";
}

// <body onload="pick()"> -> js on load function 이용 
</script>

</body>
</html>