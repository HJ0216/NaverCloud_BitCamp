<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Input.jsp here</title>
</head>
<body>

<form id="sungJukForm" method="post" action="/chapter06_1/sungJuk/result.do">
<!-- name value 이동 -->
<!-- method 수정 시, Controller.java의 @RequestMapping -->
<!-- 같은 *.do 파일이 있을 경우, namespaces 필수 추가 -->
	<table border="1">
		<tr>
			<th width="70">이름</th>
			<td><input type="text" name="name" /></td>
		</tr>
		<tr>
			<th>국어</th>
			<td><input type="text" name="kor" /></td>
		</tr>
		<tr>
			<th>영어</th>
			<td><input type="text" name="eng" /></td>
		</tr>
		<tr>
			<th>수학</th>
			<td><input type="text" name="math" /></td>
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