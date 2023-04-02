<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form here</title>

<style type="text/css">

div {
	color: red;
	font-size: 8pt;
	font-weight: bold;
}

</style>

</head>
<body>
<h3>
<img src="../image/android.png" width="50" height="50" alt="안드로이드"
onclick="location.href='http://localhost:8080/miniProject_JSP/index.jsp'" style="cursor: pointer;">
<!-- 이미지를 폴더에 저장 후 eclipse에서 새로고침하여 이미지 파일이 확인되야 web에서 load 가능 -->

로그인
</h3>

<form name="loginForm" method="post" action="/mvcMember/member/login.do">
<!-- *.do: servlet으로 mapping되어 있음 -->

<table border="1" cellpadding="5" cellspacing="0">

	<tr>
		<th width="70">아이디</th>
		<td><input type="text" name="id" id="id" placeholder="ID 입력">
		<div id="idDiv"></div>
		</td>
	</tr>
	

	<tr>
		<th width="70">비밀번호</th>
		<td><input type="password" name="pwd" id="pwd" placeholder="비밀번호 입력">
		<div id="pwdDiv"></div>
		</td>
	</tr>
	

	<tr>
		<td colspan="2" align="center">
			<input type="button" value="로그인" onclick="checkLogin()">
			<input type="button" value="회원가입" onclick="location.href='/mvcMember/member/writeForm.do'">
			<!-- 경로: java: /mvcMember/member/*.do, jsp: /member/*.jsp -->
			<!-- 모든 client의 접근은 *.do: servlet을 통해 이뤄지도록 함 -->
		</td>
	</tr>
	
</table>

</form>


<script type="text/javascript">

	function checkLogin() {
		document.getElementById("idDiv").innerText = "";
		document.getElementById("pwdDiv").innerText = "";
		
		if(document.loginForm.id.value=="") {
			document.getElementById("idDiv").innerText="아이디 입력";
			return;} // return: 함수 종료

		else if(document.loginForm.pwd.value=="") {
			document.getElementById("pwdDiv").innerText="비밀번호 입력";
			return;
		}
	 	
	 	else {document.loginForm.submit();} // submit -> action
	 	
	} // check()
	
</script>

</body>
</html>