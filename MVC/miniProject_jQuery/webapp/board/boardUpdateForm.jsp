<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- boardViewT.jsp 참조 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardUpdateForm here</title>

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

<form id="boardUpdateForm" action="">

<%--  boardUpdateForm.jsp에 담아줘야 boardUpdate.js에서도 사용 가능
seq, pg: From BoardUpdateFormService.java --%>
<input type="text" id="seq" name="seq" value="${seq }" >
<input type="text" id="pg" name="pg" value="${pg }" >

	<table id="boardUpdateTable" border="1" cellpadding="5" cellspacing="0">
		<tr>
			<th width="70">Subject</th>
			<td>
				<input type="text" name="subject" id="subject" placeholder="Enter the subject">
				<div id="subjectDiv"></div>
			</td>
		</tr>
		
	
		<tr>
			<th width="70">Contents</th>
			<td>
				<textarea name="content" id="content" cols="50" rows="10"  placeholder="Enter the Content"></textarea>
				<!-- <textarea></textarea>는 태그 사이의 띄어쓰기 or 개행을 입력값으로 받으므로 붙여쓰기 -->
				<div id="contentDiv"></div>
			</td>
		</tr>
		
	
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="Complete" id="boardReWriteBtn">
				<!-- onclick: js() 사용 시, submit에 따른 action을 false 처리해줘야 하므로 id 지정 후 ajax 처리 -->
				<input type="button" value="ReWrite" id="boardResetBtn">
			</td>
		</tr>
			
	</table>

<%-- 
1. baordViewService에서 넘겨받은 pg값 활용 el tag: ${pg }
2. input type 활용 $('#pg').val()
 --%>
</form>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="../js/boardUpdate.js"></script>


</body>
</html>