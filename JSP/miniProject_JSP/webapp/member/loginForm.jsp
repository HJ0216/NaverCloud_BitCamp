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

로그인

<!-- 이미지를 폴더에 저장 후 eclipse에서 새로고침하여 이미지 파일이 확인되야 web에서 load 가능 -->
</h3>
<form name="loginForm" method="post" action="login.jsp">

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
			<!-- js function을 통한 유효성 검사 실시 -->
			<input type="button" value="회원가입" onclick="location.href='http://localhost:8080/miniProject_JSP/member/writeForm.jsp'">
			<!-- onclick="location.href='writeForm.html'""
				 localhost:8080로 부터 넘어온 경우 생략 가능: /memberServlet/member/writeForm.html 
				 같은 /member에 파일이 있을 경우, 생략가능: writeForm.html
				 location.href: js method 대신, html file로 연결 -->
			
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