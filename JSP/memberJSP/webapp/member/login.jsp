<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="member.dao.MemberDAO" %>

<%
request.setCharacterEncoding("UTF-8");
// 로그인 시, 한글 ID 로그인 허용

// data (param data type: String)
// getParameter: name attribute (id attribute가 아님)
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

// DB
MemberDAO memberDAO = MemberDAO.getInstance();
String name = memberDAO.memberLogin(id, pwd);
// DAO.memberLogin에 id, pwd 전달 -> name return

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 
<form name="updateForm" method="post" action="http://localhost:8080/memberJSP/member/updateForm.jsp">
</form>

url: query String을 넘기고 input type="hidden"으로 해서 query 값 숨기기
단, F12 소스보기에서는 보임 --> 

<!-- login 성공 여부에 따라 Fail과 Okay 구분 -->
<% if(name==null) {
	// 페이지 이동
	response.sendRedirect("loginFail.jsp");
	
} else {
	/*

	// Cookie
	
	Cookie cookie = new Cookie("memName", name);
				    new Cookie("쿠키 이름", 쿠키 값)
	cookie.setMaxAge(30 * 60);
	response.addCookie(cookie);
	setMaxAge: 초 단위
	setMaxAge만큼의 시간동안 사용할 수 있는 쿠키를 client에게 response
	
	Cookie cookie2 = new Cookie("memId", id);
	cookie2.setMaxAge(30 * 60);
	response.addCookie(cookie2);

	*/
	
	
	// Session(Server 보관)
	// HttpSession session = request.getSession();
	// HttpSession은 Interface이므로 new를 통한 객체 생성 불가
	// JSP에 내장 객체로 Session이 만들어져 있으모 method를 통한 객체 생성 필요 X

	session.setAttribute("memName", name);
	// setAttribue(이름, 값)
	// loginForm에서 받아온 name value를 session value에 저장
	session.setAttribute("memId", id);
	// session은 유효시간을 지정하지 않아도 30분이 기본
	session.setAttribute("memPwd", pwd);

	// 페이지 이동
	response.sendRedirect("loginOkay.jsp");
	
} %>


</body>
</html>