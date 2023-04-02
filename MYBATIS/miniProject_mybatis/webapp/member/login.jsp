<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="member.dao.MemberDAO"%>
<%@ page import="member.bean.MemberDTO"%>

<%
request.setCharacterEncoding("UTF-8");
// loginForm에서 한글 data를 받아오기 위한 Encoding

// data (String param)
// getParameter("name attribute") *id attribute가 아님
String id  = request.getParameter("id");
String pwd = request.getParameter("pwd");

Map<String, String> map = new HashMap<>();
// login.jsp or MemberDAO에서 map 처리 모두 가능
map.put("id", id);
map.put("pwd", pwd);

// DB
MemberDAO memberDAO = MemberDAO.getInstance();
MemberDTO memberDTO_login = memberDAO.memberLogin(map);
String name = null;
if(memberDTO_login!=null) {
	name = memberDTO_login.getName();
}

// for session setting
MemberDTO memberDTO_call = memberDAO.memberCall(id);
String email = null;

if(memberDTO_call!=null) {
	email = memberDTO_call.getEmail1() + "@" + memberDTO_call.getEmail2();
}

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