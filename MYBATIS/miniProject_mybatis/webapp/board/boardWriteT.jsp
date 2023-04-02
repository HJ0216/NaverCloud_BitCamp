<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page 
		 import="board.dao.BoardDAO"
		 import="board.bean.BoardDTO"
		 import="java.util.HashMap"
		 import="java.util.Map"
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
	
	
	// 2. DB: board.dao.BoardDAO, board.dao.BoardDTO
	// 회원가입 DAO/DTO, 방명록 DAO/DTO를 구분해서 만들어 줘야 함

	/*
	using boardDTO

	BoardDTO boardDTO = new BoardDTO();
	boardDTO.setId(name);
	boardDTO.setName(name);
	boardDTO.setEmail(name);
	boardDTO.setSubject(name);
	boardDTO.setContent(name);

	BoardDAO boardDAO = BoardDAO.getInstance();
	boardDAO.boardWrite(boardDTO);
	*/
	
	
	// boardDTO 대신 map에 전달받은 5개의 데이터를 저장
	Map<String, String> map = new HashMap<>();
	map.put("id", id);
	// put("이름", value);
	// value 대신 key 값을 사용함으로서 코드 사용의 편리함을 높일 수 있음
	map.put("name", name);
	map.put("email", email);
	map.put("subject", subject);
	map.put("content", content);
	
	BoardDAO boardDAO = BoardDAO.getInstance();
	boardDAO.boardWriteT(map);
	
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>


<!-- using map<> -->
<script type="text/javascript">
window.onload=function(){
	alert("글작성 완료");
	location.href="./boardListT.jsp?pg=1";
}
</script>


</body>
</html>