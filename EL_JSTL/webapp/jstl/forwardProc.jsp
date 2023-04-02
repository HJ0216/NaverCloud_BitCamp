<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- import 알파벳 순으로 배치 -->
<%@page import="com.jstl.PersonDTO"%>    
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<% // java 역할
// For test
List<String> list = new ArrayList<>();
list.add("호랑이");
list.add("사자");
list.add("기린");
list.add("코끼리");
list.add("타조");
list.add("코알라");
list.add("여우");

request.setAttribute("list", list);
// jstlStart: request.setAttribute("list", list) != jstlEnd: requestScope.list

// forward 방법을 통한 변수값 공유
// 1. jsp tag 사용(사용빈도 낮음)
// 2. 변수값 공유를 위한 dispatcher 선언




//For PersonDTO
PersonDTO aa = new PersonDTO("홍길동", 25);
//aa: className@16진수_주소값을 통해 객체 주소 반환
PersonDTO bb = new PersonDTO("라이언", 23);
PersonDTO cc = new PersonDTO("프로도", 30);

List<PersonDTO> list2 = new ArrayList<>();
//list2: className@16진수_주소값을 통해 객체 주소 반환
list2.add(aa);
list2.add(bb);
list2.add(cc);
//DTO: set, Map: put, List: add

request.setAttribute("list2", list2);
// url 사용 시, list2의 객체 주소를 넘기는 것이 아니라 String처리 됨



RequestDispatcher dispatcher = request.getRequestDispatcher("forwardResult.jsp");
// interface new 사용 불가 > 함수를 통한 객체 구현
// getRequestDispatcher("합병할 jsp 파일의 상대번지")
// 합병을 통해서 request, response value 공유
dispatcher.forward(request, response); // 제어권 넘기기


%>

<!--
1. jsp tag
<jsp:forward page="forwardResult.jsp"/>
: jsp tag를 잘 사용하지 않음
-->
