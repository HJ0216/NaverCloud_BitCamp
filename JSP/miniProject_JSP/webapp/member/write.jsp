<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="member.dao.MemberDAO"
		 import="member.bean.MemberDTO" %>
		 

    
<%  // 스크립트릿: 지역변수, 호출 시 마다 실행
	// Data
	request.setCharacterEncoding("UTF-8");

	String name = request.getParameter("name");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String gender = request.getParameter("gender");
	String email1 = request.getParameter("email1");
	String email2 = request.getParameter("email2");
	String tel1 = request.getParameter("tel1");
	String tel2 = request.getParameter("tel2");
	String tel3 = request.getParameter("tel3");
	String zipcode = request.getParameter("zipcode");
	String addr1 = request.getParameter("addr1");
	String addr2 = request.getParameter("addr2");

%>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>

<%
// OracleDB와 연동: lib에 .jar 저장

// DB
MemberDTO memberDTO = new MemberDTO();
memberDTO.setName(name);
memberDTO.setId(id);
memberDTO.setPwd(pwd);
memberDTO.setGender(gender);
memberDTO.setEmail1(email1);
memberDTO.setEmail2(email2);
memberDTO.setTel1(tel1);
memberDTO.setTel2(tel2);
memberDTO.setTel3(tel3);
memberDTO.setZipcode(zipcode);
memberDTO.setAddr1(addr1);
memberDTO.setAddr2(addr2);

MemberDAO memberDAO = MemberDAO.getInstance();
int row = memberDAO.memberWrite(memberDTO);

%>

<% if(row==0){ %>
<h3>회원가입 실패</h3>
<input type='button' value='뒤로' onclick='history.go(-1)'>
<%} else { %>
<h3>회원가입 성공</h3>
<%} %>
<input type='button' value='로그인' onclick=location.href='loginForm.jsp'>


</body>
</html>