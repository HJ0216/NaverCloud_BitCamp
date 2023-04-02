<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardWriteForm here</title>

<style type="text/css">

#boardWriteForm div {
	color: red;
	font-size: 8px;
	font-weight: bold;
}
/*#: name attribute*/
</style>

</head>
<body>

<form id="boardWriteForm">
<!-- ajax 사용에 따른 삭제: method="post" action="/miniProject_jQuery/board/writeForm.do" -->

<h3>WRITE FORM</h3>

<table border="1" cellpadding="5" cellspacing="0">

	<tr>
		<th width="70">Subject</th>
		<td><input type="text" name="subject" id="subject" placeholder="Ener the subject">
		<div id="subjectDiv"></div>
		</td>
	</tr>
	

	<tr>
		<th width="70">Contents</th>
		<td><textarea name="content" id="content" cols="50" rows="10"  placeholder="Enter the Content"></textarea>
		<!-- <textarea></textarea>는 태그 사이의 띄어쓰기 or 개행을 입력값으로 받으므로 붙여쓰기 -->
		<div id="contentDiv"></div>
		</td>
	</tr>
	

	<tr>
		<td colspan="2" align="center">
			<input type="button" value="Complete" id="boardWriteBtn">
			<!-- onclick: js() 사용 시, submit에 따른 action을 false 처리해줘야 하므로 id 지정 후 ajax 처리 -->
			<input type="reset" value="ReWrite">
		</td>
	</tr>

	
</table>

</form>

<%-- jQuery 사용에 대한 안내: 서버에 직접 접근(CDM 방식) --%>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="../js/boardWrite.js"></script>

</body>
</html>