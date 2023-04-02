<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="member.dao.MemberDAO" %>    
    
<%
request.setCharacterEncoding("UTF-8");

// From Session sata
String id = request.getParameter("id");
//getParameter("name attribute") *id attribute가 아님

// From DB data
boolean existId = false;
MemberDAO memberDAO = MemberDAO.getInstance();
existId = memberDAO.isExistId(id);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CheckId here</title>
</head>
<body>
<%if(existId) { %>
	<form action="checkId.jsp"> <!-- 재귀 -->
		<h3><%=id %> 사용 불가능</h3>
		<br>
		아이디<input type="text" name="id"> <input type="submit" value="중복체크">
	</form>
<%} else {%>
	<h3><%=id %> 사용 가능</h3>
	<input type="button" value="사용하기" onclick="checkIdClose('<%=id%>')">
	<!--
		submit+action으로 이어지므로 type은 submit이 아닌 button 사용
		 js function 사용 시, 먼저 alert로 함수 작동 여부 확인해보기
	-->
		 
<% } %>


<script type="text/javascript">
function checkIdClose(id) {
	// document: 해당 .jsp 파일을 의미하므로 document.writeForm은 checkId.jsp에서만 form을 찾게됨
	// opener: 해당 .jsp 파일을 불러온 부모 document를 추가 참조
	
	opener.writeForm.id.value = id;
	opener.writeForm.idDuplication.value = id;
	// idDup value도 전달하여 중복 확인 검사
	// 문자 입력 시, "" 사용 필수
	// checkId() 매개변수에서 이미 문자 처리했으므로 js에서는 따로 처리 X
	window.close();
	opener.writeForm.pwd.focus();
	
}
</script>


</body>
</html>