<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Start here</title>
</head>
<body>

<font color="salmon"><b>
jstlStart.jsp > sendProc.jsp > sendResult 페이지 이동<br/>
sendRedirect: 데이터 공유X<br/>
도착 url: sendResult.jsp<br/>
</b></font>
<br/>

<font color="dodgerblue"><b>
jstlStart.jsp > forwardProc.jsp > forwardResult 페이지 이동<br/>
forwardProc.jsp와 forwardResult.jsp가 합병이 일어나 동일한 jsp 파일처럼 취급되어 데이터 공유O<br/>
주소: forwardProc.jsp / 결과: forwardResult.jsp<br/>
</b></font>
<br>

<input type="button" value="sendRedirect" onclick="location.href='sendProc.jsp'">
<!-- 이동 경로: jstlStart > sendProc > sendResult -->
<input type="button" value="forward" onclick="location.href='forwardProc.jsp'">
<!-- 이동 경로: jstlStart > forwardProc -->

</body>
</html>