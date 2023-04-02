<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
// Data
	String name = null;
	String id = null;
	String email = null;
	
	name = (String) session.getAttribute("memName");
	id = (String) session.getAttribute("memId");
	email = (String) session.getAttribute("memEmail");
	
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

div {
	color: red;
	font-size: 8pixel;
	font-weight: bold;
}

</style>

</head>
<body>


<form name="boardWriteForm" method="post" action="boardWriteT.jsp">

<h3>
	<img src="../image/xbox.jfif" width="50" height="50" alt="엑스박스"
	onclick="location.href='../index.jsp'" style="cursor: pointer;">
	WRITE FORM
</h3>

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
			<input type="button" value="Complete" onclick="checkBoardWrite()">
			<input type="reset" value="ReWrite">
		</td>
	</tr>

	
</table>

</form>

<script type="text/javascript">

	function checkBoardWrite() {
		document.getElementById("subjectDiv").innerText = "";
		document.getElementById("contentDiv").innerText = "";
		
		if(document.boardWriteForm.subject.value=="") {
			document.getElementById("subjectDiv").innerText="Enter the Subject";
			document.boardWriteForm.subject.focus();
			// focus를 맞춰서 커서를 입력칸에 두어 입력이 바로 가능하도록 함
			return;} // return: 함수 종료

		else if(document.boardWriteForm.content.value=="") {
			document.getElementById("contentDiv").innerText="Enter the Content";
			document.getElementById("content").focus();
			// contentDiv focus가 아니라 content에 focus
			return;
		}
	 	
	 	else {document.boardWriteForm.submit();} // submit -> action
	 	
	} // check()
	
</script>

</body>
</html>