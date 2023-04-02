<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Directive <@ page @> <@ include @> <@ taglib @> 
     servlet: response.setContentType("text/html; charset=UTF-8");
   = jsp: contentType="text/html; charset=UTF-8" -->

<%!
	// 선언문: 전역변수, init(), 실행 시 1번만 처리
	String name = "홍길동";
	int age = 25;
%>

<%
	// 스크립트릿: 지역변수, 호출 시 마다 실행
	age++;

%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- http://localhost:8080/testJSP/exam/hello.jsp -->

<!-- 안녕하세요, JSP :) <br/>
	 html 주석: web browser로 보내짐 -->
<%-- Hello JSP :) <br/>
	 JSP 주석: web browser로 보내지지 않음 --%>
나의 이름은 <%=name %> 입니다. <br/>
<% out.println("Name: " + name); %>

<!-- 내 나이는 <%=age %> 입니다. <br/>
	 web browser로 넘어가므로 새로 고침 시, 나이가 증가함-->
<%-- 내 나이는 <%=age %> 입니다. <br/> --%>

</body>
</html>