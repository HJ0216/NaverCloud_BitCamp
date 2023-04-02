<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 
내장 객체 param = request.getParameter
 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>elResult here</title>
</head>
<body>

<h3>
${param['x'] } + ${param['y'] } = ${param['x'] + param['y'] } <br/>
${param['x'] } - ${param['y'] } = ${param['x'] - param['y'] } <br/>
${param.x } * ${param.y } = ${param.x * param.y } <br/>
<!-- ['x'] 대신 .x 사용 가능 -->
${param.x } / ${param.y } = ${param.x / param.y } <br/>
<!-- web 표현형식에서는 java처럼 정수, 실수를 구분하지 않음
	 그러므로, 입력받은 값을 나누기할 경우, 자동으로 소수이하 반환
	 + 문자도 숫자와 마찬가지로 문자와 문자열을 나누지 않음  -->

</h3>

</body>
</html>