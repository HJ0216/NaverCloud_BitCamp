<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL End here</title>
</head>
<body>

${requestScope.list }
<!-- jstlStart와 다른 reqeust 객체이므로 forwarding을 통한 변수값 공유 필요 -->


<!-- 
request.getParameter() = ${param.variable_name }
request.setAttribute()
request.getAttribute() = ${requestScope.variable_name}
 -->

</body>
</html>