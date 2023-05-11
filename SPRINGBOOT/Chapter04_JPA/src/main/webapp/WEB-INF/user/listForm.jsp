<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listForm.jsp here</title>
<style>
div#keywordDiv {
  display: inline-block;
  color: tomato;
}
</style>
</head>
<body>


<img src="http://localhost:8080/image/spongebob.png" width="128"
													height="128"
												   onclick="location.href='/'"
												     style="cursor: pointer; margin-left: 160px"
												    />

<form id="listForm">

<table id="listFormTable" border="1" frame="hsides" rules="rows">
	<tr>
		<th width="150">Name</th>
		<th width="150">ID</th>
		<th width="150">PWD</th>
	</tr>


</table>

</form>

<br/>
<div style="width: 450px; text-align: center;">
	<form id="searchForm">
		<select id="searchOpt" name="searchOpt">
			<option value="name">Name</option>
			<option value="id">Id</option>
		</select>
		<input type="text" id="keyword" name="keyword" />
		<input type="button" id="searchBtn" value="Srch" />
	</form>
</div>

<!-- CDM 방식: js파일에 대한 서버로의 직접 접근 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="/js/list.js"></script>


</body>
</html>