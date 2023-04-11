<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeForm.jsp here</title>
<style type="text/css">
di
</style>
</head>
<body>

<h1><a href="/chapter06_SpringWebMaven/"><img src="../img/spongebob.png" width="100" height="100"></a>회원가입</h1>
<%-- <img> /를 사용하여서 dispathcerServlet 요청을 한 것으로 혼동 --%>
<!-- "/" = index -->
<form>
	<table border="1">
		<tr>
			<th>이름</th>
			<td>
				<input type="text" name="name" id="name" />
				<div id="nameDiv"></div>
			</td>
		</tr>

		<tr>
			<th>아이디</th>
			<td>
				<input type="text" name="id" id="id" />
				<div id="idDiv"></div>
			</td>
		</tr>

		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" name="pwd" id="pwd" />
				<div id="pwdDiv"></div>
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="submit" id="writeBtn" />
				<input type="reset" value="cancel" />
			</td>
		</tr>
		
	</table>
</form>

<!-- CDM 방식: js파일에 대한 서버로의 직접 접근 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="../js/write.js">
</script>
</body>
</html>