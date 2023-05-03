<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index.jsp here</title>
</head>
<body>

<h3>*** Main ***</h3>
<p><a href="/user/writeForm">Input</a></p>
<!--
/chapter06_SpringWebMaven"/"user/writeForm
"/": dispatcherServlet ▶ HandlerMapping의 도움을 받아 Controller와 연결
-->
<p><a href="/user/list">Output</a></p>
<!-- @RequestParam(required=false, defaultValue="1") String pg 활용 -->
<p><a href="/user/updateForm">Modify</a></p>
<p><a href="/user/deleteForm">Delete</a></p>
<p><a href="/user/uploadForm">Upload</a></p>
<p><a href="/user/uploadFormAjax">Upload_Ajax</a></p>
<p><a href="/user/uploadFormAjaxList">Image List</a></p>
</body>
</html>