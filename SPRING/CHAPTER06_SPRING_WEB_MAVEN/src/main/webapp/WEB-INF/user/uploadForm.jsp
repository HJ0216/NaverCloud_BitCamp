<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadForm.jsp here</title>
</head>
<body>

<!-- 1. 단순 submit, 반드시 post 방식, 반드시 enctype="multipart/form-data" 선언 -->
<form method="post" enctype="multipart/form-data" action="/chapter06_SpringWebMaven/user/upload">
	<input type="file" name="img" />
	<br/>
	<input type="submit" id="uploadBtn" value="이미지 등록"/>
</form>


<!-- 2. ajax -->


</body>
</html>