<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%--
<% int x = reqeust.getParameter(); %>
 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result.jsp here</title>
</head>
<body>

<h3>${sungJukDTO.name } 성적</h3>
<%-- <h3>${sungJukDTO.getName() } 성적</h3> 과 동일 --%>

총점: ${sungJukDTO.getTot() }
평균: ${sungJukDTO.avg }
<%--자료형을 따지지 않으므로 string으로 넘어와도 사칙 연산 가능 --%>

</body>
</html>