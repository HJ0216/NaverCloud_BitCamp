<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index here</title>
<style type="text/css">
html, body {
   width: 100%;
   height: 100%;
}

html {
   overflow-y: scroll;
}

#wrap{
   width: 1100px; 
   margin: 0 auto; /*가운데 정렬*/
}

#header {
   height: 10%;
   text-align: center;
}

#container {
   margin: auto;
   width: 1100px;
   /*height: 500px; 생략 시, 유동적으로 footer 최적화*/
}

#container:after {
   content: '';
   display: block;
   clear: both;
   float: none;
}

#nav {
   margin-left: 10px;
   width: 25%; /*container의 25%*/
   height: 100%;
   float: left;
}

#section {
   width: 70%;
   height: 100%;
   float: left;
}

#footer {
   width: 1100px;
   height: 10%;
}

</style>
</head>
<body>

<div id="wrap">
	<div id="header">
		<h1>
		<img src="/miniProject_jQuery/img/dog.gif" width="250" height="150"
			 onclick="location.href='/miniProject_jQuery/index.jsp'"
			 style="cursor: pointer;">MVC_miniProject
			 <%-- location.href: 경로 작성법
			 * /miniproject_jQuery만 생략이 안되는 이유
			 /miniproject_jQuery/member가 함께 기억되므로 생략 시, folder name까지 같이 생략해야 함
			 --%>
		</h1>
		
		<jsp:include page="./main/menu.jsp" />
		
	</div>
	
	
	<div id="container">
	
		<div id="nav">
			<jsp:include page="./main/nav.jsp" />
		</div>
		
		
		<div id="section">
		<!-- 회원가입 버튼 클릭 시, 전환 -->
			<h3>
			<c:if test="${ empty display }">	
				<%-- display
				display 변수가 pageScope 내 선언되지 않았으므로,
				pageScope > requestScope > sessionScope > applicationScope 순으로 해당 변수를 탐색
				
				writeFormService.java까지 올라가서 display 변수를 확인하게 됨
				회원가입 버튼을 누를 경우, ./main/nav.jsp에서 writeForm.do가 호출이 되고
				mapping된 자바 파일인 writeFormService가 호출되어 display 변수에 value가 저장되는 구조
				
				자바와는 달리 값이 저장되지 않을 경우, null or ""가 아닌 empty로 확인해야 함
				
				요약
				회원가입 버튼 클릭 > nav.jsp > (*.do) controlServlet > WriteFormService.java: display값 설정
				(회원가입 버튼을 누르지 않은 상태에서는 display가 빈 공간으로 남아있음)
				 --%>
				 
				홈페이지 방문에 감사드립니다.<br/>
				<img src="/miniProject_jQuery/img/dog.gif">
			</c:if>
			
			<c:if test="${ not empty display }">
				<jsp:include page="${display }" />
				<%-- 회원가입창과 글쓰기창의 key 값을 중복시켜서 최종적으로 선언되는 display가 반환됨 --%>
				<%--
				"display" ▶ "/member/writeForm.jsp" ▶ "/miniProject_jQuery/member/write.do" ▶ WriteService.jav ▶ writeFail.jsp or writeOkay.jsp 반환
				index.jsp ▶ write.do: url상 이동
				이동 후 최종적으로 반환된 *.jsp가 section에 view가 됨
				 --%>
			</c:if>
			

			</h3>
		</div>

	</div><!-- container -->
	
	<div id="footer">
		<h4>BitCamp</h4>
	</div>
	
</div>

</body>
</html>

 
 <%--
 * Web Page
 Wrap(
 	Header()
 	Container(
 		Nav(), Content()
 	)
 	Footer()
 )
  --%>
 
<%-- jsp 주석

<div id="nav">
	<c:if test="${ empty display2 }">
		<jsp:include page="./main/nav.jsp" />

		jsp 파일 내 다른 jsp include 방법

		1. jsp:include(추천)
		<jsp:include/>
		jsp 파일 내 <!DOCTYPE html> ~ </html>까지 index로 호출 
		<!DOCTYPE html> 코드가 중복되면 error 발생하므로,
		include 시킬 jsp 파일은 html의 기본 구조를 삭제
		
		2. include directive
		<%@include file = "" %>
		
		차이점:
		<jsp:include />: 각각 compile 후 include
		<%@include %>: include 후 함께 compile
		aa.jsp			bb.jsp
		a=10			a=20
		
		directive: include 후 compile을 실행하기 때문에 동일하게 정의된 변수에서 중복변수 오류 발생
		jsp: 각각 컴파일하기 때문에 중복변수에 대한 문제가 발생하지 않음
			
	</c:if>

	<c:if test="${ not empty display2 }">
		<jsp:include page="${display2 }" />
	</c:if>
	
</div>
	

--%>