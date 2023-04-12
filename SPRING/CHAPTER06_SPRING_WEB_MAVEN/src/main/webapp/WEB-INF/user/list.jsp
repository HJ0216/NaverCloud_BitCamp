<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp here</title>
</head>
<body>

<h3>
	<a href="/chapter06_SpringWebMaven/"><img src="../img/spongebob.png" width="100" height="100"></a>
	List
</h3>

<table id="userListTable" border="1" frame="hsides" rules="rows"> <!-- 가로 선만 출력 -->
	<tr>
		<th width="150">Name</th>
		<th width="150">Id</th>
		<th width="150">Pwd</th>
	</tr>

	<!-- 동적 처리: script -->
	
</table>
<!-- CDM 방식: js파일에 대한 서버로의 직접 접근 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="../js/list.js"></script>
<%-- /: DispatcherServlet 호출이 아님을 mvc:resources 선언 --%>
</body>
</html>