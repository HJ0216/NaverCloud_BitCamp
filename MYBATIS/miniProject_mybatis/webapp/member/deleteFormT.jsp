<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="member.dao.MemberDAO" %>

<%

	// Data
	// deleteForm으로부터 받은 Data
	String pwd = request.getParameter("pwd");
	System.out.println("pwd: " + pwd);
	// 로그인 시 발생하는 Session 받아오기
	String id = (String) session.getAttribute("memId");
	String memPwd = (String) session.getAttribute("memPwd");
	
	
	boolean exist = false; // 검색 버튼을 누르지 않음
	
	if(pwd!=null){
	// session을 이용하지 않고 getParam을 사용할 경우
	// index.jsp에서 Delete account로 넘어갈 때, param을 넘기지 않으므로 request.getParameter("pwd") == null
	// 이후 재귀를 통한 pwd에 value 입력

	// deleteFormT로 넘어오면 Data 부분이 자동으로 인식되므로
	// null이 DAO로 넘어가는 문제 발생 > not null 조건 추가
		
	
		// memberDAO.isExistPwd(map)를 활용하여 계정 비밀번호 확인
		// Map<String, String> map = new HashMap<>();
		// map.put("id", id);
		// map.put("pwd", pwd);
		// MemberDAO memberDAO = MemberDAO.getInstance();
		// exist = memberDAO.isExistPwd(map);
		// return 값이 true or false일 경우 함수명이 is로 시작
		// pk인 id 추가(비밀번호 중복 방지)
		
		
		// session을 활용한 계정 비밀번호 확인
		if(memPwd.equals(pwd)) {exist = true;}
		
	}
	
	if(exist) {response.sendRedirect("delete.jsp");}
	// 비밀번호가 맞은 경우
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteFormT page here</title>

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
		<!-- js function이 작동하는지 alert로 확인 -->
		<br/><br/>
		<div id="pwdDiv"></div>
		
		<%if(pwd != null && !exist) {%>
		<!-- 비밀번호가 입력이 되고 검색이 된 상태
			 + exist==false -->
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