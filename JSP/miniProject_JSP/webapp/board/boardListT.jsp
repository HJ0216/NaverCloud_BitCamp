<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page 
		 import="board.dao.BoardDAO"
		 import="board.bean.BoardDTO"
		 import="board.bean.BoardPaging"
		 
		 import="java.util.HashMap"
		 import="java.util.Map"
		 import="java.util.ArrayList"
		 import="java.util.List"
		 
		 import="java.text.SimpleDateFormat"
%>

<%
response.setContentType("text/html; charset=UTF-8");

String id = (String) session.getAttribute("memId");	
// login.jsp에서 session을 넘겨받음

// Paging
int pg = Integer.valueOf(request.getParameter("pg"));
// index.jsp에서 url query로 param을 넘겨받음

int endNum = pg*5;
int startNum = endNum-4;

Map<String, Integer> map = new HashMap<>();
map.put("startNum", startNum); // key, value
map.put("endNum", endNum);

BoardDAO boardDAO = BoardDAO.getInstance();

List<BoardDTO> listT = boardDAO.boardListT(map);
List<BoardDTO> list = boardDAO.boardList(startNum, endNum);

// 다형성 List<BoardDTO> list = boardDAO.boardList();
// 변수 type을 상위의 class로 지정 시, 코드의 유연성을 높일 수 있음


// 총 글수, 총 페이지수
int totalA = boardDAO.getTotalA();
int totalP = (int) Math.ceil(totalA/5.0);
// totalA/5 기재 시, int값이 먼저 처리되므로 5.0으로 실수화 먼저 진행


// 페이징 처리(Using Class)
BoardPaging boardPaging = new BoardPaging();
boardPaging.setCurrentPage(pg);
boardPaging.setPageBlock(3);
boardPaging.setPageSize(5);
boardPaging.setTotalA(totalA);

boardPaging.makePagingHTML(); // HTML code create

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List here</title>


<style type="text/css">

/*
id(attribute가 1번만 사용되는 경우): #
class(attribute가 여러번 사용되는 경우): .
*/

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

<!-- 
1. Data
목록으로 넘어올 때, 전달받을 데이터 X

2. DB: BoardDAO.java와 연동
SELECT * FROM BOARD ORDER BY SEQ DESC;
작성된 글이 여러개이므로 ArrayList<BoardDTO>에 담아오기
글번호, 제목, 작성자(ID), 조회수(hit), 작성일(YYYY.MM.DD) 불러오기

3. Response
return boardList
 -->
 
<div class="titleBox">

<img src="../image/android.png" width="50" height="50" alt="안드로이드"
onclick="location.href='http://localhost:8080/miniProject_JSP/index.jsp'" style="cursor: pointer;">
목록
</div>
 
<%if(list != null) { %>
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

	<% for(BoardDTO data : list){%>
		<tr>
			<td align="center"><%=data.getSeq() %></td>
			<td><a class="subjectA" href="#" onclick="isLogin('<%=id%>', <%=data.getSeq()%>, <%=pg %>)"><%=data.getSubject() %></a></td>
			<!-- subject align="center" 적용 X, 일반적으로 왼쪽 정렬함 -->
			<!-- id: attribute가 1번만 사용되는 경우
				 class: attribute가 여러번 사용되는 경우(현재 for문을 통해서 a tag가 여러번 사용됨) -->
			<!-- return checkLogin(): return value가 true 일 때만 돌아와서 href= 수행
									  				 false일 경우, js function 수행 후 해당 tag 종료 -->	 
			<!-- 태그가 태그를 감싸고 있을 때, 상속관계로 칭함
			     <a>의 부모: <td> -->
			<td align="center"><%=data.getId() %></td>
			<td align="center"><%=data.getHit() %></td>
			<td align="center">
				<%=new SimpleDateFormat("yyyy.MM.dd.").format(data.getLogtime()) %></td>
				<!-- java code에서 date 출력 형식 변경,
					 변수를 지정하지 않았으므로 일회용으로만 사용 가능 -->
		</tr>
		
	<%} // for: return list %>

<%} // if: not null %>

	</table>
</div>


<br/>
<br/>

<div class="pagingBox">

<!-- currentPage를 구분하여 다른 style 적용 -->
<% for(int i=1; i<=totalP; i++) {
		if(i==pg){%> 
			<div id='currentPagingDiv'><a id='currentPaging' href="./boardListT.jsp?pg=<%=i %>"> <%=i %> </a></div>
			<!-- query 넘기는 부분 ' ' 공백없도록 유의 -->
	 <% } else { %>
			<div id='PagingDiv'><a id='paging' href="./boardListT.jsp?pg=<%=i %>"> <%=i %> </a></div>
	 <% } %>
<% } %>

</div>


<div style="border: 1px solid black; margin-top: 15px; text-align: center;">
<!-- style width: 1000px; div의 총 길이를 지정하므로 align 기준이 달라짐 -->
	<%=boardPaging.getPagingHTML() %>
</div>



<script type="text/javascript">
function checkLogin() {	
	if(<%=id %>==null){
	// session id를 받아와서 login 여부 확인
	// java 변수 선언시 < %= % > 작성 필수
		alert("로그인이 필요합니다.");
		var session = false;
	} else {
		var session = true;
	}
	return session;
}

function isLogin(id, seq, pg) {
	if(id=='null') {alert("먼저 로그인하세요");}
	/* "isLogin('<%=id%>')"
	// 로그인하지 않았을 경우, null
	// ''로 문자화
	// 최종적으로 'null'과 비교 */
	else {location.href="boardViewT.jsp?seq=" + seq + "&pg=" + pg;}	
}

function boardPaging(pg){
	location.href="boardListT.jsp?pg=" + pg;
}

</script>

</body>
</html>