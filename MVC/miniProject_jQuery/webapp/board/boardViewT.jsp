<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- boardListT.jsp 참조 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardViewT here</title>

<style type="text/css">

</style>

</head>
<body>

<form id="boardViewForm" action="">

<%--  boardViewT.jsp에 담아줘야 boardView.js에서도 사용 가능
seq, pg: From BoardViewService.java --%>
<input type="text" id="seq" name="seq" value="${requestScope.seq }" >
<input type="text" id="pg" name="pg" value="${pg }" >

<%-- From GetBoardService.java, 수정, 삭제 Btn에서 활용 --%>
<input type="text" id="memId" value="${memId }" >

<table id="boardViewTable" width="650" border="2" frame="hsides" rules="rows">
	<tr>
		<td colspan="3">
			<h2><span id="subjectSpan"></span></h2>
			<!-- span tag를 추가 > 동적 데이터 추가 -->
			<!-- 동적으로 데이터를 입력할 경우, 웹 브라우저에서 페이지 소스보기에서 동적 데이터가 보이지 않음 -->
		</td>
	</tr>
	
	<tr>
		<td width="150" align="center">글번호: <span id=seqSpan></span></td>
		<td width="150" align="center">작성자: <span id=idSpan></span></td>
		<td width="150" align="center">조회수: <span id=hitSpan></span></td>
	</tr>

	<tr>
		<td colspan="3" height="250" valign="top">
			<div style="width: 100%; height: 100%; overflow: auto;">
				<pre style="white-space: pre-line; word-break: break-all;">
					<span id="contentSpan"></span>
				</pre>
			</div>
		</td>
	</tr>

</table>

<input type="button" value="목록" onclick="location.href='/miniProject_jQuery/board/boardList.do?pg=${pg}'">
<%-- 
1. baordViewService에서 넘겨받은 pg값 활용 el tag: ${pg }
2. input type 활용 $('#pg').val()
 --%>
 
<span id="boardViewSpan" style="border: 1px solid red;">
	<input type="button" value="글수정" id="boardUpdateFormBtn" />
	<input type="button" value="글삭제" id="boardDeleteFormBtn" />
</span>

<input type="button" value="답글" id="boardReplyFormBtn" />
<%-- 로그인 여부와 관계없이 버튼이 항상 보여야 함 --%>

</form>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="../js/boardView.js"></script>
<!-- 데이터 처리는 boardView.js에서! -->


</body>
</html>