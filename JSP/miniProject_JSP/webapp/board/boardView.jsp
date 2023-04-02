<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page 
		 import="board.dao.BoardDAO"
		 import="board.bean.BoardDTO"
		 import="board.bean.BoardPaging"
		 
		 import="java.util.ArrayList"
		 import="java.util.List"
		 
		 import="java.text.SimpleDateFormat"
%>    


<%
	response.setContentType("text/html; charset=UTF-8");
	int seq = Integer.valueOf(request.getParameter("seq"));
	
	String id = (String) session.getAttribute("memId");	
	
	BoardDAO boardDAO = BoardDAO.getInstance();
	BoardDTO boardDTO = boardDAO.boardCall(seq);
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardView here</title>

<style type="text/css">

div {
	color: red;
	font-size: 8pixel;
	font-weight: bold;
}

</style>

</head>
<body>

<form name="boardViewForm" method="post" action="boardList.jsp"> <!-- boardWriteForm.jsp 참조 -->

<h3>
	<img src="../image/xbox.jfif" width="50" height="50" alt="엑스박스"
	onclick="location.href='http://localhost:8080/miniProject_JSP/index.jsp'" style="cursor: pointer;">
	작성한 글 확인
</h3>

<table border="1" cellpadding="5" cellspacing="0">

	<tr>
		<th width="70">Subject</th>
		<td><input type="text" name="subject" id="subject" value="<%=boardDTO.getSubject()%>" readonly>
		<div id="subjectDiv"></div>
		</td>
	</tr>
	

	<tr>
		<th width="70">Contents</th>
		<td><textarea name="content" id="content" cols="50" rows="10" readonly><%=boardDTO.getContent()%></textarea>
		<!-- <textarea></textarea>는 태그 사이의 띄어쓰기 or 개행을 입력값으로 받으므로 붙여쓰기 -->
		<div id="contentDiv"></div>
		</td>
	</tr>
	

	<tr>
		<td colspan="2" align="center">
			<input type="button" value="목록" onclick="history.go(-1)">
		</td>
	</tr>

	
</table>

</form>

<script type="text/javascript">

	
</script>

</body>
</html>