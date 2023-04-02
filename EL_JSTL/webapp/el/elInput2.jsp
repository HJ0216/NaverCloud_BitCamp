<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>elInput2 here</title>
</head>
<body>

<form name="calcForm" method="get" action="elResult2.jsp">
	<h3>자바 클래스의 메소드 이용</h3>
	<table border="1" cellpadding="10" cellspacing="0">
	<!-- result.jsp
		 1. Data
		 2. Response -->
	
	<tr>
		<td width="70" align="center">X</td>
		<td><input type="text" name="x">
		</td>
	</tr>


	<tr>
		<td width="70" align="center">Y</td>
		<td><input type="text" name="y">
		</td>
	</tr>
	
	
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="계산">
			<input type="reset" value="취소">
		</td>
	</tr>
	
	</table>	
</form>

</body>
</html>