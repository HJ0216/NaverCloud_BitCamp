<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp here</title>
<style type="text/css">
/*userPaging에서 span tag에 적용*/

#currentPaging {
	color: yellowgreen;
	text-decoration: underline;
	border: 1px solid darkgreen;
	padding: 5px 8px; /* (top, bottom), (left, right) */
	margin: 5px; /* top, right, bottom, left */
	cursor: pointer;
}
#paging {
	color: black;
	text-decoration: none;
	padding: 5px; /* padding: 안쪽 여백 */ 	
	margin: 5px; /* margin: 바깥쪽 여백 */ 
	cursor: pointer;
}
</style>
</head>
<body>

<h3>
	<a href="/"><img src="/img/spongebob.png" width="100" height="100"></a>
	List
</h3>

<form id="listTable">
<input type="text" id="pg" value="${pg }" >

<%-- pg값을 list.js에서 사용하기 위해 id 설정 --%>
<table id="userListTable" border="1" frame="hsides" rules="rows"> <!-- 가로 선만 출력 -->
	<tr>
		<th width="150">Name</th>
		<th width="150">Id</th>
		<th width="150">Pwd</th>
	</tr>
	
</table>

</form>

<div id="userPagingDiv" style="margin-top: 30px; width: 450px; text-align: center;">
</div>


<!-- CDM 방식: js파일에 대한 서버로의 직접 접근 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="../js/list.js"></script>
<%-- /: DispatcherServlet 호출이 아님을 mvc:resources 선언 --%>


<script type="text/javascript">
function userPaging(pg) {
		location.href = "/user/list?pg=" + pg;
}
// userPaging에서 동적으로 들어오는 데이터에서 onclick method name: userPaging
</script>

</body>
</html>