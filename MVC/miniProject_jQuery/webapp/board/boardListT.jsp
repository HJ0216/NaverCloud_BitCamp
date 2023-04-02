<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardList here</title>

<style type="text/css">
.subjectA:link 		{color: black; 	 text-decoaration: none;} /* 한 번도 클릭하지 않았을 경우 */
.subjectA:visited 	{color: hotpink; text-decoaration: none;} /* 마우스 클릭 후 */
.subjectA:hover 	{color: black; 	 text-decoaration: none;} /* 마우스를 올렸을 때 */
.subjectA:active 	{color: black; 	 text-decoaration: none;} /* 마우스를 누르고 있을 때 */

#currentPagingDiv {float: left;  border: 1px red solid; color: black;  width: 20px; height: 20px; margin-left: 5px; text-align: center;}
#PagingDiv 		  {float: left;  border: 1px red solid; color: yellow; width: 20px; height: 20px; margin-left: 5px; text-align: center;}
#currentPaging    {color: red;   text-decoration: none;}
#paging 		  {color: black; text-decoration: none;}

#boardListTable th {font-size: 12pt;}
#boardListTable td {font-size: 10pt;}

.titleBox {
	display: table; margin-left: auto; margin-right: auto;
}
.listingBox {
	display: table; margin-left: auto; margin-right: auto;
}
.pagingBox {
	display: table; margin-left: auto; margin-right: auto;
} 
#currentPaging {
	color: red;
	text-decoration: none;
	border: 1px solid red;
	padding: 5px 8px; /* (top, bottom), (left, right) */
	margin: 5px; /* top, right, bottom, left */
	cursor: pointer;
}
#paging {
	color: black;
	text-decoration: none;
	/* border: 1px solid black; */
	padding: 5px; /* padding: 안쪽 여백 */ 	
	margin: 5px; /* margin: 바깥쪽 여백 */ 
	cursor: pointer;
}
 
</style>

</head>
<body>

<%-- 
1. boardListT.jsp에 연결된 boardList.js는 boardListT.jsp와 다른 파일
2. boardListT.jsp에서 변수를 따로 지정하여 값을 저장한 후에 연결된 boardList.js에서 사용 가능
 --%>
<input type="text" id="pg" value="${pg }" >
<%-- pg: From BoardListService.java, requestScope --%>
<input type="text" id="memId" value="${memId }" >
<%-- memId: From BoardListService.java, requestScope --%>
 
	<div class="titleBox">	
		<img src="../img/google.png" width="50" height="50" alt="구글_로고"
		onclick="location.href='../index.jsp'" style="cursor: pointer;">
		목록
	</div>

<div class="listingBox">

<table id="boardListTable" border="1" cellpadding="5" cellspacing="0" frame="hsides" rules="rows">
<%-- table id 부여: boardList.js에서 동적 데이터 할당 시 필요 --%>

	<tr>
		<th width="100">글번호</th>
		<th width="100">제목</th>
		<th width="100">작성자</th>
		<th width="100">조회수(hit)</th>
		<th width="100">작성일(YYYY.MM.DD)</th>
	</tr>

	</table>
</div>

<br/>
<br/>

<div id="boardPagingDiv" style="margin-top: 15px; width: 850px; text-align: center;">
<%-- div id 부여: boardList.js에서 데이터 입력 시, paging 위치가 나타나는 곳 --%>
</div>


<script type="text/javascript">
function boardPaging(pg) {
// boardPagingDiv에 boardList.js를 통해서 데이터가 동적으로 입력됨
// 그 중 <span id="paging" onclick="boardPaging(n)"> 
	location.href = "boardList.do?pg=" + pg;
	// location.href = "/miniProject_jQuery/board/boardList.do?pg=" + pg;	
}
</script>


<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="../js/boardList.js"></script>


</body>
</html>