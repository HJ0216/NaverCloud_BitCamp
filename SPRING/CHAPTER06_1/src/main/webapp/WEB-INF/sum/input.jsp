<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Input.jsp here</title>
</head>
<body>

<form id="sumForm" method="post" action="/chapter06_1/result.do">
<!-- name value 이동 -->
<!-- method 수정 시, Controller.java의 @RequestMapping -->
	<table border="1">
		<tr>
			<th width="70">X</th>
			<td><input type="text" name="calcX" /></td>
		</tr>
		<tr>
			<th>Y</th>
			<td><input type="text" name="calcY" /></td>
		</tr>
	
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="calc" />
				<input type="reset" value="reset" />
			</td>
		</tr>
		
	</table>
	
</form>

</body>
</html>