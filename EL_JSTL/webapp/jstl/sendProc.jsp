<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%
// java 역할
List<String> list = new ArrayList<>();
list.add("호랑이");
list.add("사자");
list.add("기린");
list.add("코끼리");
list.add("타조");
list.add("코알라");
list.add("여우");

// 페이지 이동(jstlStart.jsp -> jstlEnd.jsp)
response.sendRedirect("sendResult.jsp");
// jstlEnd.jsp?변수=값 형태로 list 객체를 query로 넘길 수 없음
// 출력 시, null값 ${}는 공란 반환
// 그러므로 request.setAttribute("list", list);로 객체를 전달

request.setAttribute("list", list);
// jstlStart에서 request에 대해 값을 저장(setAttribute)
// jstlEnd에서 reqeustScope로 받는 request 객체와는 다르므로 set한 데이터를 추출할 수 X
// jstlEnd와 다른 reqeust 객체이므로 forwarding을 통한 변수값 공유 필요

%>