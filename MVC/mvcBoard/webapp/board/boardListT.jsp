<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List here</title>

<style type="text/css">
.subjectA:link 		{color: black; 	 text-decoaration: none;} /* 한 번도 클릭하지 않았을 경우 */
.subjectA:visited 	{color: hotpink; text-decoaration: none;} /* 마우스 클릭 후 */
.subjectA:hover 	{color: black; 	 text-decoaration: none;} /* 마우스를 올렸을 때 */
.subjectA:active 	{color: black; 	 text-decoaration: none;} /* 마우스를 누르고 있을 때 */

#currentPagingDiv {float: left;  border: 1px red solid; color: black;  width: 20px; height: 20px; margin-left: 5px; text-align: center;}
#PagingDiv 		  {float: left;  border: 1px red solid; color: yellow; width: 20px; height: 20px; margin-left: 5px; text-align: center;}
#currentPaging    {color: red;   text-decoration: none;}
#paging 		  {color: black; text-decoration: none;}

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


 
<div class="titleBox">

<img src="../image/android.png" width="50" height="50" alt="안드로이드"
onclick="location.href='../index.jsp'" style="cursor: pointer;">
목록
</div>

<div class="listingBox">

<table border="1" cellpadding="5" cellspacing="0" frame="hsides" rules="rows">
<!-- th: 표 제목, tr: 행, td: 열 -->

	<tr>
		<th width="100">글번호</th>
		<th width="100">제목</th>
		<th width="100">작성자</th>
		<th width="100">조회수(hit)</th>
		<th width="100">작성일(YYYY.MM.DD)</th>
	</tr>

<c:if test="${requestScope.listT != null }">
<!-- requestScope 생략 가능, 생략 시, 현재 페이지의 pageScope에 해당 변수가 없을 경우, request.setAttribute로 넘어가서 탐색 -->
	<c:forEach var="boardDTO" items="${listT }">
	<!-- var 작성 시, 자료형 생략 가능,
		 items의 requestScope 생략 가능 -->
		<tr>
			<td align="center">${boardDTO.seq }</td>
			<td><a class="subjectA" href="#" onclick="isLogin('${boardDTO.id }', ${boardDTO.seq }, ${pg })">${boardDTO.subject }</a></td>
			<!-- boardDTO.getSeq() > board.seq: 메서드도 변수명처럼 사용할 수 있음  -->
			<!-- return checkLogin(): return value가 true 일 때만 돌아와서 href= 수행
									  				 false일 경우, js function 수행 후 해당 tag 종료 -->	 
			<td align="center">${boardDTO.id }</td>
			<td align="center">${boardDTO.hit }</td>
			<td align="center"><fmt:formatDate value="${boardDTO.logtime }" pattern="yyyy.MM.dd"/>
			</td>
		</tr>
	</c:forEach>
</c:if>
</table>
</div>

<br/>
<br/>

<div class="pagingBox">

<c:forEach var="i" begin="1" end="${totalP }">
	<c:if test="${i } eq ${pg }">
			<div id='currentPagingDiv'><a id='currentPaging' href="/mvcBoard/board/boardList.do?pg=${i }"> ${i } </a></div>
	</c:if>
	<c:if test="${i } ne ${pg }">
			<div id='PagingDiv'><a id='paging' href="/mvcBoard/board/boardList.do?pg=${i }"> ${i } </a></div>
	</c:if>		
</c:forEach>
</div>


<div style="margin-top: 15px; text-align: center;">
<!-- style width: 1000px; div의 총 길이를 지정하므로 align 기준이 달라짐 -->
	${boardPaging.getPagingHTML() }
	<!-- boardPaging.getPagingHTML() > boardPaging.pagingHTML
		 메서드를 변수화해서 사용 가능 -->
</div>





<script type="text/javascript" method="get" action="/mvcBoard/board/boardList.do">
/*
function checkLogin() {	
	if(${data.id }==null){
		alert("로그인이 필요합니다.");
		var session = false;
	} else {
		var session = true;
	}
	return session;
}
*/

function isLogin(id, seq, pg) {
	if(id=='null') {alert("먼저 로그인하세요");}
	else {location.href="boardViewT.jsp?seq=" + seq + "&pg=" + pg;}	
}

function boardPaging(pg){
	location.href="/mvcBoard/board/boardList.do?pg=" + pg;
}

</script>

</body>
</html>