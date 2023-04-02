<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>elTest here</title>
</head>
<body>

<table border="1" width="50%">
	<tr>
		<th width="50%">표현식</th>
		<th width="50%">값</th>
	</tr>
	
	<tr>
		<td align="center">\${25+3 }</td>
		<!-- \:입력값 그대로 출력 -->
		<td align="center">${25+3 }</td>
	</tr>

	<tr>
		<td align="center">\${25/3 }</td>
		<td align="center">${25/3 }</td>
		 <!-- java code가 아니라 web code이기때문에
		 	  int type 계산 결과가 int로 반환되지 않음 -->
	</tr>

	<tr>
		<td align="center">\${25 div 3 }</td>
		<td align="center">${25 div 3 }</td>
		<!-- / -> div 사용 가능 -->
	</tr>

	<tr>
		<td align="center">\${25 % 3 }</td>
		<td align="center">${25 % 3 }</td>
	</tr>

	<tr>
		<td align="center">\${25 mod 3 }</td>
		<td align="center">${25 mod 3 }</td>
		<!-- % -> mod 사용 가능 -->
	</tr>

	<tr>
		<td align="center">\${25 < 3 }</td>
		<td align="center">${25 < 3 }</td>
	</tr>

	<!--  > gt, < lt, >= ge, <= le, == eq, != ne -->
	<tr>
		<td align="center">\${25 ne 3 }</td>
		<td align="center">${25 ne 3 }</td>
	</tr>

	<tr>
		<td align="center">\${header['host'] }</td>
		<td align="center">${header.host }</td>
		<!-- 내장객체 header를 통한 ip_address, port 조회 -->
	</tr>

</table>

</body>
</html>