<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteForm.jsp here</title>
</head>
<body>

<h3>
	<a href="/"><img src="/img/spongebob.png" width="100" height="100"></a>
	회원 탈퇴
</h3>

<p>
	삭제할 아이디 입력: <input type="text" id="searchId" placeholder="Enter the ID">
	<input type="button" id="deleteBtn" value="search" />
</p>
<div id="resultDiv"></div>

<!-- CDM 방식: js파일에 대한 서버로의 직접 접근 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="/js/delete.js"></script>


</body>
</html>