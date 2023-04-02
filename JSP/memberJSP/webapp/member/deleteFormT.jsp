<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="member.dao.MemberDAO" %>

<%

	//Data
	String pwd = request.getParameter("pwd"); // deleteForm으로부터 받은 Data
	System.out.println("pwd: " + pwd); // 시작 pwd: null
	
	// 로그인 시 발생하는 Session 받아오기
	String id = (String) session.getAttribute("memId"); // 세션을 통해서 id 값 받아오기
	String memPwd = (String) session.getAttribute("memPwd"); // 세션을 통해서 pw도 받아온 경우
	
	boolean exist = false; // 검색 버튼을 누르지 않음
	
	if(pwd!=null){
	// 처음 pwd가 null이므로 DB로 넘어가지 못함
	// deleteFormT로 넘어오면 Data 부분이 자동으로 읽힘
	// getParam이 되지 않는 pwd는 null
	// null이 DAO로 넘어가는 문제 발생 > not null 조건 추가
		
	
		// 로그인 시, pwd를 DB와 비교한 경우,
//		MemberDAO memberDAO = MemberDAO.getInstance();
//		exist = memberDAO.isExistPwd(id, pwd);
		// return 값이 true or false일 경우 함수명이 is로 시작
		// pk인 id 추가(비밀번호 중복 방지)
		
		
		// 로그인 시, session에 비밀번호를 저장한 경우,
		if(memPwd.equals(pwd)) {exist = true;}
		
	}
	
	if(exist) {response.sendRedirect("delete.jsp");}
	// 비밀번호가 맞은 경우
	
	
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- div 중 pwdDiv -->
<style type="text/css">
div#pwdDiv {
	color: red;
	font-weight: bold;
}

</style>

</head>
<body>
<form name="deleteForm" method="post" action="deleteFormT.jsp">
<!-- deleteFormT: 재귀 -->

	<div style="text-align: center;">
		비밀번호 입력 : <input type="password" name="pwd" id="pwd">
		<input type="button" value="검색" onclick="checkDelete()">
		<!-- js function이 작동하는지 확인 -->
		<br/><br/>
		<div id="pwdDiv"></div>
		
		<%if(pwd != null && !exist) {%>
		<!-- 비밀번호가 입력이 되고 검색이 된 상태 + select된 데이터가 없음 -->
			비밀번호가 맞지 않습니다.
		<% } %>
		
		
		
	</div>

</form>
<script type="text/javascript">

function checkDelete(){
	document.getElementById("pwdDiv").innerText = "";
	
	if(document.getElementById("pwd").value==""){ // form이 없으므로 id value를 직접 받기
		document.getElementById("pwdDiv").innerText = "비밀번호 입력";
	}
	
	else {document.deleteForm.submit();}
}
</script>

</body>
</html>