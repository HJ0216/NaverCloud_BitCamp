<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@
	page import="member.dao.MemberDAO" 
		 import="member.bean.MemberDTO"
%>

<%
request.setCharacterEncoding("UTF-8");
// loginForm에서 한글 data를 받아오기 위한 Encoding

// data (String param)
// getParameter("name attribute") *id attribute가 아님
String id  = request.getParameter("id");
String pwd = request.getParameter("pwd");

// DB
MemberDAO memberDAO = MemberDAO.getInstance();
String name = memberDAO.memberLogin(id, pwd);

// for session setting
MemberDTO memberDTO = memberDAO.memberCall(id);
String email = null;

if(memberDTO!=null) {
	email = memberDTO.getEmail1() + "@" + memberDTO.getEmail2();
}
// data 부분은 login.jsp로 넘어올 경우, 자동으로 실행됨
// 만일 id 조차 없는 값을 memberCall에 입력할 경우, memberDTO가 null이 return
// null값에 대한 getEmail을 할 경우, NullPointException 발생
// exception에 대한 처리

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login here</title>
</head>
<body>

<!-- 
	1. url query parameter를 통한 로그인 정보 전달(loginForm.jsp 참조)
	input type="hidden"으로 해서 query 값 숨기기 활용
	단, url에서는 보이지 않으나 F12 소스보기에서는 보이는 문제 발생
	cookie or session 이용
--> 

<!-- login 성공 여부에 따라 Fail과 Okay 구분 -->
<% if(name==null) {
	
	// 페이지 이동
	response.sendRedirect("loginFail.jsp");
	
} else {
	/*

	// 2. Cookie를 활용한 로그인 정보 전달
	
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
	
	
	// 3. Session(Server 보관)값 설정을 통한 로그인 정보 전달
	// HttpSession session = request.getSession();
	// HttpSession은 Interface이므로 new를 통한 객체 생성 불가
	// JSP에 내장 객체로 session이 만들어져 있음


	session.setAttribute("memName", name);
	// setAttribue(이름, 값)
	// loginForm에서 받아온 name value를 session value에 저장
	// session은 유효시간을 지정하지 않아도 30분이 기본
	session.setAttribute("memId", id);
	session.setAttribute("memPwd", pwd);
	session.setAttribute("memEmail", email);

	// 페이지 이동
	response.sendRedirect("loginOkay.jsp");
	
} %>


</body>
</html>