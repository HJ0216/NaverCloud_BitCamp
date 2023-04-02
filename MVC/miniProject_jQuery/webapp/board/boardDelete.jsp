<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardDelete here</title>
<style type="text/css">
</style>
</head>
<body>

<%--jQuery 사용 시, js파일 삽입 --%>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	alert('글삭제 완료');
	location.href='/miniProject_jQuery/board/boardList.do?pg=1';	
});
</script>

</body>
</html>
