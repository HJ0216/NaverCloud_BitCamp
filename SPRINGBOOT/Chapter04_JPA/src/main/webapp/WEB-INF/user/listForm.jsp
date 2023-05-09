<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listForm.jsp here</title>
</head>
<body>

<form id="listForm">

<table id="listFormTable" border="1" frame="hsides" rules="rows">
	<tr>
		<th width="150">Name</th>
		<th width="150">ID</th>
		<th width="150">PWD</th>
	</tr>

	<tr>
		<td>이름</td>
		<td>아이디</td>
		<td>패스워드</td>
	</tr>


</table>

</form>

<!-- CDM 방식: js파일에 대한 서버로의 직접 접근 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="../js/list.js"></script>


</body>
</html>