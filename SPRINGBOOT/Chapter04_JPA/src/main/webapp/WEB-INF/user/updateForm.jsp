<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateForm.jsp here</title>
</head>
<body>

<img src="http://localhost:8080/image/spongebob.png" width="128"
													height="128"
												   onclick="location.href='/'"
												     style="cursor: pointer; margin-left: 160px"
												    />


<p>
	수정 할 아이디 입력 <input type="text" id="searchId">
	<input type="button" id="searchBtn" value="search">
</p>

<div id="resultDiv"></div>



<form id="updateForm">
	<table border="1">
		<tr>
			<th>NAME</th>
			<td><input type="text" name="name" id="name"></td>
		</tr>

		<tr>
			<th>ID</th>
			<td>
				<input type="text" name="id" id="id" readonly="readonly">
			</td>
		</tr>

		<tr>
			<th>PWD</th>
			<td><input type="password" name="pwd" id="pwd"></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="update" id="updateBtn">
				<input type="reset" value="cancel" id="cancelBtn">
			</td>
		</tr>
	</table>
</form>



<!-- CDM 방식: js파일에 대한 서버로의 직접 접근 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="/js/update.js"></script>
<!-- http://localhost8080/js/update.js -->


</body>
</html>