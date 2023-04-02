<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page
   import="board.dao.BoardDAO"
   import="board.bean.BoardDTO"   
%>

<%
// From QueryParam
int seq = Integer.valueOf(request.getParameter("seq"));
int pg = Integer.valueOf(request.getParameter("pg"));
// casting: Integer.parseInt 시 NumberforamtException 발생으로 Integer.valueOf로 변경

// From DB
BoardDAO boardDAO = BoardDAO.getInstance();
BoardDTO boardDTO = boardDAO.boardCall(seq);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

</style>

</head>
<body>
<% if(boardDTO != null) { %>
<h3>
	<img src="../image/xbox.jfif" width="50" height="50" alt="엑스박스"
	onclick="location.href='../index.jsp'" style="cursor: pointer;">
	작성한 글 확인
</h3>

<table border="1" cellpadding="5" cellspacing="0">

	<tr>
		<th><h3>제목</h3></th>
			<td colspan="5"><h3><%=boardDTO.getSubject()%></h3>
		</td>
	</tr>
	
	
	<tr>
		<th>글번호</th>
		<td><%=boardDTO.getSeq()%></td>
		<th>작성자</th>
		<td><%=boardDTO.getName()%></td>
		<th>조회수</th>
		<td><%=boardDTO.getHit()%></td>
	</tr>
	

	<tr>
		<th>내용</th>
		<td colspan="5" valign="top" width="400px" height="500" style="word-break:break-all;">
		<%=boardDTO.getContent().replaceAll(" ", "&nbsp;")
								.replaceAll("\n", "<br/>")%>
		<!-- contents를 html에 입력하면서 특수문자를 html 용어로 변경 -->
		</td>
	</tr>
	

	<tr>
		<td colspan="6" align="center">
			<input type="button" value="글수정" onclick="">
			<input type="button" value="목록" onclick="location.href='boardListT.jsp?pg=<%=pg%>'">
			<!-- boardList로부터 넘겨받은 param을 활용하여 목록으로 돌아갈 pg에 활용 -->
		</td>
	</tr>

</table>


<table width="300" border="1" frame="hsides" rules="rows">
<!-- frame="hsides" rules="rows" 행만 border 표시 -->
<!-- white-space 사용 시, table에 width가 잡혀있어야 함 -->
	<tr>
		<td colspan="3">
			<h2><%=boardDTO.getSubject() %></h2>
		</td>
	</tr>

	<tr>
		<td width="150" align="center">글 번호: <%=boardDTO.getSeq() %></td>
		<td width="150" align="center">작성자: <%=boardDTO.getId() %></td>
		<td width="150" align="center">조회수: <%=boardDTO.getHit() %></td>
	</tr>
	
	<tr>
		<td colspan="3" height="200" valign="top">
			<div style="width: 100%; height: 100%; overflow: scroll;">
			<!-- div영역이 td영역에 맞춰짐 -->
				<pre style="white-space: pre-line; word-break: break-all;"><%=boardDTO.getContent() %></pre>
				<!-- white-space 사용 시, table에 width가 잡혀있어야 함 -->
			</div>
		</td>
	</tr>

</table>
<input type="button" value="목록" onclick="history.go(-1)">


<%} %>
</body>
</html>