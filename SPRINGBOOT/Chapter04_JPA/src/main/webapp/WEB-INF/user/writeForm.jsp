<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeForm.jsp here</title>
<style type="text/css">
#writeForm div{
	color: tomato;
	font-size: 8px;
	font-weight: bold;
}
</style>
</head>
<body>

<form id="writeForm">
	<table border="1">
		<tr>
			<th>NAME</th>
			<td><input type="text" name="name"></td>
		</tr>

		<tr>
			<th>ID</th>
			<td>
				<input type="text" name="id" id="id">
				<div id="idDiv"></div>
			</td>
		</tr>

		<tr>
			<th>PWD</th>
			<td><input type="password" name="pwd"></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="submit" id="writeBtn">
				<input type="reset" value="cancel">
			</td>
		</tr>
	</table>
</form>


<!-- CDM 방식: js파일에 대한 서버로의 직접 접근 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="/js/write.js"></script>


</body>
</html>