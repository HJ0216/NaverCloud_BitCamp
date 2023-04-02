<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page 
		 import="board.dao.BoardDAO"
		 import="board.bean.BoardDTO"
%>    
    
<%
// 1. DATA: Subj, Cont

	request.setCharacterEncoding("UTF-8");
	// post method 사용 시,
	// boardWriteForm으로부터 request로 받아오는 데이터 중 한글 인식을 위한 encoding 필요

	// getParameter("name attribute")
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");


	// session
	// getParam - 이전 페이지에서만 넘겨받을 수 있음
	// session - session.setAttibute 시 어느 파일에서나 사용 가능
	
	String name  = null;
	String id    = null;
	String email = null;
	
	// JSP 내장객체 사용(HttpSession session = request.getSession(); 선언 필요 X)
	name  = (String) session.getAttribute("memName");
	id    = (String) session.getAttribute("memId");
	email = (String) session.getAttribute("memEmail");

%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

<%
// 2. DB: board.dao.BoardDAO, board.dao.BoardDTO
// 회원가입 DAO/DTO, 방명록 DAO/DTO를 구분해서 만들어 줘야 함

BoardDTO boardDTO = new BoardDTO();
boardDTO.setId(name);
boardDTO.setName(name);
boardDTO.setEmail(name);
boardDTO.setSubject(name);
boardDTO.setContent(name);

BoardDAO boardDAO = BoardDAO.getInstance();
int rowInsert = boardDAO.boardWrite(boardDTO);
%>


<%
// 3. Response: alert("Complete")
// : 메인화면으로 이동

if(rowInsert==0){ %>
<h3>글 저장 실패</h3>
<input type='button' value='뒤로' onclick='history.go(-1)'>
<%} else { %>
<script>
window.onload=function(){
alert("글 저장 성공");
location.href="./boardList.jsp?pg=1";
}
</script>
<%} %>

</body>
</html>